package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    private SwerveDrive swerveDrive;
    private GameController controller;

    @Override
    public void robotInit() {
        swerveDrive = new SwerveDrive();
        controller = new GameController();
    }

    @Override
    public void teleopPeriodic() {
        double driveSpeed = controller.getDriveSpeed();
        double driveRotation = controller.getDriveRotation();
        double strafeSpeed = controller.getStrafeSpeed();

        swerveDrive.drive(driveSpeed, driveRotation, strafeSpeed);

        // Update SmartDashboard with current values
        SmartDashboard.putNumber("Drive Speed", driveSpeed);
        SmartDashboard.putNumber("Drive Rotation", driveRotation);
        SmartDashboard.putNumber("Strafe Speed", strafeSpeed);
    }
}
