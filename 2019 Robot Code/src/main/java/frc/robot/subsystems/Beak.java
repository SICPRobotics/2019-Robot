package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class Beak extends Subsystem 
{
  DoubleSolenoid beakSolo;

  public Beak()
  {
    beakSolo = new DoubleSolenoid(RobotMap.k_beakSolo1, RobotMap.k_beakSolo2);
  }
  
  @Override
  public void initDefaultCommand() 
  {

  }

  public void openBeak()
  {
    beakSolo.set(DoubleSolenoid.Value.kForward);
  }

  public void shutBeak()
  {
    beakSolo.set(DoubleSolenoid.Value.kReverse);
  }
}
