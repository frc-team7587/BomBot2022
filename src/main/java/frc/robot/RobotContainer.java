package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.*;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import static frc.robot.Constants.*;
import frc.robot.commands.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.CvSink;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;


public class RobotContainer {

    private final SendableChooser<String> m_chooser = new SendableChooser<>();
    private final DriveTrain m_drive = new DriveTrain();
    // private final Arm arm = new Arm();
    private final Intake intake = new Intake();
    private final neo Neo = new neo();
    private final pneumatics pnue = new pneumatics();


    final XboxController xbox = new XboxController(XBOX_CTRL_PORT);
    final Joystick logi = new Joystick(LOGIJOY_PORT);

    public RobotContainer() {

        UsbCamera camera = CameraServer.startAutomaticCapture();
        // CvSink cvSink = CameraServer.getVideo();
        // CvSource outputstream = CameraServer.putVideo("Blur", 640, 480);

        // set up possible auto start position
        m_chooser.setDefaultOption("Back-Out Only","Back-Out Only");
        m_chooser.addOption("None", null);
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
        
    

        // m_drive.setDefaultCommand(
           // polarity based on normal (+) throttle
           
            // new RunCommand(
            //     () -> m_drive.drive(
            //         DRIVE_SPEED_MULTIPLIER * logi.getY() * logi.getThrottle(),
            //         DRIVE_SPEED_MULTIPLIER * TWIST_DISCOUNT * -logi.getTwist() * logi.getThrottle()
            //         ),
            //     m_drive)
            // );  
           
    }

    private void configureButtonBindings() {

        // Intake 
    //     new JoystickButton(xbox, Button.kLeftBumper.value)
    //          .whileHeld( new IntakeIn(intake) );
             
    //     new JoystickButton(xbox, Button.kRightBumper.value)
    //         .whileHeld( new IntakeOut(intake) );

    //     // Arm
       new JoystickButton(xbox, Button.kX.value) // Y button
            .whileHeld(new off(pnue));

       new JoystickButton(xbox, Button.kB.value)   // B button
           .whileHeld(new neoRunDown(Neo));
        
        new JoystickButton(xbox, Button.kA.value)
            .whenPressed(new backward(pnue));

            new JoystickButton(xbox, Button.kY.value)
            .whenPressed(new forward(pnue));

        //    new JoystickButton(logi, 1) // Y button
        //    .whileHeld(new stabilizer(m_drive));
          
    //     new JoystickButton(xbox, Button.kY.value) // Y button
    //        .whileHeld(new ArmUp(arm));

    //   new JoystickButton(xbox, Button.kB.value)   // B button
    //       .whileHeld(new ArmDown(arm));

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
        double tBackVol = SmartDashboard.getNumber("t_Back_Vol", 0);
        double tBackTime = SmartDashboard.getNumber("t_Back_Time", 0);
        double tTurnVol = SmartDashboard.getNumber("t_Turn_Vol", 0);
        double tTurnTime = SmartDashboard.getNumber("t_Turn_Time", 0);
        double tFwdVol = SmartDashboard.getNumber("t_Fwd_Vol", 0);
        double tFwdTwist = SmartDashboard.getNumber("t_Fwd_Twist", 0);
        double tFwdTime = SmartDashboard.getNumber("t_Fwd_Time", 0);
        
        switch (position) {
           case "Back-Out Only":
                cmd = maneuver(-0.5, 0, 2).andThen(fullStop());
                break;
            case "T1-Left":
            case "T2-Left":
                cmd = 
                    // maneuver(tBackVol, 0, tBackTime).andThen(
                    maneuver(-0.500000, 0, 1.650000).andThen(       // back out of tarmac
                    initWait()).andThen(
                    maneuver(0, 0.5000, 0.48500000)).andThen(        // turn right
                    maneuver(0.6400000, -0.46100000, 2.850000)).andThen(    // curve left to fender
                    // maneuver(0, tTurnVol, tTurnTime)).andThen(
                    // maneuver(tFwdVol, tFwdTwist, tFwdTime)).andThen(
                    deliverCargo()).andThen(
                    fullStop());
                break;
            case "T1-Center":
            case "T2-Center":
                cmd = maneuver(-0.5, 0, 3.1).andThen(       // back out of tarmac
                        initWait()).andThen(
                        maneuver(0.5, 0, 4)).andThen(        // move straight to fender
                        // maneuver(tBackVol, 0, tBackTime)).andThen(
                        // maneuver(tFwdVol, tFwdTwist, tFwdTime)).andThen(
                        deliverCargo()).andThen(
                        fullStop());
                break;
            case "T1-Right":
            case "T2-Right":
                cmd =  
                        // maneuver(tBackVol, 0, tBackTime).andThen(
                        maneuver(-0.500000, 0, 1.650000).andThen(
                         initWait()).andThen(
                        // maneuver(0, tTurnVol, tTurnTime)).andThen(
                        // maneuver(tFwdVol, tFwdTwist, tFwdTime)).andThen(
                        maneuver(0, -0.5000, 0.48500000)).andThen(
                        maneuver(0.6400000, 0.46100000, 2.850000)).andThen(
                        deliverCargo()).andThen(
                        fullStop());
                break;
            default:
                return null;
        }
        
        return cmd;
    }

    private Command deliverCargo(){
        return new IntakeOut(intake).withTimeout(2).andThen(
                maneuver(-0.5, 0, 3.5));
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