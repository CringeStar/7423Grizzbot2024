package frc.robot;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

@SuppressWarnings("removal")
public class FlywheelSubsystem extends SubsystemBase {
    private static final double kP = 0.1;
    private static final double kI = 0.0;
    private static final double kD = 0.0;
    private static final double kF = 0.05;

    private static final double kMaxOutput = 1.0;
    private static final double kMinOutput = -1.0;

    private static final double kSetpoint = 3000; // RPM

    private CANSparkMax motor1;
    private CANSparkMax motor2;

    private PIDController pidController1;
    private PIDController pidController2;

    private SimpleMotorFeedforward feedforward;

    public FlywheelSubsystem() {
        motor1 = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);
        motor2 = new CANSparkMax(2, CANSparkMaxLowLevel.MotorType.kBrushless);

        pidController1 = new PIDController(kP, kI, kD);
        pidController1.setTolerance(50); // You can adjust this tolerance as needed
        pidController1.setSetpoint(kSetpoint);

        pidController2 = new PIDController(kP, kI, kD);
        pidController2.setTolerance(50); // You can adjust this tolerance as needed
        pidController2.setSetpoint(kSetpoint);

        feedforward = new SimpleMotorFeedforward(kF, 0.2);
    }

    public void shoot(boolean direction, boolean onOff) {
        double velocity1 = 0.0;
        double velocity2 = 0.0;

        if (onOff) {
            velocity1 = direction ? pidController1.calculate(motor1.getEncoder().getVelocity(), kSetpoint) :
                                    -pidController1.calculate(motor1.getEncoder().getVelocity(), kSetpoint);
            velocity2 = direction ? pidController2.calculate(motor2.getEncoder().getVelocity(), kSetpoint) :
                                    -pidController2.calculate(motor2.getEncoder().getVelocity(), kSetpoint);
        }

        double output1 = feedforward.calculate(kSetpoint) + velocity1;
        double output2 = feedforward.calculate(kSetpoint) + velocity2;

        motor1.set(onOff ? limitOutput(output1) : 0.0);
        motor2.set(onOff ? limitOutput(output2) : 0.0);
    }

    private double limitOutput(double output) {
        return Math.max(Math.min(output, kMaxOutput), kMinOutput);
    }
}
