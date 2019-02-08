package wolfbyte;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Tape {
    RotatedRect rect;
    String side;
    public Tape(MatOfPoint contour){
        rect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
        if (rect.angle > 0 && rect.angle < 180) {
            side = "left";
        } else {
            side = "right";
        }
    }
    public Mat drawOn(Mat mat){
        Point[] vertices = new Point[4];  
        rect.points(vertices);  
        for (int j = 0; j < 4; j++){  
            Imgproc.line(mat, vertices[j], vertices[(j+1)%4], new Scalar(0,255,0));
        }
        Imgproc.putText(mat, side, rect.center, 0, 1.0, new Scalar(255.0,255.0,255.0));
        return mat;
    }
}

