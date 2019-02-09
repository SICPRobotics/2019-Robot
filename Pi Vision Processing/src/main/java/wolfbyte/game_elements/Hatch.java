package wolfbyte.game_elements;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

public class Hatch extends Element {
    private Tape left;
    private Tape right;
    public int id;
    public boolean selected;

    public Hatch(Tape left, Tape right) {
      this.left = left;
      this.right = right;
      selected = false;
      id = -1;
    }
    public Tape getTapeOnSide(String side){
      if (side == "left") {
        return left;
      } else {
        return right;
      }
    }
    public void drawOn (Mat mat){
      RotatedRect rect = new RotatedRect();

      
      Point topLeft = new Point(left.getRect().boundingRect().x,left.getRect().boundingRect().y);
      Point bottomLeft = new Point(left.getRect().boundingRect().x,left.getRect().boundingRect().y + left.getRect().boundingRect().height);

      Point topRight = new Point(right.getRect().boundingRect().x + right.getRect().boundingRect().width, right.getRect().boundingRect().y);
      Point bottomRight = new Point(right.getRect().boundingRect().x + right.getRect().boundingRect().width, right.getRect().boundingRect().y + right.getRect().boundingRect().height);
      
      Point[] vertices = new Point[]{topLeft, bottomLeft, bottomRight, topRight};

      for (int j = 0; j < 4; j++){  
        Imgproc.line(mat, vertices[j], vertices[(j+1)%4], new Scalar(154,0,232));
      }
      if (id != -1) {
        Imgproc.rectangle(mat, getCenter(), new Point(getCenter().x + 10, getCenter().y - 10), new Scalar(selected ? 255 : 0, 0, 0), -1);
        Imgproc.putText(mat, Integer.toString(id), getCenter(), 0, 0.5, new Scalar(255, 255, 255));
      }
    }

    public Point getCenter() {
      return(new Point((left.getRect().center.x + right.getRect().center.x)/2, (left.getRect().center.y + right.getRect().center.y)/2));
    }
}