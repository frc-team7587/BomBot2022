package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.pneumatics;

public class forward extends CommandBase {

  private pneumatics m_pneu;
  // private int count;

  public forward(pneumatics pneu) {
    addRequirements(pneu);
    m_pneu = pneu;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    m_pneu.forward();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_pneu.off();
  }
}
