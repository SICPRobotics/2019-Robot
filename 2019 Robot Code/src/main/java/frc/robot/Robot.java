package frc.robot;

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

public class Robot extends TimedRobot 
{
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static Beak beak;
  public static Claws claws;
  public static OI oi;

  public static Joystick j1;
  Compressor c;

  Command autoCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  boolean vision;

  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    //driveTrain.resetGyro();
    elevator = new Elevator();
    beak = new Beak();
    claws = new Claws();
    //c = new Compressor(0);
    //c.setClosedLoopControl(true);
    //INSERT CAMERA CODE HERE
    j1 = new Joystick(0);
    oi = new OI(); 
    chooser.setDefaultOption("Auto Drive Off", new DriveOffPlatform());
    chooser.addOption("Do Nothing", new DoNothing());
    SmartDashboard.putData("Auto mode", chooser);

    vision = false;
    SmartDashboard.putBoolean("Running Vision", vision);

    /*try {
			UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(0);
		}
		catch (Exception e){
			System.out.println("failed camera 0" + e);
    }
    try {
			UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(0);
		}
		catch (Exception e){
			System.out.println("failed camera 0" + e);
		}*/
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void disabledInit() 
  {
    driveTrain.disable();
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
  }

  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();    
    if(!vision)
    {
      driveTrain.cheesyDrive(j1);
    }
    else if(vision) {}
  }

  @Override
  public void testPeriodic() {}
}
