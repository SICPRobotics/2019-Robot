package wolfbyte;

import java.util.ArrayList;
import java.util.Properties;

import wolfbyte.game_elements.*;

public class ElementManager {
    private ArrayList<Hatch> hatches;

    ElementManager() {

    }
    public void updateTapes(ArrayList<Tape> tapesFound){
        for (Tape newTape : tapesFound) {
            Tape closestOldestTape;
            for (Hatch oldHatch : hatches) {
                Tape oldTape = oldHatch.getTapeOnSide(newTape.getSide());

                //We need to figure out which tapes have just been moved.
                //get the distance
                
            }
        }
    }
}