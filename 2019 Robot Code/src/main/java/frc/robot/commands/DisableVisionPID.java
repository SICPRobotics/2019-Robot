package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DisableVisionPID extends Command 
{
  public DisableVisionPID() 
  {
    requires(Robot.driveTrain);
  }

  @Override
  protected void initialize() 
  {
    Robot.driveTrain.disable();
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
