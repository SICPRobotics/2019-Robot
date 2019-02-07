package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleClaws extends Command 
{
  double speed;

  public ToggleClaws(double howFast) 
  {
    requires(Robot.claws);
    speed = howFast;
  }

  @Override
  protected void initialize() 
  {

  }

  @Override
  protected void execute() 
  {
    Robot.claws.moveClaws(speed);
  }

  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.claws.moveClaws(0);
  }

  @Override
  protected void interrupted() {}
}
