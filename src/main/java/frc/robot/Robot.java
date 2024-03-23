package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    private SwerveDrive swerveDrive;
    private GameController controller;
    private FlywheelController flywheelController;

    @Override
    public void robotInit() {
        swerveDrive = new SwerveDrive();
        controller = new GameController(0); //conteoller port
        CANSparkMax flywheelMotor1 = new CANSparkMax(0, MotorType.kBrushed); //update
        CANSparkMax flywheelMotor2 = new CANSparkMax(0, MotorType.kBrushed); //update
        Flywheel flywheel = new Flywheel(flywheelMotor1, flywheelMotor2);
        flywheelController = new FlywheelController(flywheel);

    }

    @Override
    public void teleopPeriodic() {
        double driveSpeed = controller.getDriveSpeed();
        double driveRotation = controller.getDriveRotation();
        double strafeSpeed = controller.getStrafeSpeed();
        double flywheelSpeed = flywheelController.getFlywheelSpeed();

        SmartDashboard.putNumber("Drive Speed", driveSpeed);
        SmartDashboard.putNumber("Drive Rotation", driveRotation);
        SmartDashboard.putNumber("Strafe Speed", strafeSpeed);
        SmartDashboard.putNumber("Flywheel Speed", flywheelSpeed);

        swerveDrive.drive(driveSpeed, driveRotation, strafeSpeed);

        if (controller.isFlywheelButtonPressed()) {
            flywheelController.setFlywheelSpeed(5000); // Set desired flywheel speed
        } else {
            flywheelController.setFlywheelSpeed(0); // Stop the flywheel if button is released
        }
    }
}

