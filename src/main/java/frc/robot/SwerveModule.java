package frc.robot;

import com.revrobotics.CANSparkMax;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

public class SwerveModule {
    private double angleOffset;
    private double length;
    private CANSparkMax driveMotor;
    private WPI_VictorSPX steeringMotor;

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
        
        // Configure CANcoder feedback for the steering motor
        steeringMotor.configSelectedFeedbackSensor(FeedbackDevice.RemoteSensor0, 0, 0);
        
        // You will need to configure PID control for the VictorSPX manually
        // Here's an example setup, you might need to adjust it according to your needs
        steeringMotor.config_kP(0, kP);
        steeringMotor.config_kI(0, kI);
        steeringMotor.config_kD(0, kD);
        steeringMotor.config_kF(0, kF);
        // Adjust other settings as necessary
    }

    public void setDriveSpeed(double speed) {
        // Set drive motor speed
        driveMotor.set(speed);
    }

    public void setSteeringAngle(double angle) {
        // Ensure angle is within limits
        angle = Math.min(Math.max(angle, minAngle), maxAngle);
        
        // Convert angle to position based on your encoder's resolution and gear ratio
        double position = angleToPosition(angle);

        // Set setpoint for position control (PID) on the VictorSPX motor
        steeringMotor.set(ControlMode.Position, position);
    }

    public double getSteeringAngle() {
        // Read the position from the CANcoder and convert it to angle
        double position = steeringMotor.getSelectedSensorPosition(0);
        double angle = position * (360.0 / 4096.0); // Assuming 4096 counts per revolution
        angle -= angleOffset; // Subtract offset

        // Normalize angle to [-180, 180] range
        while (angle > 180) {
            angle -= 360;
        }
        while (angle < -180) {
            angle += 360;
        }

        return angle;
    }

    // Helper method to convert desired angle to encoder position
    private double angleToPosition(double angle) {
        // Calculate position based on your encoder's resolution and gear ratio
        // For example, if you have a 360-degree CANcoder with 4096 counts per revolution:
        double position = (angle / 360.0) * 4096.0;
        return position;
    }


    public double getLength(){
        return length;
    }
}
