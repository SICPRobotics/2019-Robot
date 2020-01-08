package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class HoldParallelDown extends Command 
{
  boolean goDown;
  Timer timer;

  public HoldParallelDown(boolean down) 
  {
    requires(Robot.parallel);  
    goDown = down;
    timer = new Timer();
  }

  @Override
  protected void initialize() 
  {
    System.out.println("HoldParallelDown init");
    timer.start();
  }

  @Override
  protected void execute() 
  {
    if (goDown)
      Robot.parallel.drive(0.65); //.75

    else if(!goDown)
      Robot.parallel.drive(-.37); //-.5
  }

  @Override
  protected boolean isFinished() 
  {
    if(goDown && Robot.parallel.bottomLimit())
    {
      System.out.println("goDown and bottomLimit");
      return true;
    }
    else if(!goDown && timer.get()>1)
    {
      System.out.println("!goDown and timer > 1");
      return true;
    }
    else if(timer.get()>5)
    {
      System.out.println("timer past 5");
      return true;
    }
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("HoldParallelDown end");
    Robot.parallel.drive(0);
    timer.reset();
  }

  @Override
  protected void interrupted() 
  {
    end();
  }
}
