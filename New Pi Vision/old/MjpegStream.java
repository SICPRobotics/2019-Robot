import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpServer;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class MjpegStream {
	
	public Mat mat;

	public void startStream() throws IOException {
		/*mat = new Mat();
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
			//while (true) {
				//try {
					
					System.out.println(mat);

					//mat = Main.rgb;

					int length = (int) (mat.total() * mat.elemSize());
					byte img[] = new byte[length];
					mat.get(0, 0, img);
					
					Imgcodecs.imwrite("/home/pi/StreamerMat.jpg", mat);
					
					mat.get(0, 0, img);
					os.write(("--123456789000000000000987654321\r\n" + "Content-Type:image/jpeg\r\n" + "Content-Length:" + img.length + "\r\n\r\n").getBytes());
					os.write(img);
					os.write(("\r\n\r\n").getBytes());
					os.flush();
					os.close();
					/*try {
						Thread.sleep(1000);
					  } catch (InterruptedException ex) {
						  os.close();
						return;
					  }*/
				/*} catch (e) {
					e.printStackTrace();
					mjpegHandler.close();
					os.close();
				} */
			//}
		/*});

		server.setExecutor(null);
		server.start();
		System.out.println("Server is running on port: "+port);
	}*/}

	public void setMat(Mat mat) {
		this.mat = mat;
		System.out.println("its been done");
	}

}