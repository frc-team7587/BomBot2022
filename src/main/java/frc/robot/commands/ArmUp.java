package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.*;
import static frc.robot.Constants.*;

import frc.robot.subsystems.*;


public class ArmUp extends CommandBase {
  private Arm m_arm;
  private int count;

  private DigitalInput toplimitSwitch = new DigitalInput(UPLIMIT_ID);

  public ArmUp(Arm subsystem) {
    addRequirements(subsystem);
    m_arm = subsystem;
  }

  @Override
  public void initialize() {
    count = 0;
  }

  @Override
  public void execute() {
    if( (++count)%20==0){
      System.out.println("arm UP cycles [" + ++count + "]");
    }
    if (toplimitSwitch.get()) {
      m_arm.stop();
    } else {
      m_arm.raise();
    }    
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armUp stopped");
    m_arm.stop();
  }



}
