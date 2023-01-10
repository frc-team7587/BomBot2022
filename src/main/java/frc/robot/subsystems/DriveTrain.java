package frc.robot.subsystems;

import static frc.robot.Constants.*;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;


public class DriveTrain extends SubsystemBase {

  private final WPI_VictorSPX left1 = new WPI_VictorSPX(DRIVE_VICTOR_LEFT1);
  private final WPI_VictorSPX left2 = new WPI_VictorSPX(DRIVE_VICTOR_LEFT2);

  private final WPI_VictorSPX right1 = new WPI_VictorSPX(DRIVE_VICTOR_RIGHT1);
  private final WPI_VictorSPX right2 = new WPI_VictorSPX(DRIVE_VICTOR_RIGHT2);

  private final MotorControllerGroup left = new MotorControllerGroup(left1, left2);
  private final MotorControllerGroup right = new MotorControllerGroup(right1, right2);

  private final DifferentialDrive m_drive = new DifferentialDrive(left, right);
  final Joystick logi = new Joystick(LOGIJOY_PORT);


  public DriveTrain() {

    this.left1.configFactoryDefault();
    this.left2.configFactoryDefault();
    this.right1.configFactoryDefault();
    this.right2.configFactoryDefault();

    this.right.setInverted(true);

  }

  public void drive(double speed, double rotation) {
    System.out.println("drive");
    while (logi.getRawButton(1)) {
      NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
      NetworkTableEntry tx = table.getEntry("tx");
      double x = tx.getDouble(0.0);

      // post to smart dashboard periodically
      SmartDashboard.putNumber("LimelightX", x);
      System.out.println(x);
      if (x > 0) {
        if (x < 12) {
        m_drive.arcadeDrive(0, 0.05*x);
        } else if (x < 3) {
        m_drive.arcadeDrive(0, 0.2);
        } else {
        m_drive.arcadeDrive(0, 0.6);
        }
        System.out.println("turned right");
      } else if (x < 0) {
        if (x < 12) {
          m_drive.arcadeDrive(0, 0.05*x);
          }  else if (x < 3) {
          m_drive.arcadeDrive(0, -0.2);
          } else {
          m_drive.arcadeDrive(0, -0.6);
          }        
          System.out.println("turned left");
      }
    }
    m_drive.arcadeDrive(speed, rotation);
  }
  

  // public void left() {
  //   m_drive.arcadeDrive(0, -0.1);
  //   System.out.println("turned left");
  // }

  // public void right() {
  //   m_drive.arcadeDrive(0, 0.1);
  //   System.out.println("turned left");

  // }

  public void stop() {
    m_drive.arcadeDrive(0, 0);
  }

  @Override
  public void setDefaultCommand(Command defaultCommand) {
    super.setDefaultCommand(defaultCommand);
  }

  @Override
  public void periodic() {

  }
}
