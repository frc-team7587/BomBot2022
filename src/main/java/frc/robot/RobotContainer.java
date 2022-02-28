package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.ArmDown;
import frc.robot.commands.ArmUp;
import frc.robot.commands.IntakeIn;
import frc.robot.commands.IntakeOut;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;

public class RobotContainer {
    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private final DriveTrain m_drive = new DriveTrain();
    private final Arm arm = new Arm();
    private final Intake intake = new Intake();

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

        // Auto-tuning parameters
        SmartDashboard.putNumber("t_Back_Vol", 0);
        SmartDashboard.putNumber("t_Back_Time", 0);
        SmartDashboard.putNumber("t_Turn_Vol", 0);
        SmartDashboard.putNumber("t_Turn_Time", 0);
        SmartDashboard.putNumber("t_Fwd_Vol", 0);
        SmartDashboard.putNumber("t_Fwd_Twist", 0);
        SmartDashboard.putNumber("t_Fwd_Time", 0);

        configureButtonBindings();

        m_drive.setDefaultCommand(
           // polarity based on normal (+) throttle
            new RunCommand(
                () -> m_drive.drive(
                    DRIVE_SPEED_MULTIPLIER * logi.getY() * logi.getThrottle(),
                    DRIVE_SPEED_MULTIPLIER * TWIST_DISCOUNT * -logi.getTwist() * logi.getThrottle()
                    ),
                m_drive)
            );  
    }

    private void configureButtonBindings() {

        // Intake 
        new JoystickButton(xbox, Button.kLeftBumper.value)
             .whileHeld( new IntakeIn(intake) );
             
        new JoystickButton(xbox, Button.kRightBumper.value)
            .whileHeld( new IntakeOut(intake) );

        // Arm
       new JoystickButton(xbox, Button.kY.value) // Y button
            .whileHeld(new ArmUp(arm));

       new JoystickButton(xbox, Button.kB.value)   // B button
           .whileHeld(new ArmDown(arm));

        /* NOTE: .whileHeld(() -> m_arm.lower(), m_arm) is bad; that one implements an InstantCommand thus
          doesn't give us chance to call m_arm.stop() when command ends, leaving the motor continue running even after button released!
          It's probably suitable for cases when we don't need to stop motor, or expect other buttons to end it, such as setting a constant voltage.
        */
             
    }
    public Command getAutonomousCommand() {

        String position = m_chooser.getSelected();
        if (position == null)
            return null;

        Command cmd = null;
        // tuning only
        // double tBackVol = SmartDashboard.getNumber("t_Back_Vol", 0);
        // double tBackTime = SmartDashboard.getNumber("t_Back_Time", 0);
        // double tTurnVol = SmartDashboard.getNumber("t_Turn_Vol", 0);
        // double tTurnTime = SmartDashboard.getNumber("t_Turn_Time", 0);
        // double tFwdVol = SmartDashboard.getNumber("t_Fwd_Vol", 0);
        // double tFwdTwist = SmartDashboard.getNumber("t_Fwd_Twist", 0);
        // double tFwdTime = SmartDashboard.getNumber("t_Fwd_Time", 0);
        
        switch (position) {
           case "Back-Out Only":
                cmd = initWait().andThen(
                maneuver(-0.5, 0, 0.7)).andThen(fullStop());
                break;
            case "T1-Left":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.65)).andThen(       // back out of tarmac
                        maneuver(0, 0.23, 0.5)).andThen(        // turn right
                        maneuver(0.4, -0.20, 2.68)).andThen(    // curve left to fender
                        // maneuver(tBackVol, 0, tBackTime)).andThen(
                        // maneuver(0, tTurnVol, tTurnTime)).andThen(
                        // maneuver(tFwdVol, tFwdTwist, tFwdTime)).andThen(
                        fullStop());
                break;
            case "T1-Center":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.75)).andThen(       // back out of tarmac
                        maneuver(0.5, 0, 1.38)).andThen(        // move straight to fender
                        fullStop());
                break;
            case "T1-Right":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.7)).andThen(
                        maneuver(0, -0.23, 0.5)).andThen(
                        maneuver(0.4, 0.20, 2.71)).andThen(
                        fullStop());
                break;
            case "T2-Left":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.65)).andThen(
                        maneuver(0, 0.23, 0.5)).andThen(
                        maneuver(0.4, -0.20, 2.68)).andThen(
                        fullStop());
                break;
            case "T2-Center":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.75)).andThen(
                        maneuver(0.5, 0, 1.4)).andThen(
                        fullStop());
                break;
            case "T2-Right":
                cmd = initWait().andThen(
                        maneuver(-0.5, 0, 0.7)).andThen(
                        maneuver(0, -0.23, 0.5)).andThen(
                        maneuver(0.4, 0.20, 2.71)).andThen(
                        fullStop());
                break;
            default:
                return null;
        }
        
        cmd = cmd.andThen(
                  new IntakeOut(intake).withTimeout(2)    // shoot out cargo
                ).andThen(
                  maneuver(-0.6, 0, 1.5)          // back away from fender
                );

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
