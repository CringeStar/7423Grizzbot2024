package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

public class RobotContainer {
    // Singleton instance
    private static RobotContainer instance;

    // PigeonIMU instance
    private final PigeonIMU pigeonIMU;

    // Private constructor
    private RobotContainer() {
        pigeonIMU = new PigeonIMU(0);
        pigeonIMU.configFactoryDefault();
    }

    // Singleton instance getter
    public static RobotContainer getInstance() {
        if (instance == null) {
            instance = new RobotContainer();
        }
        return instance;
    }

    // Method to get the heading from the PigeonIMU
    public double getPigeonHeading() {
        double[] ypr = new double[3]; // Array to store yaw, pitch, and roll
        pigeonIMU.getYawPitchRoll(ypr); // Get yaw, pitch, and roll from the Pigeon IMU
        return ypr[0]; // Return yaw (heading)
    }

    public PigeonIMU getPigeonIMU() {
        return this.pigeonIMU;
    }
}
