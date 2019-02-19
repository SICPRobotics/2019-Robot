package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.UrlReader;

public class DriveTrain extends PIDSubsystem 
{

  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_BLACK = "\u001B[30m";
  public static final String ANSI_RED = "\u001B[31m";
  public static final String ANSI_GREEN = "\u001B[32m";
  public static final String ANSI_YELLOW = "\u001B[33m";
  public static final String ANSI_BLUE = "\u001B[34m";
  public static final String ANSI_PURPLE = "\u001B[35m";
  public static final String ANSI_CYAN = "\u001B[36m";
  public static final String ANSI_WHITE = "\u001B[37m";

  WPI_TalonSRX frontL, rearL, frontR, rearR;
  DifferentialDrive robotBase;
  SpeedControllerGroup left, right;
  ADXRS450_Gyro gyro;

  UrlReader urlReader;

  int count = 0;
  double camWidth = 320;

  public String status;

  private boolean persist;

  private boolean isEnabled;

  public DriveTrain()
  {
    super("DriveTrain", 1, 0, 0, .00);
    setSetpoint(160);

    frontR = new WPI_TalonSRX(3);
    /*
    frontR.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    frontR.setSensorPhase(true);
    frontR.selectProfileSlot(0, 0);
    frontR.config_kF(0,.3); //example: 
    frontR.config_kP(0,0.2);//.0682) example: .2
    frontR.config_kI(0,.00);
    frontR.config_kD(0,0);
    frontR.configMotionCruiseVelocity(3000,100); //example: 15000
    frontR.configMotionAcceleration(2500,100); //example: 6000
    frontR.setSelectedSensorPosition(0);
*/
    rearR = new WPI_TalonSRX(1);
   // rearR.follow(frontR);
    
    frontL = new WPI_TalonSRX(2);
    frontL.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,0,0);
    frontL.setSensorPhase(false);

    rearL = new WPI_TalonSRX(0);
   // rearL.follow(frontL);
    left = new SpeedControllerGroup(frontL, rearL);
    right = new SpeedControllerGroup(frontR, rearR);

    //robotBase = new DifferentialDrive(left, right);

    //gyro = new ADXRS450_Gyro();
    status = "starting";

    persist = SmartDashboard.getBoolean("Persist in Vision: ", false);

    readUrl();
  }

  public void readUrl() {
    try {
      urlReader = new UrlReader();
    } catch (Exception e) {
      System.out.println("Reading the URL failed. Retrying soon.");
      e.printStackTrace();
      new java.util.Timer().schedule(
        new java.util.TimerTask() {
          @Override
          public void run() {
            readUrl();
          }
        }, 2000);
    }
  }
  
  @Override
  public void initDefaultCommand() {}

  @Override
  public void enable() {
    if (!isEnabled){
      super.enable();
      SmartDashboard.putBoolean("Running Vision", true);
      isEnabled = true;
      System.out.println("Enabled");
    }
  }

  @Override
  public void disable() {
    super.disable();
    SmartDashboard.putBoolean("Running Vision", false);
    isEnabled = false;
    status = status == "Operating" ? "Vision Disabled" : status;
  }

  @Override
  protected double returnPIDInput() 
  {
    //Persist: if we lose track of the target, should we continue looking for it?
    persist = SmartDashboard.getBoolean("Persist in Vision: ", persist);

    double pidIn;
    //System.out.println("returnPIDInput");
    try {
      //pidIn = urlReader.getCurrentData().get("center").getDouble("x");
      //System.out.println(pidIn);
      pidIn = -0.0;
      //System.out.println("pidIn Set");
      System.out.println(urlReader.getCurrentData().toString());
      status = "Operating";
    } catch (Exception e) {
      System.out.println("URL Read Failed");
      e.printStackTrace();
      status = "Failed to Read URL";
      pidIn = -0.0;
    }
    //System.out.println("PidIn" + pidIn);
    if (new Double(pidIn).equals(new Double(-0.0))) {
      //System.out.println("Lost track of hatch. Disabling.");
      
      if (!persist) {
        disable();
      }

      status = status == "Failed to Read URL" ? status : "No Hatch Found";
    }
      

    
    SmartDashboard.putBoolean("Persist in Vision: ", persist);
    return pidIn;
    
  }

  @Override
  protected void usePIDOutput(double output) 
  {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
   // robotBase.tankDrive(0.5 + output, 0.5 - output);
   if (isEnabled){
      double driveSpeed = SmartDashboard.getNumber("Vision Drive Speed", -0.0);
      if (new Double(driveSpeed).equals(new Double(-0.0))){
        SmartDashboard.putNumber("Vision Drive Speed", 0.2);
        driveSpeed = 0.2;
      }
      left.set((output * -0.2) - driveSpeed);
      right.set((output * -0.2) + driveSpeed);
   }
    //if (count++%100 ==0)
      //System.out.println("PID Output: " + output);
  }
  
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

  public void drive()
  {
    robotBase.tankDrive(.3, .3);
  }
  
  //Returns the rotation based on the FoV of the camera. Does not take into account current gyro rotation. 
  //0deg is the current robot rotation.
  public double toRotation(double pixelsX){
    
    double fov = 110.0;
    double frac = pixelsX / camWidth;
    
    return (fov * frac) - (fov / 2);
  }

  public boolean isEnabled() {
    return isEnabled;
  }
}
