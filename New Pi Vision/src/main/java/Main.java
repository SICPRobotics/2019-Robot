
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import edu.wpi.cscore.AxisCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.vision.VisionPipeline;
import edu.wpi.first.vision.VisionThread;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;

import wolfbyte.MjpegStream;
import wolfbyte.Tape;
import wolfbyte.GripPipeline;

public final class Main {
    private static String configFile = "/boot/frc.json";

    @SuppressWarnings("MemberName")
    public static class CameraConfig {
        public String name;
        public String path;
        public JsonObject config;
        public JsonElement streamConfig;
    }

    public static int team;
    public static boolean server;
    public static List<CameraConfig> cameraConfigs = new ArrayList<>();

    private static MjpegStream stream = new MjpegStream();
    public static Mat rgb;

    private Main() {
    }

    /**
     * Report parse error.
     */
    public static void parseError(String str) {
        System.err.println("config error in '" + configFile + "': " + str);
    }

    /**
     * Read single camera configuration.
     */
    public static boolean readCameraConfig(JsonObject config) {
        CameraConfig cam = new CameraConfig();

        // name
        JsonElement nameElement = config.get("name");
        if (nameElement == null) {
            parseError("could not read camera name");
            return false;
        }
        cam.name = nameElement.getAsString();

        // path
        JsonElement pathElement = config.get("path");
        if (pathElement == null) {
            parseError("camera '" + cam.name + "': could not read path");
            return false;
        }
        cam.path = pathElement.getAsString();

        // stream properties
        cam.streamConfig = config.get("stream");

        cam.config = config;

        cameraConfigs.add(cam);
        return true;
    }

    /**
     * Read configuration file.
     */
    @SuppressWarnings("PMD.CyclomaticComplexity")
    public static boolean readConfig() {
        // parse file
        JsonElement top;
        try {
            top = new JsonParser().parse(Files.newBufferedReader(Paths.get(configFile)));
        } catch (IOException ex) {
            System.err.println("could not open '" + configFile + "': " + ex);
            return false;
        }

        // top level must be an object
        if (!top.isJsonObject()) {
            parseError("must be JSON object");
            return false;
        }
        JsonObject obj = top.getAsJsonObject();

        // team number
        JsonElement teamElement = obj.get("team");
        if (teamElement == null) {
            parseError("could not read team number");
            return false;
        }
        team = teamElement.getAsInt();

        // ntmode (optional)
        if (obj.has("ntmode")) {
            String str = obj.get("ntmode").getAsString();
            if ("client".equalsIgnoreCase(str)) {
                server = false;
            } else if ("server".equalsIgnoreCase(str)) {
                server = true;
            } else {
                parseError("could not understand ntmode value '" + str + "'");
            }
        }

        // cameras
        JsonElement camerasElement = obj.get("cameras");
        if (camerasElement == null) {
            parseError("could not read cameras");
            return false;
        }
        JsonArray cameras = camerasElement.getAsJsonArray();
        for (JsonElement camera : cameras) {
            if (!readCameraConfig(camera.getAsJsonObject())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Start running the camera.
     */
    public static VideoSource startCamera(CameraConfig config) {
        System.out.println("Starting camera '" + config.name + "' on " + config.path);
        CameraServer inst = CameraServer.getInstance();
        UsbCamera camera = new UsbCamera(config.name, config.path);
        MjpegServer server = inst.startAutomaticCapture(camera);

        Gson gson = new GsonBuilder().create();

        camera.setConfigJson(gson.toJson(config.config));
        camera.setConnectionStrategy(VideoSource.ConnectionStrategy.kKeepOpen);

        if (config.streamConfig != null) {
            server.setConfigJson(gson.toJson(config.streamConfig));
        }

        return camera;
    }

    /**
     * Example pipeline.
     */
    public static class MyPipeline implements VisionPipeline {
        public int val;

        @Override
        public void process(Mat mat) {
            val += 1;
        }
    }

    public static NetworkTableEntry setUpNetworkTables() {
        // start NetworkTables
        NetworkTableInstance ntinst = NetworkTableInstance.getDefault();
        if (server) {
            System.out.println("Setting up NetworkTables server");
            ntinst.startServer();
        } else {
            System.out.println("Setting up NetworkTables client for team " + team);
            ntinst.setNetworkIdentity("Raspberry Pi");
            ntinst.startClientTeam(5822);
            ntinst.setUpdateRate(0.01);
        }

        NetworkTable table = ntinst.getTable("/SmartDashboard");
        NetworkTableEntry entry = table.getEntry("centerVal");
        return entry;

    }

    public static void updateNetworkTables(NetworkTableEntry entry, int inc) {
        entry.setDouble(entry.getDouble(0) + 1);
        System.out.println(entry.getDouble(0.0));
    }

    /**
     * Main.
     */
    public static void main(String... args) {
        System.out.println("Vision is starting up...");
        if (args.length > 0) {
            configFile = args[0];
        }

        // read configuration
        if (!readConfig()) {
            return;
        }

        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        VideoSource axisCamera = new AxisCamera("wolfbyte axis camera", "10.58.22.11");

        // Imgcodecs.imwrite("/home/pi/RawImage.jpg", rgb);

        // start cameras
        List<VideoSource> cameras = new ArrayList<>();
        for (CameraConfig cameraConfig : cameraConfigs) {
            cameras.add(startCamera(cameraConfig));
        }

        NetworkTableEntry entry = setUpNetworkTables();

        try {
            stream.startStream();
        } catch (IOException e) {
            System.out.println(e);
        }
        // something like this for GRIP:
        VisionThread visionThread = new VisionThread(axisCamera, new GripPipeline(), pipeline -> {
           
            List<MatOfPoint> contours = pipeline.filterContoursOutput();
            List<Tape> tapes = new ArrayList<Tape>();
            if (contours.size() >= 2) {
                rgb = new Mat();
                CvSink sink = new CvSink("opencv_wolfbyte axis camera");
                sink.setSource(axisCamera);
                sink.grabFrame(rgb);

                Imgcodecs.imwrite("/home/pi/WhatTheCameraSees.jpg", rgb);

                for (MatOfPoint contour : contours) {
                    Tape tape = new Tape(contour);
                    tapes.add(tape);
                    rgb = tape.drawOn(rgb);
                }

                stream.sendMat(rgb);

                Imgcodecs.imwrite("/home/pi/TapesFound.jpg", rgb);
            }
        });

        visionThread.start();
        // }

        // loop forever
        int inc = 0;

        for (;;) {
            System.out.println("Ran");
            if (!axisCamera.isConnected()) {
                System.out.println("Not connected to Axis Camera");
            } else if (axisCamera.isConnected()) {
                System.out.println("Connected to Axis Camera");
            }
            // updateNetworkTables(entry, inc);
            inc++;

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                return;
            }
        }
    }
}