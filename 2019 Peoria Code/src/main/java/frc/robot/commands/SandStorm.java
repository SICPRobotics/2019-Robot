package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SandStorm extends CommandGroup {
  public SandStorm() 
  {
    addSequential(new DriveOffPlatform());
    addSequential(new Calibrate());
  }
}
