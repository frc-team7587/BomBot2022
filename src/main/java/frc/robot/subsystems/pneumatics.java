package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;


public class pneumatics extends SubsystemBase {
    private final Compressor pcmCompressor = new Compressor(2, PneumaticsModuleType.CTREPCM);
    // private final Solenoid front = new Solenoid(PneumaticsModuleType.CTREPCM, 1);
    // private final Solenoid back = new Solenoid(PneumaticsModuleType.CTREPCM, 0);

    private final DoubleSolenoid solenoid = new DoubleSolenoid(2, PneumaticsModuleType.CTREPCM, 0, 1);


    public pneumatics() {
        pcmCompressor.enableDigital();
        // System.out.println(front.get());
        // System.out.println(back.get());

        // solenoid.set(kReverse);
    }

    public void forward() {
        solenoid.set(DoubleSolenoid.Value.kForward);
        // front.set(true);
        // back.set(false);
        System.out.println("forward");
    }

    public void backward() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
        // front.set(false);
        // back.set(true);
        System.out.println("backward");
    }

    public void off() {
        solenoid.set(DoubleSolenoid.Value.kOff);
    }
}
