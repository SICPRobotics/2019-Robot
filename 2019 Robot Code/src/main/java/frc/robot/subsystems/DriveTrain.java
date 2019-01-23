package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

public class DriveTrain extends Subsystem 
{
  WPI_TalonSRX frontL, rearL, frontR, rearR;
  DifferentialDrive robotBase;
  SpeedControllerGroup left, right;

  public DriveTrain()
  {
    frontL = new WPI_TalonSRX(RobotMap.k_frontL);
    rearL = new WPI_TalonSRX(RobotMap.k_rearL);
    frontR = new WPI_TalonSRX(RobotMap.k_frontR);
    rearR = new WPI_TalonSRX(RobotMap.k_rearR);

    left = new SpeedControllerGroup(frontL, rearL);
    right = new SpeedControllerGroup(frontR, rearR);

    robotBase = new DifferentialDrive(left, right);
  }
  
  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void cheesyDrive(Joystick j)
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
}
