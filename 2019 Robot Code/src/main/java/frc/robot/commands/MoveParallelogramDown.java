package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveParallelogramDown extends Command 
{
  public MoveParallelogramDown() 
  {
    requires(Robot.parallel);  
  }

  @Override
  protected void initialize() 
  {
    System.out.println("MoveParallelogramDown init");
  }

  @Override
  protected void execute() 
  {
    if(Robot.parallel.bottomLimit())
      Robot.parallel.drive(0);
    else
      Robot.parallel.drive(-0.1);
  }

  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("MoveParallelogramDown end");
  }

  @Override
  protected void interrupted() 
  {
    end();
  }
}
