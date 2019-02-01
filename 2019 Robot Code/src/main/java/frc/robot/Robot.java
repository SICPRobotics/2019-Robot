package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.SetDrivePID;
import frc.robot.subsystems.Beak;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Elevator;

public class Robot extends TimedRobot 
{
  public static DriveTrain driveTrain = new DriveTrain();
  public static Elevator elevator = new Elevator();
  public static Beak beak = new Beak();
  public static OI oi;

  Command autoCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  Joystick j1, j2;
  
  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    driveTrain.resetGyro();
    
    oi = new OI();

    j1 = new Joystick(RobotMap.k_joystick1);
    j2 = new Joystick(RobotMap.k_joystick2);
   
    chooser.setDefaultOption("Default Auto", new SetDrivePID(0, RobotMap.k_autoForward));
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", chooser);
  }

  @Override
  public void robotPeriodic() 
  {
    //TODO: any constant updates we want to record
  }

  @Override
  public void disabledInit() 
  {
    //set everything to 0/turn stuff off/cancel all commands
  }

  @Override
  public void disabledPeriodic() {}

  @Override
  public void autonomousInit() 
  {
    autoCommand = chooser.getSelected();

    if (autoCommand != null) 
    {
      autoCommand.start();
    }
  }

  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() 
  {
    if (autoCommand != null) {
      autoCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();
    driveTrain.cheesyDrive(j1);
    //driveTrain.calibrateTalons(j1);
  }

  @Override
  public void testPeriodic() {}
}
