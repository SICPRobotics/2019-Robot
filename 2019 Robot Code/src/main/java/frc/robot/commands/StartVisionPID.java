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
    Robot.driveTrain.enable();
  }

  @Override
  protected void execute() {}

  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  @Override
  protected void end() 
  {
    Robot.driveTrain.disable();
  }

  @Override
  protected void interrupted() {}
}
