package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.*;
import static frc.robot.Constants.*;

import frc.robot.subsystems.*;


public class ArmUp extends CommandBase {
  DigitalInput toplimitSwitch = new DigitalInput(UPLIMIT_ID);
  private Arm m_arm;
  private int count, cycles;

  public ArmUp(Arm subsystem) {
    addRequirements(subsystem);
    m_arm = subsystem;
  }

  @Override
  public void initialize() {
    count = 0;
    cycles = 0;
  }

  @Override
  public void execute() {
    if( (++count)%25==0){
      cycles++;
      System.out.println("arm UP cycles " + cycles + " [" + ++count + "]");
    }
    if (toplimitSwitch.get()) {
      // We are going up and top limit is tripped so stop
      m_arm.stop();
    } else {
      // We are going up but top limit is not tripped so go at commanded speed
      m_arm.raise();
    }    
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armUp stopped");
    m_arm.stop();
  }



}
