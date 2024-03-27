package frc.robot.Amp;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


public class Amp {
    private VictorSPX motor;

    public Amp(VictorSPX motor) {
        this.motor = motor;

    }

    public void setSpeed(double speed) {
        // Set flywheel speed using PID control for both motors
        motor.set(ControlMode.Current, speed);
    }
    
}

