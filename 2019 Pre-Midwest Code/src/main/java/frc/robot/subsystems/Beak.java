package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Beak extends Subsystem 
{
  DoubleSolenoid beakSolo;

  public Beak()
  {
    beakSolo = new DoubleSolenoid(0,1);
    //beakSolo.set(DoubleSolenoid.Value.kForward);
  }
  
  @Override
  public void initDefaultCommand() {}

  public void openBeak()
  {
    beakSolo.set(DoubleSolenoid.Value.kForward);
  }

  public void shutBeak()
  {
    beakSolo.set(DoubleSolenoid.Value.kReverse);
  }
}
