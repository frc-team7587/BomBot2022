/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.Intake;

public class IntakeIn extends WaitUntilCommand {

  private Intake m_intake;
  private int count;

  public IntakeIn(Intake intake, BooleanSupplier btnState) {
    super(btnState);
    addRequirements(intake);
    m_intake = intake;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(++count > 25){
      System.out.println("intake In....");
      count=0;
    }
    m_intake.in();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("intake In stopped");
    m_intake.stop();
  }
}
