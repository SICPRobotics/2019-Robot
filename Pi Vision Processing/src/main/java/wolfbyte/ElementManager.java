package wolfbyte;

import java.util.ArrayList;
import java.util.Properties;

import org.opencv.core.MatOfPoint2f;
import org.opencv.imgproc.Imgproc;

import wolfbyte.game_elements.*;

public class ElementManager {
    private ArrayList<Hatch> hatches;
    private int currentId;
    private int camWidth = 160;

    public ElementManager() {
        hatches = new ArrayList<Hatch>();
        currentId = 0;
    }
    public void updateTapes(ArrayList<Tape> tapesFound){
        /*ArrayList<Hatch> newHatches = new ArrayList();
        for (Tape newTape : tapesFound) {
            Tape closestOldestTape;
            for (Hatch oldHatch : hatches) {
                Tape oldTape = oldHatch.getTapeOnSide(newTape.getSide());

                //We need to figure out which tapes have just been moved.
                //get the distance
                
            }
        }*/
        ArrayList<Hatch> foundHatches = matchTapes(tapesFound);
        for (Hatch foundHatch : foundHatches) {
            for (Hatch oldHatch : hatches) {
                if (Math.abs(foundHatch.getCenter().x - oldHatch.getCenter().x) < 10.0) {
                    foundHatch.id = oldHatch.id == -1 ? nextId() : oldHatch.id;
                } else {
                    foundHatch.id = nextId();
                }
            }
        }
        hatches = foundHatches;
        if (hatches.size() != 0) {
            Hatch middleHatch = hatches.get(0);
            for (Hatch hatch : hatches) {
                if (Math.abs(camWidth - hatch.getCenter().x) < Math.abs(camWidth - middleHatch.getCenter().x)) {
                    middleHatch = hatch;
                }
            }
            middleHatch.selected = true;
        }
    }
    public static ArrayList<Hatch> matchTapes(ArrayList<Tape> tapes){
        ArrayList<Hatch> foundHatches = new ArrayList<Hatch>();
        for (Tape tape : tapes) {
            for (Tape possibleCounterpart : tapes) {
                if (tape != possibleCounterpart && tape.getSide() != possibleCounterpart.getSide() && Imgproc.pointPolygonTest(new MatOfPoint2f(possibleCounterpart.getContour().toArray()), tape.predictCounterpart(), false) >= 0 ) {
                    Tape left = tape.getSide() == "left" ? tape : possibleCounterpart;
                    Tape right = tape.getSide() == "right" ? tape : possibleCounterpart;

                    foundHatches.add(new Hatch(left, right));

                    //Cannot modify an arraylist while iterating through it when using a for-each loop
                    //tapes.remove(tape);
                    //tapes.remove(possibleCounterpart);
                }
            }
        }
        return foundHatches;
    }
    public ArrayList<Hatch> getHatches() {
        return hatches;
    }
    private int nextId() {
        currentId++;
        return currentId - 1;
    }
}