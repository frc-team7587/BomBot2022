package frc.robot.subsystems;

import static frc.robot.Constants.*;

  import com.ctre.phoenix.motorcontrol.ControlMode;
  import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  
  TalonSRX intake = new TalonSRX(INTAKE_TALON_ID);

  public void in() {
    intake.set(ControlMode.PercentOutput, -INTAKE_MAX_SPEED);
  }

  public void out() {
    intake.set(ControlMode.PercentOutput, INTAKE_MAX_SPEED * 1.5);
  }

  public void stop() {
    intake.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
  }
}