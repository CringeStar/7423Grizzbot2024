package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    private final SwerveDriveSubsystem swerveDrive = new SwerveDriveSubsystem();
    private final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);

    @Override
    public void robotInit() {
    }


    @Override
    public void teleopPeriodic() {
        double forward = -driverController.getLeftY() * Constants.MAX_DRIVE_SPEED; // Inverting Y for forward
        double strafe = driverController.getLeftX() * Constants.MAX_DRIVE_SPEED;
        double rotation = driverController.getRightX() * Constants.MAX_TURN_SPEED;

        swerveDrive.drive(forward, strafe, rotation);

        // Optionally, output current pose to SmartDashboard
        SmartDashboard.putNumber("X Position", swerveDrive.getPose().getX());
        SmartDashboard.putNumber("Y Position", swerveDrive.getPose().getY());
        SmartDashboard.putNumber("Heading", swerveDrive.getPose().getRotation().getDegrees());
    }

    // Optionally include autonomousPeriodic, disabledInit, etc.
}


