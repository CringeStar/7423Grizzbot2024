package frc.robot.Subsystems;

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

    private CANSparkMax bottomMotor;
    private CANSparkMax topMotor;

    private PIDController pidController1;
    private PIDController pidController2;

    private SimpleMotorFeedforward feedforward;

    public FlywheelSubsystem() {
        bottomMotor = new CANSparkMax(13, CANSparkMaxLowLevel.MotorType.kBrushless);
        topMotor = new CANSparkMax(14, CANSparkMaxLowLevel.MotorType.kBrushless);

        pidController1 = new PIDController(kP, kI, kD);
        pidController1.setTolerance(50); // You can adjust this tolerance as needed
        pidController1.setSetpoint(kSetpoint);

        pidController2 = new PIDController(kP, kI, kD);
        pidController2.setTolerance(50); // You can adjust this tolerance as needed
        pidController2.setSetpoint(kSetpoint);

        feedforward = new SimpleMotorFeedforward(kF, 0.2);
    }

    public void shoot(boolean direction, boolean onOff, boolean flywheelRampUp) {
        double velocity1 = 0.0;
        double velocity2 = 0.0;

        if (!onOff) {
            bottomMotor.set(0);
            topMotor.set(0);
            return;
        }
        
        velocity1 = direction ? pidController1.calculate(bottomMotor.getEncoder().getVelocity(), kSetpoint) :
                                    -pidController1.calculate(bottomMotor.getEncoder().getVelocity(), kSetpoint);
        velocity2 = direction ? pidController2.calculate(topMotor.getEncoder().getVelocity(), kSetpoint) :
                                    -pidController2.calculate(topMotor.getEncoder().getVelocity(), kSetpoint);
        

        double output1 = feedforward.calculate(kSetpoint) + velocity1;
        double output2 = feedforward.calculate(kSetpoint) + velocity2;

        if(flywheelRampUp) {
            bottomMotor.set(0);
            topMotor.set(limitOutput(output2));
            return;
        }

        bottomMotor.set(limitOutput(output1));
        topMotor.set(limitOutput(output2));
    }

    private double limitOutput(double output) {
        return Math.max(Math.min(output, kMaxOutput), kMinOutput);
    }
}
