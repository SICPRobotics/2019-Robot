package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveParallelogramUp extends Command 
{
  public MoveParallelogramUp() 
  {
    requires(Robot.parallel);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("MoveParallelogramUp init");
  }

  @Override
  protected void execute() 
  {
    Robot.parallel.drive(.1);
  }

  @Override
  protected boolean isFinished() 
  {
    if(Robot.parallel.topLimit())
      return true;
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("MoveParallogramUp end");
  }

  @Override
  protected void interrupted() 
  {
  }
}
