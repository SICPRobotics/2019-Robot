package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot 
{
 // Compressor c;
  
  DoubleSolenoid solo;
  Joystick j = new Joystick(0);
  WPI_TalonSRX talon1;

  @Override
  public void robotInit() 
  {
   // c = new Compressor(17);
    //c.setClosedLoopControl(true);
    solo = new DoubleSolenoid(0,1);
   // CameraServer.getInstance().startAutomaticCapture(0);
  /*try {
			UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(0);
		}
		catch (Exception e){
			System.out.println("failed camera 0"  + e);
    }
    try {
			UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(1);
		}
		catch (Exception e){
			System.out.println("failed camera 10" + e);
		}*/
    talon1 = new WPI_TalonSRX(4);
  }

  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopPeriodic() 
  {
    /*if(j.getRawAxis(1)>0)
      solo.set(DoubleSolenoid.Value.kForward);
    else if (j.getRawAxis(1)<0)
      solo.set(DoubleSolenoid.Value.kReverse);*/
    double speed = j.getRawAxis(1) * .6;
    System.out.println(speed);
      talon1.set(speed);

  }

  @Override
  public void testPeriodic() {
  }
}
