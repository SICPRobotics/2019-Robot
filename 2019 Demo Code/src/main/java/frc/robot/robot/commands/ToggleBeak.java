package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ToggleBeak extends Command 
{
  public boolean direction = false;

  public ToggleBeak() 
  {
    requires(Robot.beak);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("ToggleBeak init");
  }

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

  @Override
  protected void end() 
  {
    System.out.println("ToggleBeak end");
    direction = !direction;
    Robot.open = !Robot.open;
  }

  @Override
  protected void interrupted() {}
}
