package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleBeak extends Command 
{
  public boolean direction = true;

  public ToggleBeak() 
  {
    requires(Robot.beak);
  }

  @Override
  protected void initialize() {}

  @Override
  protected void execute() 
  {
    if (direction)
      Robot.beak.shutBeak();
    else if (!direction)
      Robot.beak.openBeak();
  }

  @Override
  protected boolean isFinished() 
  {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    direction = !direction;
  }

  @Override
  protected void interrupted() {}
}
