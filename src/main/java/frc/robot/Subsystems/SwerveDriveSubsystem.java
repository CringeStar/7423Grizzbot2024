package frc.robot.Subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.SwerveModule;

public class SwerveDriveSubsystem {
    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule backLeft;
    private final SwerveModule backRight;

    private final SwerveDriveKinematics kinematics;

    public SwerveDriveSubsystem() {
        frontLeft = new SwerveModule(Constants.DRIVE_MOTOR_IDS[0], Constants.TURNING_MOTOR_IDS[0], Constants.CANCODER_IDS[0]);
        frontRight = new SwerveModule(Constants.DRIVE_MOTOR_IDS[1], Constants.TURNING_MOTOR_IDS[1], Constants.CANCODER_IDS[1]);
        backLeft = new SwerveModule(Constants.DRIVE_MOTOR_IDS[2], Constants.TURNING_MOTOR_IDS[2], Constants.CANCODER_IDS[2]);
        backRight = new SwerveModule(Constants.DRIVE_MOTOR_IDS[3], Constants.TURNING_MOTOR_IDS[3], Constants.CANCODER_IDS[3]);

        kinematics = new SwerveDriveKinematics(
            Constants.FRONT_LEFT_LOCATION, Constants.FRONT_RIGHT_LOCATION,
            Constants.BACK_LEFT_LOCATION, Constants.BACK_RIGHT_LOCATION);
    }

    public void drive(double forward, double strafe, double rotation) {
        // Get the current heading
        double heading = getHeading();
        
        // Calculate the desired chassis speeds including rotation
        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(forward, strafe, rotation, Rotation2d.fromDegrees(heading));

        // Convert chassis speeds to module states
        SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);

        // Set desired states for each module
        frontLeft.setDesiredState(moduleStates[0]);
        frontRight.setDesiredState(moduleStates[1]);
        backLeft.setDesiredState(moduleStates[2]);
        backRight.setDesiredState(moduleStates[3]);
    }

    private double getHeading() {
        // Access the RobotContainer statically to get the heading
        return RobotContainer.getInstance().getPigeonHeading();
    }
}
