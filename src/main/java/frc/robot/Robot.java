package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
    private final SwerveDriveSubsystem swerveDrive = new SwerveDriveSubsystem();
    private final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
    // private final FlywheelSubsystem flywheel = new FlywheelSubsystem();

    @Override
    public void robotInit() {
    }


    @Override
    public void teleopPeriodic() {
        double forward = -driverController.getLeftY() * Constants.MAX_DRIVE_SPEED; // Inverting Y for forward
        double strafe = driverController.getLeftX() * Constants.MAX_DRIVE_SPEED;
        double rotation = driverController.getRightX() * Constants.MAX_TURN_SPEED;


        boolean flywheelDir = driverController.getRightBumper();
        if(driverController.getLeftBumper()) {
            flywheelDir = !flywheelDir;
        }
        boolean flywheelOff = driverController.getYButton();


        boolean amp = driverController.getRightTriggerAxis() > 0;
        if(driverController.getLeftTriggerAxis() > 0) {
            amp = !amp;
        }
        boolean ampOff = driverController.getBButton();

        boolean climber = driverController.getXButton();



        // flywheel.shoot(flywheelDir, flywheelOff);
        swerveDrive.drive(forward, strafe, rotation);
        SmartDashboard.putNumber("pigeon oreintation", RobotContainer.getInstance().getPigeonHeading());
    }

    // Optionally include autonomousPeriodic, disabledInit, etc.
}


