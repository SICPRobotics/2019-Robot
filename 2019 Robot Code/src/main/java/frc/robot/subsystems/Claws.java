package frc.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Claws extends Subsystem 
{
  //motors: use RobotMap.k_claw1, RobotMap.k_claw2
  VictorSP claw1, claw2;

  public Claws()
  {
    claw1 = new VictorSP(RobotMap.k_claw1);
    claw2 = new VictorSP(RobotMap.k_claw2);
  }
  
  @Override
  public void initDefaultCommand() 
  {
  
  }

  public void moveClaws(double speed)
  {
    claw1.set(speed);
    claw2.set(-1 * speed);
  }
}
