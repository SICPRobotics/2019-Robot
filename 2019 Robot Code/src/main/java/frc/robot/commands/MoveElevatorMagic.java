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
    return true;
  }

  @Override
  protected void end() 
  {
    System.out.println("MoveElevatorMagic end");
  }

  @Override
  protected void interrupted() {
  }
}
