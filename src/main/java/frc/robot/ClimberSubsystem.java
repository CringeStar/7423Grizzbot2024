package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class ClimberSubsystem extends SubsystemBase {


    private WPI_VictorSPX motor;

    public ClimberSubsystem() {
        motor = new WPI_VictorSPX(16);
    }

    public void climb(boolean on) {
        if(on) {
            motor.set(ControlMode.PercentOutput, 1);
        } else {
            motor.set(ControlMode.PercentOutput, 0);
        }
    }
}
