package frc.robot.commands;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;


public class ArmDown extends CommandBase {
  private Arm m_arm;
  private int count, cycles;
  DigitalInput bottomlimitSwitch = new DigitalInput(DOWNLIMIT_ID);


  public ArmDown(Arm subsystem) {
    addRequirements(subsystem);
    m_arm = subsystem;
  }

  @Override
  public void initialize() {
    count=0;
    cycles = 0;
  }

  @Override
  public void execute() {
    if( (++count)%25==0){
      cycles++;
      System.out.println("arm DOWN cycles " + cycles + " [" + ++count + "]");
    }
    if (bottomlimitSwitch.get()) {
      // We are going down and bottom limit is tripped so stop
      m_arm.stop();
    } else {
      // We are going down but bottom limit is not tripped so go at commanded speed
      m_arm.lower();
    }


  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armDown stopped");
    m_arm.stop();
  }
  
}
