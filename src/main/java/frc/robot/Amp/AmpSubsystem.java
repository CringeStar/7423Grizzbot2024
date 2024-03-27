package frc.robot.Amp;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class AmpSubsystem extends SubsystemBase {


    private WPI_VictorSPX bottomMotor;

    public AmpSubsystem() {
        bottomMotor = new WPI_VictorSPX(15);
    }

    public void shoot(boolean direction, boolean onOff) {

    }

}
