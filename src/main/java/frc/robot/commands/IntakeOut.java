package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.Intake;

public class IntakeOut extends CommandBase {

  private Intake m_intake;
  private int count;

  public IntakeOut(Intake intake) {
    addRequirements(intake);
    m_intake = intake;
  }

  @Override
  public void initialize() {        
  }

  @Override
  public void execute() {
    if(++count > 25){
      System.out.println("intake Out....");
      count=0;
    }
    m_intake.out();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("intake Out stopped");
    m_intake.stop();
  }
}
