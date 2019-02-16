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
    elevator1 = new WPI_TalonSRX(4);
    elevator1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    elevator1.setSensorPhase(false);
    elevator1.selectProfileSlot(0, 0);
    elevator1.config_kF(0, .2);
    elevator1.config_kP(0, 0);
    elevator1.config_kI(0, 0);
    elevator1.config_kD(0, 0);
    elevator1.configMotionCruiseVelocity(51145,100); 
    elevator1.configMotionAcceleration(25000,100); 
    elevator1.setSelectedSensorPosition(0);
    
    elevator2 = new WPI_TalonSRX(5);
    elevator2.setInverted(true);
    //elevator2.follow(elevator1);
   // elevator2.set(ControlMode.Follower,4);
   // elevator1.set(ControlMode.MotionMagic,762000);
  }
  @Override
  public void initDefaultCommand() {}

  public void magicMotion(double targetPos)
  {
    elevator1.set(ControlMode.MotionMagic,targetPos);
    elevator2.set(ControlMode.Follower,4);
  }

  public void slowDrive(double speed)
  {
    elevator1.set(ControlMode.PercentOutput, speed);
    elevator2.set(ControlMode.PercentOutput,speed);
    //System.out.println("I'm in slow drive");
  }

  public double elevatorHeight()
  {
    return elevator1.getSelectedSensorPosition();
  }

  public void setEncPosition(int set)
  {
    elevator1.setSelectedSensorPosition(set);
  }

  public void moveParallel()
  {
    
  }
}