package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Claws extends Subsystem 
{
  VictorSPX claw1, claw2;

  public Claws()
  {
    /*claw1 = new VictorSPX(0);
    claw2 = new VictorSPX(1);
    claw2.setInverted(true);
    claw2.follow(claw1);*/
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
