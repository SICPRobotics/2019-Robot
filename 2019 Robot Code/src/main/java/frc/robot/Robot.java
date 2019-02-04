package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
  public static DriveTrain driveTrain;
  public static Elevator elevator;
  public static Beak beak;
  public static OI oi;

  Command autoCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  Joystick j1, j2;
  Button joystickButton7;
  boolean arcadeDriveStyle;
  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    driveTrain.resetGyro();

    elevator = new Elevator();
    beak = new Beak();
    oi = new OI();

    j1 = new Joystick(RobotMap.k_joystick1);
    j2 = new Joystick(RobotMap.k_joystick2);
   
    joystickButton7 = new JoystickButton(j1, 7);
    arcadeDriveStyle = true;

    chooser.setDefaultOption("Default Auto", new SetDrivePID(0, RobotMap.k_autoForward));
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", chooser);
  }

  @Override
  public void robotPeriodic() {}

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
    if (autoCommand != null) 
    {
      autoCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();

    if (joystickButton7.get()) 
      arcadeDriveStyle = !arcadeDriveStyle;
    if (arcadeDriveStyle)
      driveTrain.cheesyDrive(j1);
    else if (!arcadeDriveStyle)
      driveTrain.tankDrive(j1, j2);
  }

  @Override
  public void testPeriodic() {}
}
