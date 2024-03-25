package frc.robot;

import edu.wpi.first.math.geometry.Translation2d;

public class Constants {
    // Motor controller IDs for drive and turning motors
    public static final int[] DRIVE_MOTOR_IDS = {1, 2, 3, 4}; // Front Left, Front Right, Back Left, Back Right
    public static final int[] TURNING_MOTOR_IDS = {5, 6, 7, 8}; // Front Left, Front Right, Back Left, Back Right

    // CANCoder IDs
    public static final int[] CANCODER_IDS = {9, 10, 11, 12}; // Front Left, Front Right, Back Left, Back Right

    // Robot dimensions and controller ports
    public static final double ROBOT_LENGTH = 1.0; // Example length, adjust to your robot's specific dimensions
    public static final double ROBOT_WIDTH = 1.0; // Example width, adjust to your robot's specific dimensions
    public static final int DRIVER_CONTROLLER_PORT = 0;

    // Swerve Drive constants
    public static final double MAX_DRIVE_SPEED = 1.0; // Max drive speed
    public static final double MAX_TURN_SPEED = 0.5; // Max turning speed

    public static final Translation2d FRONT_LEFT_LOCATION = new Translation2d(0.5, 0.5);
    public static final Translation2d FRONT_RIGHT_LOCATION = new Translation2d(0.5, -0.5);
    public static final Translation2d BACK_LEFT_LOCATION = new Translation2d(-0.5, 0.5);
    public static final Translation2d BACK_RIGHT_LOCATION = new Translation2d(-0.5, -0.5);
}
