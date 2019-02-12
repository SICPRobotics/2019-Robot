package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveElevatorMagic extends Command 
{
  double targetPos;

  public MoveElevatorMagic(double set) 
  {
    requires(Robot.elevator);
    targetPos = set;
  }

  @Override
  protected void initialize() 
  {
    System.out.println("MoveElevatorMagic init");
  }

  @Override
  protected void execute() 
  {
    //Robot.elevator.magicMotion(targetPos);
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
  protected void interrupted() {
  }
}
