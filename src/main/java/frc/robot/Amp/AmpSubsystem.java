package frc.robot.Amp;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

@SuppressWarnings("removal")
public class AmpSubsystem extends SubsystemBase {

    private static final double kSetpoint = 3000; // RPM

    private CANSparkMax bottomMotor;

    public AmpSubsystem() {
        bottomMotor = new CANSparkMax(15, CANSparkMaxLowLevel.MotorType.kBrushless);
    }

    public void shoot(boolean direction, boolean onOff, boolean flywheelRampUp) {

    }

}
