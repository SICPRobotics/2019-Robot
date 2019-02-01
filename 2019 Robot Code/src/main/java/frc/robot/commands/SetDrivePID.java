package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetDrivePID extends Command 
{
  double setpt, dist;

  public SetDrivePID(double setpoint, double distance) 
  {
    requires(Robot.driveTrain);
    setpt = setpoint;
    dist = distance;
  }

  @Override
  protected void initialize() 
  {
    Robot.driveTrain.setSet(setpt);
    Robot.driveTrain.enable();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    if (Robot.driveTrain.encDistance() > dist)
      return true;
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.driveTrain.disable();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
