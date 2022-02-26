package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;


public class ArmDown extends CommandBase {
  private Arm m_arm;
  private int count;

  public ArmDown(Arm subsystem) {
    addRequirements(subsystem);
    m_arm = subsystem;
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    
    m_arm.lower();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armDown stopped");
    m_arm.stop();
  }
  
}
