package frc.robot.Amp;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class AmpSubsystem extends SubsystemBase {


    private VictorSPX bottomMotor;

    public AmpSubsystem() {
        bottomMotor = new VictorSPX(15);
    }

    public void shoot(boolean direction, boolean onOff) {

    }

}
