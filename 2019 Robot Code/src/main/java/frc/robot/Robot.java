package frc.robot;

import java.net.InetAddress;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.Beak;
import frc.robot.subsystems.Claws;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Parallelogram;

public class Robot extends TimedRobot 
{
  //public static DriveTrain driveTrain = new DriveTrain();
  //public static Elevator elevator = new Elevator();
  //public static Beak beak = new Beak();
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static Beak beak;
  public static Claws claws;
  public static Parallelogram parallel;
  public static OI oi;
  public static Joystick j1;
  Compressor c;

  Command autoCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  boolean vision;

  InetAddress piAddress;

  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();

    //Connect to the Pi so that we can ping it later
    try {
      piAddress = InetAddress.getByName("10.58.22.48");
    } catch (Exception e) {
      e.printStackTrace();
    }
   
    elevator = new Elevator();
    beak = new Beak();
    claws = new Claws();
    parallel = new Parallelogram();
    oi = new OI(); 
    j1 = new Joystick(0);
    //c = new Compressor(0);
    //c.setClosedLoopControl(true);
    
    chooser.setDefaultOption("Auto Drive Off", new SandStorm());
    chooser.addOption("Do Nothing", new DoNothing());
    SmartDashboard.putData("Auto mode", chooser);

    //driveTrain.enable();

    //We don't need to do this; the Pi will be the camera server for USB and IP cameras.
    //Cameras will be streamed directly off of the Pi. Go to http://frcvision.local while connected to the robot to configure cameras.
    /*try {
			UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(0);
		}
		catch (Exception e){
			System.out.println("failed camera 0"  + e);
    }
    try {
			UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(0);
		}
		catch (Exception e){
			System.out.println("failed camera 10" + e);
		}*/
  }

  @Override
  public void robotPeriodic() {
    if (SmartDashboard.getBoolean("Running Vision", false)){
      driveTrain.enable();
    } else {
      driveTrain.disable();
    }
    SmartDashboard.putString("Vision Status: ", driveTrain.status);
    try {
      SmartDashboard.putBoolean("Pi Ping", piAddress.isReachable(1000));
    } catch( Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void disabledInit() 
  {
    driveTrain.disable();
    //set everything to 0/turn stuff off/cancel all commands
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() 
  {
    //autoCommand = chooser.getSelected();

    if (autoCommand != null) 
    {
      autoCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
    if (!autoCommand.isRunning())
      driveTrain.cheesyDrive(j1);
    /*if(!vision)
    {
      driveTrain.cheesyDrive(j1);
    }
    else if(vision) {}*/
  }

  @Override
  public void teleopInit() 
  {
    if (autoCommand != null) 
    {
      autoCommand.cancel();
    }
    //driveTrain.enable();
    //new StartVisionPID();
  }

  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();
    //driveTrain.cheesyDrive(j1);
    driveTrain.drive();
    Scheduler.getInstance().run();    
    if(!driveTrain.isEnabled())
    {
      driveTrain.cheesyDrive(j1);
    }
  }

  @Override
  public void testPeriodic() {}
}
