package wolfbyte.game_elements;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Tape extends Element {
    RotatedRect rect;
    private String side;
    MatOfPoint contour;
    public Tape(MatOfPoint contour){
        this.contour = contour;
        rect = Imgproc.minAreaRect(new MatOfPoint2f(contour.toArray()));
        if (rect.angle < -36) {
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
        Imgproc.putText(mat, side + " at " + Double.toString(rect.angle).substring(0, 3), rect.center, 0, 0.5, new Scalar(255.0,255.0,255.0));

        //Draw predicted partner position
        Imgproc.circle(mat, predictCounterpart(), 5, new Scalar(0.0,0.0,255.0));

        return mat;
    }
    public Point predictCounterpart() {
        return new Point(rect.center.x + ((side == "left" ? rect.size.height : rect.size.width) * 5 * (side == "left" ? 1 : -1)), rect.center.y);
    }
    public String getSide() {
        return side;
    }
}

