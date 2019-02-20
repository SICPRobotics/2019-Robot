package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Calibrate extends Command 
{
  Timer timer = new Timer();

  public Calibrate() 
  {
    requires(Robot.elevator);
  }

  @Override
  protected void initialize() 
  {
    System.out.println("Calibrate init");
    timer.start();
  }

  @Override
  protected void execute() 
  {
    Robot.elevator.slowDrive(.082);
  }

  @Override
  protected boolean isFinished() 
  {
    if (timer.get() > 3)
      return true;
    return false;
  }

  @Override
  protected void end() 
  {
    Robot.elevator.setEncPosition(0);
    System.out.println("Calibrate end" + Robot.elevator.elevatorHeight());
    Robot.elevator.slowDrive(0);
  }

  @Override
  protected void interrupted() {
  }
}
