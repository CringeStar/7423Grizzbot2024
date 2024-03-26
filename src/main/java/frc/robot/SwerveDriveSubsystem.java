package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveModule frontLeft = new SwerveModule(Constants.DRIVE_MOTOR_IDS[0], Constants.TURNING_MOTOR_IDS[0], Constants.CANCODER_IDS[0]);
    private final SwerveModule frontRight = new SwerveModule(Constants.DRIVE_MOTOR_IDS[1], Constants.TURNING_MOTOR_IDS[1], Constants.CANCODER_IDS[1]);
    private final SwerveModule backLeft = new SwerveModule(Constants.DRIVE_MOTOR_IDS[2], Constants.TURNING_MOTOR_IDS[2], Constants.CANCODER_IDS[2]);
    private final SwerveModule backRight = new SwerveModule(Constants.DRIVE_MOTOR_IDS[3], Constants.TURNING_MOTOR_IDS[3], Constants.CANCODER_IDS[3]);

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
        Constants.FRONT_LEFT_LOCATION, Constants.FRONT_RIGHT_LOCATION,
        Constants.BACK_LEFT_LOCATION, Constants.BACK_RIGHT_LOCATION);

    // private final SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(0), kinematics.toSwerveModuleStates(speeds)); // Assuming gyro heading is 0 at start

    public SwerveDriveSubsystem() {
    }

    public void log(){
        SmartDashboard.putString("fl encoder", frontLeft.getState().toString());
        SmartDashboard.putString("fr encoder", frontRight.getState().toString());
        SmartDashboard.putString("br encoder", backRight.getState().toString());
        SmartDashboard.putString("bl encoder", backLeft.getState().toString());

    }

    public void drive(double forward, double strafe, double rotation) {
        ChassisSpeeds speeds = ChassisSpeeds.fromFieldRelativeSpeeds(forward, strafe, rotation, Rotation2d.fromDegrees(getHeading()));
        SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);
    
        // Optimizing all module states based on constraints
        for (int i = 0; i < moduleStates.length; i++) {
            SwerveModuleState.optimize(moduleStates[i], Rotation2d.fromDegrees(getHeading()));
        }
    
        // Setting desired states for each module
        log();
        frontLeft.setDesiredState(moduleStates[0]);
        frontRight.setDesiredState(moduleStates[1]);
        backLeft.setDesiredState(moduleStates[2]);
        backRight.setDesiredState(moduleStates[3]);
    }
    

    // public void updateOdometry() {
    //     odometry.update(Rotation2d.fromDegrees(getHeading()), frontLeft.getState(), frontRight.getState(), backLeft.getState(), backRight.getState());
    // }

    // public Pose2d getPose() {
    //     return odometry.getPoseMeters();
    // }

    private double getHeading() {
        // Access the RobotContainer statically to get the heading
        return RobotContainer.getInstance().getPigeonHeading();
    }

    // @Override
    // public void periodic() {
    //     // This method will be called once per scheduler run
    //     drive(getHeading(), getHeading(), getHeading());=
    // }
}

    
