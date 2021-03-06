package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain extends PIDSubsystem 
{
  WPI_TalonSRX frontL, rearL, frontR, rearR;
  DifferentialDrive robotBase;
  SpeedControllerGroup left, right;

  public DriveTrain()
  {
    super("DriveTrain", 1, 2, 3);
    setSetpoint(0);

    frontR = new WPI_TalonSRX(0);
    frontR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    frontR.setSensorPhase(true);
    frontR.selectProfileSlot(0, 0);
    frontR.config_kF(0,.3); //example: 
    frontR.config_kP(0,.2);//.0682) example: .2
    frontR.config_kI(0,0);
    frontR.config_kD(0,0);
    frontR.configMotionCruiseVelocity(3000,100); //example: 15000
    frontR.configMotionAcceleration(2500,100); //example: 6000
    frontR.setSelectedSensorPosition(0);

    rearR = new WPI_TalonSRX(1);
    
    frontL = new WPI_TalonSRX(3);
    frontL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    frontL.setSensorPhase(false);

    rearL = new WPI_TalonSRX(2);
    
    left = new SpeedControllerGroup(frontL, rearL);
    right = new SpeedControllerGroup(frontR, rearR);

    robotBase = new DifferentialDrive(left, right);
    robotBase.feedWatchdog(); //might mess stuff up
    //getWatchdog().setExpiration(100);
  }
  
  @Override
  public void initDefaultCommand() {}
  
  @Override
  protected double returnPIDInput() 
  {
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) {}
  
  public void invertLeft(boolean invert)
  {
    rearL.setInverted(invert);
    frontL.setInverted(invert);
  }

  public void magicDrive(double distance)
  {
    frontR.set(ControlMode.MotionMagic,distance);
    //rearR.set(ControlMode.Follower,0);
    //rearL.set(ControlMode.Follower,0);
    frontL.set(ControlMode.Follower,0);  
  }

  public double encSpeed()
  {
    return frontR.getSelectedSensorVelocity();
  }

  public void cheesyDrive(double moveValue, double rotateValue, double scale) 
  {
    scale = ((scale + 1) / 5) + .6;

    //Dead zone on y axis value
    if (Math.abs(moveValue) < .005)
      moveValue = 0;
    
    //Dead zone on x axis only if y value is small
    if (Math.abs(rotateValue) < .005 && Math.abs(moveValue) < .1)
      rotateValue = 0;
    
    moveValue = moveValue * scale * -1;
    rotateValue = rotateValue * scale;

   robotBase.arcadeDrive(moveValue, rotateValue, true);
  }
  
  public void tankDrive(Joystick jL, Joystick jR) 
  {
    double leftValue = jL.getRawAxis(1);
    if (Math.abs(leftValue) < .005)
      leftValue = 0;
    double rightValue = jR.getRawAxis(1);
    if (Math.abs(rightValue) < .005)
      rightValue = 0;

    left.set(leftValue);
    right.set(rightValue);    
  }

  public double encDistance()
  {
    double rightEnc, leftEnc;
    int nativeUnits1 = rearL.getSelectedSensorPosition(0);
    leftEnc = nativeUnits1 * .0046019424 * -1;
    int nativeUnits2 = rearR.getSelectedSensorPosition(0);
    rightEnc = nativeUnits2 * .0046019424;

    return (leftEnc + rightEnc) / 2;
  }

  public void drive(double speed)
  {
    robotBase.arcadeDrive(speed,0);
  }
}
