package wolfbyte;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MjpegStream {

    private File file;

    private ArrayList<OutputStream> openOutputStreams;
    private ArrayList<OutputStream> openDataStreams;
    private int num;

	public void startStream() throws IOException {
        openOutputStreams = new ArrayList<>();
        openDataStreams = new ArrayList<>();
        num = 0;
        file = new File("/home/pi/TapesFound.jpg");
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		System.out.println("E X E C U T E D !!!!");
		int port = 25565;
		HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
		server.createContext("/", (rootHandler) -> {
			byte[] response = "<!DOCTYPE html><html><body><img src=\"./mjpeg\"></body></html>".getBytes();
			rootHandler.sendResponseHeaders(200, response.length);
			final OutputStream os = rootHandler.getResponseBody();
			os.write(response);
			os.flush();
			rootHandler.close();
		});

		server.createContext("/mjpeg", (mjpegHandler) -> {
			Headers h = mjpegHandler.getResponseHeaders();
			h.set("Content-Type", "multipart/x-mixed-replace; boundary=123456789000000000000987654321");
			mjpegHandler.sendResponseHeaders(200, 0);
			OutputStream os = mjpegHandler.getResponseBody();
			openOutputStreams.add(os);
					/*System.out.println(mat);

					int length = (int) (mat.total() * mat.elemSize());
					byte img[] = new byte[length];
					mat.get(0, 0, img);
					
					Imgcodecs.imwrite("/home/pi/StreamerMat.jpg", mat);
					
					mat.get(0, 0, img);
					os.write(("--123456789000000000000987654321\r\n" + "Content-Type:image/jpeg\r\n" + "Content-Length:" + img.length + "\r\n\r\n").getBytes());
					os.write(img);
					os.write(("\r\n\r\n").getBytes());
					os.flush();
                    os.close();*/
        });

        server.createContext("/data", (handler) -> {
            Headers h = handler.getResponseHeaders();
			h.set("Content-Type", "text/plain; boundary=123456789000000000000987654321");
			byte[] response = "Test test".getBytes();
			handler.sendResponseHeaders(200, response.length);
			final OutputStream os = handler.getResponseBody();
			os.write(response);
			os.flush();
			openDataStreams.add(os);
        });

		server.setExecutor(null);
		server.start();
        System.out.println("Server is running on port: "+port);
        
        
    }

    public void sendMat(Mat mat) {
        //The BAD way of doing it
        byte[] img;
        try {
            img = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            System.out.println("Failed to read TapesFound.jpg:");
            System.out.println(e.getLocalizedMessage());
            img = new byte[0];
        }


        //Convert! MAKE SURE THIS ACTUALLY WORKS -- not working
        /*int length = (int) (mat.total() * mat.elemSize());
		byte[] img = new byte[length];
        mat.get(0, 0, img);*/

        
        //Go through all the output streams and send the new image.
        for(OutputStream os : openOutputStreams) {
            try {
                os.write(("--123456789000000000000987654321\r\n" + "Content-Type:image/jpeg\r\n" + "Content-Length:" + img.length + "\r\n\r\n").getBytes());
                os.write(img);
                os.write(("\r\n\r\n").getBytes());
                os.flush();
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                try {
                    os.close();
                } catch (IOException ee) {
                    System.out.println(ee.getLocalizedMessage());
                    System.out.println("IOExeption and output stream could not be closed");
                }
            }
            
        }

        //Update the data
        byte[] data = Integer.toString(num).getBytes();

        for(OutputStream os : openDataStreams){
            try {
                os.write(("--123456789000000000000987654321\r\n" + "Content-Type:text/plain\r\n" + "Content-Length:" + data.length + "\r\n\r\n").getBytes());
                os.write(data);
                os.write(("\r\n\r\n").getBytes());
                os.flush();
            } catch (IOException e) {
                System.out.println(e.getLocalizedMessage());
                try {
                    os.close();
                } catch (IOException ee) {
                    System.out.println(ee.getLocalizedMessage());
                    System.out.println("IOExeption and output stream could not be closed");
                }
            }
        }
        num++;
    }

}