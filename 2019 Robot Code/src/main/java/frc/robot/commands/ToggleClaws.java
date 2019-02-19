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
    System.out.println("ToggleClaws init");
  }

  @Override
  protected void execute() 
  {
    //Robot.claws.moveClaws(speed);
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
    System.out.println("ToggleClaws end");
    Robot.claws.moveClaws(0);
  }

  @Override
  protected void interrupted() {}
}
