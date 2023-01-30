package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class neoRunUp extends CommandBase {
    private neo neo;

    public neoRunUp(neo subsystem) {
        addRequirements(subsystem);
        neo = subsystem;
    }

    @Override
    public void execute() {
        neo.raise();
    }

    @Override
    public void end(boolean interrupted) {
        neo.stop();
    }

}
