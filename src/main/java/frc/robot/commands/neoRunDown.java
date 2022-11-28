package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;

public class neoRunDown extends CommandBase {
    private neo neo;

    public neoRunDown(neo subsystem) {
        addRequirements(subsystem);
        neo = subsystem;
    }

    @Override
    public void execute() {
        neo.lower();
    }

    @Override
    public void end(boolean interrupted) {
        neo.stop();
    }

}
