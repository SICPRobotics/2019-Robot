package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldParallelDown extends Command 
{
  boolean goDown;
  public HoldParallelDown(boolean down) 
  {
    requires(Robot.parallel);  
    goDown = down;
  }

  @Override
  protected void initialize() 
  {
    System.out.println("HoldParallelDown init");
  }

  @Override
  protected void execute() 
  {
    if (goDown)
      Robot.parallel.drive(0.6);
    else if(!goDown)
      Robot.parallel.drive(-.5);
  }

  @Override
  protected boolean isFinished() 
  {
    if(Robot.parallel.bottomLimit())
      return true;
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("HoldParallelDown end");
    Robot.parallel.drive(0);
  }

  @Override
  protected void interrupted() 
  {
    end();
  }
}
