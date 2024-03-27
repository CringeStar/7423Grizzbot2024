package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class AmpSubsystem extends SubsystemBase {


    private WPI_VictorSPX motor;

    public AmpSubsystem() {
        motor = new WPI_VictorSPX(16);
    }

    public void climb() {
        motor.set(ControlMode.PercentOutput, 1);
        Thread.sleep(10000); //assuming it takes 10 seconds fot climber to finish
        motor.set(ControlMode.PercentOutput, 0);
    }
}
