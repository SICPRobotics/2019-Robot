package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

public class XboxTrig extends Trigger {
	boolean isLeftTrigger;
	
	public XboxTrig(boolean leftSide)
	{
		isLeftTrigger = leftSide;
	}
    public boolean get() 
    {
    	if (isLeftTrigger && OI.xbox.getRawAxis(2) > 0)
			return true;
		if(!isLeftTrigger && OI.xbox.getRawAxis(3) > 0)
			return true;
		return false;
    }
}