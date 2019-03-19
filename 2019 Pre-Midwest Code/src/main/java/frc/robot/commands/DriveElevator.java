package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveElevator extends Command 
{
  double speedy;

  public DriveElevator(double speed) 
  {
    requires(Robot.elevator);
    speedy = speed;
  }

  @Override
  protected void initialize() 
  {
    System.out.println("DriveElevator init");
  }

  @Override
  protected void execute() 
  {
    Robot.elevator.slowDrive(speedy);
    System.out.println("height: "+Robot.elevator.elevatorHeight());
  }

  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  @Override
  protected void end() 
  {
    System.out.println("DriveElevator end");
    //Robot.elevator.slowDrive(0);
    MoveElevatorMagic hold = new MoveElevatorMagic(Robot.elevator.elevatorHeight());
    hold.start();
  }

  @Override
  protected void interrupted() 
  {
    System.out.println("DriveElevator interrupted");
    end();
    //Robot.elevator1.slowDrive(0);
  }
}
