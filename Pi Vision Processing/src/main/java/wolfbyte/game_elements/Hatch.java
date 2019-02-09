package wolfbyte.game_elements;

public class Hatch extends Element {
    private Tape left;
    private Tape right;
    public Hatch(Tape left, Tape right) {
      this.left = left;
      this.right = right;
    }
    public Tape getTapeOnSide(String side){
      if (side == "left") {
        return left;
      } else {
        return right;
      }
    }
}