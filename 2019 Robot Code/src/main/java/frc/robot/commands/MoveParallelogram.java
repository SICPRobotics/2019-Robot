package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveParallelogram extends Command 
{
  public MoveParallelogram() 
  {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("MoveParallelogram init");
  }

  @Override
  protected void execute() 
  {
  }

  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  @Override
  protected void end() 
  {
  }

  @Override
  protected void interrupted() 
  {
  }
}
