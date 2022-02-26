package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    
    private final TalonSRX m_arm = new TalonSRX(ARM_TALON_ID);

    public Arm(){
        m_arm.configFactoryDefault();
    }
    
    public void raise() {
        double speed = SmartDashboard.getNumber("Arm-UP", ARM_UP_SPEED);
        m_arm.set(ControlMode.PercentOutput, -speed);    
    }
    public void lower() {
        double speed = SmartDashboard.getNumber("Arm-DOWN", ARM_DOWN_SPEED);
        m_arm.set(ControlMode.PercentOutput, speed);
    }

}
