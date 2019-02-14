package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StartVisionPID extends Command 
{
  public StartVisionPID() 
  {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("StartVisionPID init");
    Robot.driveTrain.enable();
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() 
  {
    return true;
  }

  @Override
  protected void end() {}

  @Override
  protected void interrupted() {}
}
