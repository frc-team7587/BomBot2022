// package frc.robot.commands;
// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.subsystems.DriveTrain;

// import edu.wpi.first.wpilibj2.command.*;
// import frc.robot.subsystems.*;

// public class stabilizer extends CommandBase {
    
//     private DriveTrain m_drive;

//     public stabilizer(DriveTrain subsystem) {
//         addRequirements(subsystem);
//         m_drive = subsystem;
//     }
//     @Override
//     public void initialize() {
//         System.out.println("Started stabilizer");
//     }
//     @Override
//     public void execute() {
//         NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
//         NetworkTableEntry tx = table.getEntry("tx");
//         double x = tx.getDouble(0.0);

        
//         //post to smart dashboard periodically
//         SmartDashboard.putNumber("LimelightX", x);
//         System.out.println(x);
//         if (x > 0) {
//             m_drive.right();
//         } else if (x < 0) {
//             m_drive.left();
//         }       
//     }
// }
