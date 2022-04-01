package frc.robot.subsystems;

import static frc.robot.Constants.*;

  import com.ctre.phoenix.motorcontrol.ControlMode;
  import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  
  private TalonSRX m_intake = new TalonSRX(INTAKE_TALON_ID);

  public Intake(){
    m_intake.configFactoryDefault();
  }

  public void in() {
    m_intake.set(ControlMode.PercentOutput, -INTAKE_MAX_SPEED_OUT);
  }

  public void out() {
    m_intake.set(ControlMode.PercentOutput, INTAKE_MAX_SPEED_IN);
  }

  public void stop() {
    m_intake.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
  }
}