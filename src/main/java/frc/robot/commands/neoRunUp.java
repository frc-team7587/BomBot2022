package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.DigitalInput;


public class neoRunUp extends CommandBase {
    private neo neo;

    private DigitalInput toplimitSwitch = new DigitalInput(8);

    public neoRunUp(neo subsystem) {
        addRequirements(subsystem);
        neo = subsystem;
    }

    @Override
    public void execute() {
        if (toplimitSwitch.get()) {
            neo.stop();
          } else {
            neo.raise();
          }
    }

    @Override
    public void end(boolean interrupted) {
        neo.stop();
    }

}
