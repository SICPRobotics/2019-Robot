/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.buttons.Trigger;

/**
 * Add your docs here.
 */
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