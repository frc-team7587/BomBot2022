package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.*;
import static frc.robot.Constants.*;

import frc.robot.subsystems.*;

public class ArmUp extends CommandBase {
  
  private Arm m_arm;
  private int count;

  public ArmUp(Arm subsystem) {
    addRequirements(subsystem);
    m_arm = subsystem;
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    if(++count > 25){
      System.out.println("armUP...." + ARM_MAX_SPEED);
      count=0;
    }
    
    m_arm.raise();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armUp stopped");
    m_arm.stop();
  }

}
