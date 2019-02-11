package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Claws extends Subsystem 
{
  //motors: use RobotMap.k_claw1, RobotMap.k_claw2
  VictorSPX claw1, claw2;

  public Claws()
  {
    claw1 = new VictorSPX(RobotMap.k_claw1);
    claw2 = new VictorSPX(RobotMap.k_claw2);
    claw2.setInverted(true);
    claw2.follow(claw1);
  }
  
  @Override
  public void initDefaultCommand() 
  {
  
  }

  public void moveClaws(double speed)
  {
    claw1.set(ControlMode.PercentOutput,speed);
  }
}
