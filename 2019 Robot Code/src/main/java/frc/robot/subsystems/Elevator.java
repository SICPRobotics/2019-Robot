package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Elevator extends Subsystem 
{
  WPI_TalonSRX elevator1, elevator2;
  VictorSPX parallel1, parallel2;

  public Elevator() 
  {
    /*elevator1 = new WPI_TalonSRX(4);
    elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    elevator1.setSensorPhase(false);
    elevator1.selectProfileSlot(0, 0);
    elevator1.config_kF(0, .3);
    elevator1.config_kP(0, .2);
    elevator1.config_kI(0, 0);
    elevator1.config_kD(0, 0);
    elevator1.configMotionCruiseVelocity(3000,100); //probably want slower
    elevator1.configMotionAcceleration(2500,100); //probably want slower
    elevator1.setSelectedSensorPosition(0);
    
    elevator2 = new WPI_TalonSRX(5);
    elevator2.follow(elevator1);
    */
  }

  @Override
  public void initDefaultCommand() {}

  public void magicMotion(double targetPos)
  {
    //elevator1.set(ControlMode.MotionMagic,targetPos);
    //elevator2.set(ControlMode.Follower,0);
  }

  public void slowDrive(double speed)
  {
    //elevator1.set(ControlMode.PercentOutput, speed);
  }

  public double elevatorHeight()
  {
    return elevator1.getSelectedSensorPosition();
  }

  public void moveParallel()
  {
    
  }
}