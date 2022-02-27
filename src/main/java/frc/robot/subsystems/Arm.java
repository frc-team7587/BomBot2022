package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {
    
    private final TalonSRX m_arm = new TalonSRX(ARM_TALON_ID);

    public Arm(){
        
        // m_arm.configFactoryDefault();
    }
    private int count=0;
    private int cycles=0;
    public void raise() {
        if(++count > 20){
            System.out.println("armUP...." + ARM_UP_SPEED + "[" + ++cycles + "]");
            count=0;
          }
      
        m_arm.set(ControlMode.PercentOutput, ARM_UP_SPEED);     // up!
    }
    public void lower() {
        if(++count > 20){
            System.out.println("arm DOWN...." + ARM_DOWN_SPEED);
            count=0;
          }

        m_arm.set(ControlMode.PercentOutput, -ARM_DOWN_SPEED);    // downn
    }

    public void stop() {
        m_arm.set(ControlMode.PercentOutput, 0);    
    }

    @Override
    public void periodic() {

    }

    

}
