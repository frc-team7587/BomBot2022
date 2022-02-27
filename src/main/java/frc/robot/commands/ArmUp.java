package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import static frc.robot.Constants.*;

import frc.robot.subsystems.*;

public class ArmUp extends CommandBase {
  
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
      ARM_LOCATION = ARM_LOCATION + ARM_LOCATION/ARM_UP_MAX_CYCLES; //increments the location every cycle
    }
    m_arm.raise();

    // if(cycles < ARM_UP_MAX_CYCLES){
    //   m_arm.raise();
    // }else{
    //   m_arm.stop();
    // }
    
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println("armUp stopped");
    m_arm.stop();
  }



}
