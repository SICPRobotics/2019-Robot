package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothing extends Command 
{
  public DoNothing() {}

  @Override
  protected void initialize() 
  {
    System.out.println("DoNothing init");
  }
  @Override
  protected void execute() {}
  @Override
  protected boolean isFinished() 
  {
    return true;
  }
  @Override
  protected void end() 
  {
    System.out.println("DoNothing end");
  }

  @Override
  protected void interrupted() {}
}
