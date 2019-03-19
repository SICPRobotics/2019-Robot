package frc.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot 
{
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static Beak beak;
  public static Parallelogram parallel;
  public static OI oi;
  public static Joystick j1;
  Compressor c;

  PWMTalonSRX leds;

  Command autoCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  public static boolean invert = false;
  public static boolean open = true;
  public double ledSpeed;

  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    elevator = new Elevator();
    beak = new Beak();
    parallel = new Parallelogram();
    j1 = new Joystick(0);
    oi = new OI(); 
    c = new Compressor();
    c.setClosedLoopControl(true);
    
    leds = new PWMTalonSRX(0);
    ledSpeed = -.65;
    //chooser.setDefaultOption("Rainbow", new Leds(.88);
    //chooser.addOption("Do Nothing", new DoNothing());
    //SmartDashboard.putData("Sandstorm", chooser);
    SmartDashboard.putBoolean("Beak Open?", open);
   try {
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
    }
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
    /*autoCommand =
     chooser.getSelected();

    if (autoCommand != null) 
    {
      autoCommand.start();
    }*/
  }

  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
   // if (!autoCommand.isRunning())
    driveTrain.cheesyDrive(j1.getRawAxis(1),j1.getRawAxis(0),j1.getRawAxis(3)*-1);
    leds.set(ledSpeed);
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
    if(!invert)
       driveTrain.cheesyDrive(j1.getRawAxis(1),j1.getRawAxis(0),j1.getRawAxis(3)*-1);
    else if(invert)
    {
      driveTrain.cheesyDrive(j1.getRawAxis(1)*-1,j1.getRawAxis(0),j1.getRawAxis(3)*-1);
      //System.out.println("running inverted");
    }
    leds.set(ledSpeed);
    SmartDashboard.putBoolean("Beak Open?", open);
    SmartDashboard.putBoolean("Inverted?", invert);
   
  }

  @Override
  public void testPeriodic() {}
}
