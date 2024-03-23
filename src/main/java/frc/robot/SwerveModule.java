package frc.robot;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix6.hardware.CANcoder;

import edu.wpi.first.wpilibj.motorcontrol.Victor;

@SuppressWarnings("removal")
public class SwerveModule {
    private double angleOffset;
    private double length;
    private CANSparkMax driveMotor;
    private WPI_VictorSPX steeringMotor;
    private CANcoder encoder;
    private CANPIDController pidController;

    // PID constants - tune these as needed
    private static final double kP = 0.1;
    private static final double kI = 0.0;
    private static final double kD = 0.0;
    private static final double kF = 0.0;

    // Steering limits - adjust these as needed
    private static final double minAngle = -180.0;
    private static final double maxAngle = 180.0;

    public SwerveModule(double angleOffset, double length, CANSparkMax driveMotor, WPI_VictorSPX steeringMotor) {
        this.angleOffset = angleOffset;
        this.length = length;
        this.driveMotor = driveMotor;
        this.steeringMotor = steeringMotor;
        this.encoder = steeringMotor.getEncoder();
        this.pidController = steeringMotor.getPIDController();

        // Configure PID controller
        pidController.setP(kP);
        pidController.setI(kI);
        pidController.setD(kD);
        pidController.setFF(kF);
        pidController.setOutputRange(-1.0, 1.0); // Adjust output range as needed
    }

    public void setDriveSpeed(double speed) {
        // Set drive motor speed
        driveMotor.set(speed);
    }

    public void setSteeringAngle(double angle) {
        // Ensure angle is within limits
        angle = Math.min(Math.max(angle, minAngle), maxAngle);
        
        // Calculate target position based on offset
        double targetPosition = angle + angleOffset;

        // Wrap angle to [-180, 180] range
        while (targetPosition > 180) {
            targetPosition -= 360;
        }
        while (targetPosition < -180) {
            targetPosition += 360;
        }

        // Set setpoint for PID controller
        pidController.setReference(targetPosition, ControlType.kPosition);
    }

    public double getSteeringAngle() {
        // Get current steering angle from encoder
        return encoder.getPosition();
    }

    public double getLength(){
        return length;
    }
}
