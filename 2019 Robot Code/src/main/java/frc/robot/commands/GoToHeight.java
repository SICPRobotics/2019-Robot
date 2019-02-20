package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GoToHeight extends CommandGroup 
{
  public GoToHeight(double height) 
  {
    addSequential(new MoveElevatorMagic(height));
    addParallel(new HoldParallelDown(true));
  }
}
