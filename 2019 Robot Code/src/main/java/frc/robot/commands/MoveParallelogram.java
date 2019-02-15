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
    Robot.elevator.moveParallel();
  }

  @Override
  protected boolean isFinished() 
  {
    return true;
  }

  @Override
  protected void end() 
  {
    System.out.println("MoveParallogram end");
  }

  @Override
  protected void interrupted() 
  {
  }
}
