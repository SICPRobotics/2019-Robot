package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Parallelogram extends Subsystem 
{
  WPI_TalonSRX talon;
  DigitalInput limitUp, limitDown;

  public Parallelogram()
  {
    //victor = new VictorSPX(0);
    //limitUp = new DigitalInput(0);
    talon = new WPI_TalonSRX(4);
    limitDown = new DigitalInput(0);
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
    return !limitDown.get(); //limit switch returns true when not pressed
  }

  public void drive(double speed)
  {
    talon.set(ControlMode.PercentOutput, speed);
  }
}
