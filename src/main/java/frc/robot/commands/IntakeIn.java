package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.Intake;

public class IntakeIn extends CommandBase {

  private Intake m_intake;
  // private int count;

  public IntakeIn(Intake intake) {
    addRequirements(intake);
    m_intake = intake;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // if(++count > 25){
    //   System.out.println("intake In....");
    //   count=0;
    // }
    m_intake.in();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // System.out.println("intake In stopped");
    m_intake.stop();
  }
}
