package frc.robot.Amp;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;


public class Amp {
    private WPI_VictorSPX motor;

    public Amp(WPI_VictorSPX motor) {
        this.motor = motor;

    }

    public void setSpeed(double speed) {
        // Set flywheel speed using PID control for both motors
        motor.set(ControlMode.Current, speed);
    }
    
}

