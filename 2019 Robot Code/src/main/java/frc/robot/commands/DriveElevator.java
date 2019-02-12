package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveElevator extends Command 
{
  public DriveElevator() 
  {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("DriveElevator init");
  }

  @Override
  protected void execute() 
  {
    //Robot.elevator.slowDrive(.2);
  }

  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("DriveElevator end");
    //Robot.elevator1.slowDrive(0);
  }

  @Override
  protected void interrupted() 
  {
    System.out.println("DriveElevator interrupted");
    //Robot.elevator1.slowDrive(0);
  }
}
