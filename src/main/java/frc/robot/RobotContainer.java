package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

public class RobotContainer {
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private final DriveTrain m_drive = new DriveTrain();
    private final Arm m_arm = new Arm();
    private final Intake m_intake = new Intake();

    final XboxController xbox = new XboxController(XBOX_CTRL_PORT);
    final Joystick logi = new Joystick(LOGIJOY_PORT);

    public RobotContainer() {

    // set up possible auto start position
    m_chooser.setDefaultOption("None", null);
    m_chooser.addOption("Back-Out Only","Back-Out Only");
    m_chooser.addOption("T1-Left","T1-Left");
    m_chooser.addOption("T1-Center","T1-Center");
    m_chooser.addOption("T1-Right","T1-Right");
    m_chooser.addOption("T2-Left", "T2-Left");
    m_chooser.addOption("T2-Center","T2-Center");
    m_chooser.addOption("T2-Right", "T2-Right");
    SmartDashboard.putData(m_chooser);

    //init delay of auto run
    SmartDashboard.putNumber("Auto Init Delay: ", 1);
    SmartDashboard.putNumber("Auto Command Pause: ", 0.5);

    // Arm speeds
    SmartDashboard.putNumber("Arm-UP", ARM_UP_SPEED);
    SmartDashboard.putNumber("Arm-DOWN", ARM_DOWN_SPEED);

        configureButtonBindings();

        m_drive.setDefaultCommand(
                new RunCommand(
                        () -> m_drive.drive(
                                DRIVE_SPEED_MULTIPLIER * logi.getY() * logi.getThrottle(),
                                DRIVE_SPEED_MULTIPLIER * 0.75 * -logi.getTwist() * Math.abs(logi.getThrottle())),
                        m_drive));

    }

    private void configureButtonBindings() {
        // Intake 
        new JoystickButton(xbox, Button.kLeftBumper.value)
             .whileHeld(() -> m_intake.in(), m_intake);

        new JoystickButton(xbox, Button.kRightBumper.value)
             .whileHeld(() -> m_intake.out(), m_intake);

        // Arm
       new JoystickButton(xbox, Button.kY.value) // Y button
            .whileHeld(()->m_arm.raise(), m_arm);

       new JoystickButton(xbox, Button.kB.value)   // B button
           .whileHeld(() -> m_arm.lower(), m_arm);
    }

    public Command getAutonomousCommand() {

        Command cmd = null;

        String position = m_chooser.getSelected();
        if (position == null)
            return null;

        switch (position) {
           case "Backout Only":
                cmd = initWait().andThen(
                maneuver(-0.5, 0, 0.7)).andThen(fullStop());
                break;
            case "T1-Left":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.65)).andThen(
                                maneuver(0, 0.23, 0.5))
                        .andThen(
                                maneuver(0.4, -0.20, 2.68))
                        .andThen(
                                fullStop());
                break;
            case "T1-Center":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.75)).andThen(
                                maneuver(0.5, 0, 1.38))
                        .andThen(
                                fullStop());
                break;
            case "T1-Right":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.7)).andThen(
                                maneuver(0, -0.23, 0.5))
                        .andThen(
                                maneuver(0.4, 0.20, 2.71))
                        .andThen(
                                fullStop());
                break;
            case "T2-Left":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.65)).andThen(
                                maneuver(0, 0.23, 0.5))
                        .andThen(
                                maneuver(0.4, -0.20, 2.68))
                        .andThen(
                                fullStop());
                break;
            case "T2-Center":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.75)).andThen(
                                maneuver(0.5, 0, 1.4))
                        .andThen(
                                fullStop());
                break;
            case "T2-Right":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.7)).andThen(
                                maneuver(0, -0.23, 0.5))
                        .andThen(
                                maneuver(0.4, 0.20, 2.71))
                        .andThen(
                                fullStop());
                break;
            default:
                return null;
        }
        return cmd;
    }

    private Command maneuver(double fwd, double rot, double elapse){
        return new RunCommand(
          () -> m_drive.drive(fwd, rot), m_drive)
          .withTimeout(elapse)
          .andThen(pause());
      }
    
      private Command fullStop(){
        return maneuver(0,0,0);
      }
    
      private Command pause(double seconds){
        return new WaitCommand(seconds);
      }
    
      private Command initWait(){
        double s = SmartDashboard.getNumber("Auto Init Delay: ", 1);
        return pause(s);
      }
      
      private Command pause(){
        double s = SmartDashboard.getNumber("Auto Command Pause: ", 1);
        return pause(s);
      }
}
