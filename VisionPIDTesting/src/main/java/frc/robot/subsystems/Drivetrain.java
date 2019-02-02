package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * Add your docs here.
 */
public class Drivetrain extends PIDSubsystem {
  /**
   * Add your docs here.
   */
  NetworkTableEntry centerVal;
   WPI_TalonSRX
  public Drivetrain() {
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("/SmartDashboard");
    centerVal = inst.getEntry("centerVal");
    SmartDashboard.putNumber("Output from vision", entry.getDouble(-10));

    // Intert a subsystem name and PID values here
    super("Drivetrain", 0.1, 0, 0); //need to tune these values
    setSetpoint(); //NEED TO FIGURE OUT WHAT THE CENTER IS
    enable();
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    centerVal = inst.getEntry("centerVal");
  }

  @Override
  protected double returnPIDInput() {
    SmartDashboard.putNumber("Output from vision", entry.getDouble(-10));
    
    // Return your input value for the PID loop
    // e.g. a sensor, like a potentiometer:
    // yourPot.getAverageVoltage() / kYourMaxVoltage;
    return centerVal.getDouble(0);
  }

  @Override
  protected void usePIDOutput(double output) {
    // Use output to drive your system, like a motor
    // e.g. yourMotor.set(output);
  }
}
