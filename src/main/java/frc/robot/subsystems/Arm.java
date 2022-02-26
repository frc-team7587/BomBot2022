package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import static frc.robot.Constants.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    
    // private final CANSparkMax m_spark = new CANSparkMax(ARM_SPARK_ID, MotorType.kBrushless);

    TalonSRX m_arm = new TalonSRX(ARM_TALON_ID);

    public void raise() {
        m_arm.set(ControlMode.PercentOutput, -ARM_MAX_SPEED);    
    }
    public void lower() {
        m_arm.set(ControlMode.PercentOutput, ARM_MAX_SPEED *.3);    
    }

    public void stop() {
        m_arm.set(ControlMode.PercentOutput, 0);    
    }

    @Override
    public void periodic() {

    }
}
