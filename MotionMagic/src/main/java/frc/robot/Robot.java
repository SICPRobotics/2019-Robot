package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.TimedRobot;


public class Robot extends TimedRobot 
{
  WPI_TalonSRX frontR,rearR,frontL,rearL;
  
  @Override
  public void robotInit() 
  {
    frontR = new WPI_TalonSRX(0);
    //frontR.configFactoryDefault();

    frontR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    frontR.setSensorPhase(true);
    frontR.selectProfileSlot(0, 0);

    frontR.config_kF(0,.3); //example: 
    frontR.config_kP(0,0.2);//.0682) example: .2
    frontR.config_kI(0,.00);
    frontR.config_kD(0,0);
    frontR.configMotionCruiseVelocity(2500,100); //example: 15000
    frontR.configMotionAcceleration(2500,100); //example: 6000
    frontR.setSelectedSensorPosition(0);

    rearR = new WPI_TalonSRX(1);
   // rearR.follow(frontR);
    rearL = new WPI_TalonSRX(2);
    rearL.setInverted(true);
   // rearL.follow(frontR);
    frontL = new WPI_TalonSRX(3);
    frontL.setInverted(true);
 //   frontL.follow(frontR);
  }

  @Override
  public void robotPeriodic() {}
  @Override
  public void disabledInit() 
  {
    frontR.setSelectedSensorPosition(0);
  }
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
    //left.set(.3);
    //right.set(.3);
    //left.set(joystick.getRawAxis(1)); 
    //System.out.println("out: "+rearL.getMotorOutputVoltage()/rearL.getBusVoltage()+" speed: "+rearL.getSelectedSensorVelocity()+"position: "+rearL.getSelectedSensorPosition());   
   // frontR.set(ControlMode.MotionMagic,100000,DemandType.AuxPID,0);
    //rearR.set(ControlMode.Follower,0);
  //  frontL.set(ControlMode.Follower,0);
   // rearL.set(ControlMode.Follower,0);
   frontR.set(ControlMode.MotionMagic,4096*5);
   rearR.set(ControlMode.Follower,0);
   rearL.set(ControlMode.Follower,0);
   frontL.set(ControlMode.Follower,0);
   
   System.out.println("distance: "+frontR.getSelectedSensorPosition()+" speed: "+frontR.getSelectedSensorVelocity()+
   " CLE: "+frontR.getClosedLoopError()+ " CLT: "+frontR.getClosedLoopTarget());
    
    }

  @Override
  public void testPeriodic() {}
}
