package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.SoftLimitDirection;

import static frc.robot.Constants.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    
    private final CANSparkMax m_spark = new CANSparkMax(ARM_SPARK_ID, MotorType.kBrushless);

    public void raise() {
        m_spark.set(-ARM_MAX_SPEED);
    }

    public void lower() {
        m_spark.set(ARM_MAX_SPEED);
    }

    public void stop() {
        m_spark.set(0);
    }

    public void reset() {
        
        m_spark.restoreFactoryDefaults();

        m_spark.enableSoftLimit(SoftLimitDirection.kForward, true);
        m_spark.enableSoftLimit(SoftLimitDirection.kReverse, true);
        m_spark.setSoftLimit(SoftLimitDirection.kForward, ARM_SOFT_LIMIT_FWD);
        m_spark.setSoftLimit(SoftLimitDirection.kReverse, ARM_SOFT_LIMIT_BKW);
    }

    @Override
    public void periodic() {

    }
}
