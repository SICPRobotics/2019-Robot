package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class DriveTrain extends PIDSubsystem 
{
  WPI_TalonSRX frontL, rearL, frontR, rearR;
  DifferentialDrive robotBase;
  SpeedControllerGroup left, right;

  ADXRS450_Gyro gyro; 

  public DriveTrain()
  {
    super("DriveTrain", 1, 2, 3);
    setSetpoint(0);

    frontL = new WPI_TalonSRX(RobotMap.k_frontL);
    rearL = new WPI_TalonSRX(RobotMap.k_rearL);
    rearL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);

    frontR = new WPI_TalonSRX(RobotMap.k_frontR);
    rearR = new WPI_TalonSRX(RobotMap.k_rearR);
    rearR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);

    left = new SpeedControllerGroup(frontL, rearL);
    right = new SpeedControllerGroup(frontR, rearR);

    robotBase = new DifferentialDrive(left, right);

    gyro = new ADXRS450_Gyro();
  }
  
  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
  
  @Override
  protected double returnPIDInput() 
  {
    return 0;
  }

  @Override
  protected void usePIDOutput(double output) 
  {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }

  public void cheesyDrive(Joystick j) //test
  {
    double scale = j.getRawAxis(3) * -1;
    scale = ((scale + 1) / 5) + .6;

    double moveValue = j.getRawAxis(1);
    double rotateValue = j.getRawAxis(0);

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
  
  public void tankDrive(Joystick jL, Joystick jR) //test
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



  public double resetGyro() //test
  {
    gyro.reset();
    return gyro.getAngle();
  }

  public double gyroAngle() //test
  {
    return gyro.getAngle();
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
}
