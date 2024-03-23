package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;


public class SwerveDrive {
    private SwerveModule[] modules;

    public SwerveDrive() {
        // Initialize swerve modules
        modules = new SwerveModule[4];
        // Adjust these angles and lengths as per your robot design
        double[] angles = {45, 135, 225, 315};
        double[] lengths = {10, 10, 10, 10}; // Length from center to wheel
        // Adjust these motor ports as per your setup
        int[] driveMotorPorts = {1, 2, 3, 4};
        int[] steeringMotorPorts = {5, 6, 7, 8};

        for (int i = 0; i < 4; i++) {
            modules[i] = new SwerveModule(angles[i], lengths[i], new CANSparkMax(driveMotorPorts[i], MotorType.kBrushed), new WPI_VictorSPX(steeringMotorPorts[i])); 
        }
    }

    public void drive(double driveSpeed, double driveRotation, double strafeSpeed) {
        // Calculate the components of motion (forward, strafe, and rotation)
        double forward = driveSpeed;
        double strafe = strafeSpeed;
        double rotation = driveRotation;
    
        // Calculate the motion for each wheel
        for (int i = 0; i < 4; i++) {
            double angle = Math.toRadians(modules[i].getSteeringAngle());
            double length = modules[i].getLength();
    
            // Calculate the vector components for each wheel
            double wheelForward = forward * Math.cos(angle) + strafe * Math.sin(angle);
            double wheelStrafe = -forward * Math.sin(angle) + strafe * Math.cos(angle);
    
            // Calculate the drive speed for each wheel
            double wheelSpeed = Math.sqrt(Math.pow(wheelForward, 2) + Math.pow(wheelStrafe, 2));
            double wheelRotation = rotation;
    
            // Calculate the steering angle for each wheel
            double wheelAngle = Math.atan2(wheelStrafe, wheelForward) * 180 / Math.PI;
    
            // Set drive speed and steering angle for the current module
            modules[i].setDriveSpeed(wheelSpeed);
            modules[i].setSteeringAngle(wheelAngle);
        }
    }
    
}
