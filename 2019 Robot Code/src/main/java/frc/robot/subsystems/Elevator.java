package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Elevator extends Subsystem 
{
  WPI_TalonSRX elevator1, elevator2;

  public Elevator() 
  {
    elevator1 = new WPI_TalonSRX(RobotMap.k_arm1);
    elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
  }

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void magicMotion(double targetPos)
  {
   
  }

  public void slowDrive()
  {
    
  }
}
