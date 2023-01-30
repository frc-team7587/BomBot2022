package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import static frc.robot.Constants.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class neo extends SubsystemBase {
    private final CANSparkMax neo = new CANSparkMax(3, MotorType.kBrushless);

    public neo() {
        neo.restoreFactoryDefaults();
    }

    public void restoreFactoryDefaults() {
        neo.restoreFactoryDefaults();
    }

    public void raise() {
        neo.set(0.5);
    }

    public void lower() {
        neo.set(-0.5);
    }

    public void stop() {
        neo.set(0);
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Encoder Position: ", encoder.getPosition());
    }
}
