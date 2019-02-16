package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Parallelogram extends Subsystem 
{
  VictorSPX victor;
  DigitalInput limitUp, limitDown;

  public Parallelogram()
  {
    victor = new VictorSPX(0);
    //limitUp = new DigitalInput(0);
    //limitDown = new DigitalInput(1);
  }
  
  @Override
  public void initDefaultCommand() 
  {

  }

  public boolean topLimit()
  {
    return limitUp.get();
  }

  public boolean bottomLimit()
  {
    return limitDown.get();
  }

  public void drive(double speed)
  {
    victor.set(ControlMode.PercentOutput, speed);
  }
}
