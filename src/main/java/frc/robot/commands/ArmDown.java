package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;


public class ArmDown extends CommandBase {
  private Arm m_arm;
  private int count, cycles;

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
      ARM_LOCATION = ARM_LOCATION - ARM_LOCATION/ARM_DOWN_MAX_CYCLES; //increments the location every cycle

    }

    m_arm.lower();
    
    // if(cycles < ARM_DOWN_MAX_CYCLES){
    //   m_arm.lower();
    // }else{
    //   m_arm.stop();
    // }

  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armDown stopped");
    m_arm.stop();
  }
  
}
