package frc.robot.Subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class AmpSubsystem extends SubsystemBase {


    private WPI_VictorSPX motor;

    public AmpSubsystem() {
        motor = new WPI_VictorSPX(15);
    }

    public void shoot(boolean direction, boolean off) {
        if(off) {
            motor.set(ControlMode.PercentOutput, 0);
            return;
        }
        if(direction){
            motor.set(ControlMode.PercentOutput, 0.25);
        } else {
            motor.set(ControlMode.PercentOutput, -0.25);
        }
    }
}
