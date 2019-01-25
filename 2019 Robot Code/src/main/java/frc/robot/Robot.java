package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveTrain;

public class Robot extends TimedRobot 
{
  public static DriveTrain driveTrain = new DriveTrain();
  public static OI oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  Joystick j1, j2;
  
  @Override
  public void robotInit() 
  {
    driveTrain = new DriveTrain();
    driveTrain.resetGyro();

    oi = new OI();

    j1 = new Joystick(RobotMap.k_joystick1);
    j2 = new Joystick(RobotMap.k_joystick2);
   
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
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
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
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
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();
    
    driveTrain.cheesyDrive(j1);
  }

  @Override
  public void testPeriodic() {}
}
