/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class DriveTrain extends SubsystemBase {

  private final WPI_VictorSPX left1 = new WPI_VictorSPX(DRIVE_VICTOR_LEFT1);
  private final WPI_VictorSPX left2 = new WPI_VictorSPX(DRIVE_VICTOR_LEFT2);
  
  private final WPI_VictorSPX right1 = new WPI_VictorSPX(DRIVE_VICTOR_RIGHT1);
  private final WPI_VictorSPX right2 = new WPI_VictorSPX(DRIVE_VICTOR_RIGHT2);

  private final MotorControllerGroup left  = new MotorControllerGroup(left1, left2);
  private final MotorControllerGroup right  = new MotorControllerGroup(right1, right2);

  private final DifferentialDrive m_drive = new DifferentialDrive(left, right);

  public DriveTrain(){
    
    left1.configFactoryDefault();
    left2.configFactoryDefault();
    right1.configFactoryDefault();
    right2.configFactoryDefault();

    this.left.setInverted(false);
    this.right.setInverted(true);
  }

  public void drive(double speed, double rotation) {
    m_drive.arcadeDrive(speed, rotation);
  }

  public void stop() {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    super.setDefaultCommand(defaultCommand);
  }

  // @Override  public void periodic() {  }
}
