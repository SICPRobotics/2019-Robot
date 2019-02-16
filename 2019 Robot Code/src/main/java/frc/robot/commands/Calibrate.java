package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Calibrate extends Command 
{
  Timer timer = new Timer();

  public Calibrate() {
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    System.out.println("Calibrate init");
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.elevator.slowDrive(.082);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    if (timer.get() > 3)
      return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.elevator.setEncPosition(0);
    Robot.elevator.slowDrive(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
