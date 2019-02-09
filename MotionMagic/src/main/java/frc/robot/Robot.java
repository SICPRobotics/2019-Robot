package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;

public class Robot extends TimedRobot 
{
  WPI_TalonSRX talon, talon1;
  Joystick joystick;
  StringBuilder sb = new StringBuilder();
  SpeedControllerGroup left;
  @Override
  public void robotInit() 
  {
    talon = new WPI_TalonSRX(0);
    talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    talon.setSensorPhase(true);
    talon.config_kF(0,.3125);
    talon.config_kP(0,0);
    talon.config_kI(0,0);
    talon.config_kD(0,0);
    talon.configMotionCruiseVelocity(1625,1000);
    talon.configMotionAcceleration(1625,1000);
    
    talon1 = new WPI_TalonSRX(1);
    
    left = new SpeedControllerGroup(talon, talon1);
    joystick = new Joystick(0);
  }

  @Override
  public void robotPeriodic() {}
  @Override
  public void disabledInit() {}
  @Override
  public void disabledPeriodic() {}
  @Override
  public void autonomousInit() {}
  @Override
  public void autonomousPeriodic() {}
  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() 
  {
    //left.set(joystick.getRawAxis(1)); 
    //System.out.println("out: "+talon.getMotorOutputVoltage()/talon.getBusVoltage()+" speed: "+talon.getSelectedSensorVelocity()+"position: "+talon.getSelectedSensorPosition());   
    talon.set(ControlMode.MotionMagic,10000,DemandType.AuxPID,0);
    talon1.follow(talon, FollowerType.AuxOutput1);
    System.out.println("distance: " + talon.getSelectedSensorPosition());
  }

  @Override
  public void testPeriodic() {}
}
