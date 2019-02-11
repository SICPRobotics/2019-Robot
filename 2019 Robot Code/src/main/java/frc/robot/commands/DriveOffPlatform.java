package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveOffPlatform extends Command 
{
  Timer timer = new Timer();

  public DriveOffPlatform() 
  {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("DriveOffPlatform init");
    Robot.driveTrain.invertLeft(true);
    timer.start();
  }

  @Override
  protected void execute() 
  {
    Robot.driveTrain.magicDrive(4096*5);
  }

  @Override
  protected boolean isFinished() 
  {
    if (timer.get() > .5 && Robot.driveTrain.encSpeed() == 0)
      return true;
    return false;
  }

  @Override
  protected void end() 
  {
    Robot.driveTrain.invertLeft(false);
    System.out.println("DriveOffPlatform end");
  }

  @Override
  protected void interrupted() 
  {
  }
}
