package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Flywheel.FlywheelSubsystem;

public class Robot extends TimedRobot {
    private final SwerveDriveSubsystem swerveDrive = new SwerveDriveSubsystem();
    private final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
    private final FlywheelSubsystem flywheel = new FlywheelSubsystem();

    @Override
    public void autonomousInit(){
        RobotContainer.getInstance().getPigeonIMU().setYaw(180);
    }

    @Override
    public void robotInit() {
    }


    @Override
    public void teleopPeriodic() {
        double forward = driverController.getLeftY() * Constants.MAX_DRIVE_SPEED; // Inverting Y for forward
        double strafe = driverController.getLeftX() * Constants.MAX_DRIVE_SPEED;
        double rotation = driverController.getRightX() * Constants.MAX_TURN_SPEED;
        boolean cancelGyro = driverController.getBackButton();


        // boolean flywheelDir = driverController.getRightBumper();
        // if(driverController.getLeftBumper()) {
        //     flywheelDir = !flywheelDir;
        // }
        // boolean flywheelOff = driverController.getYButton();


        boolean flywheelDir = driverController.getRightTriggerAxis() > 0.1;
        if(driverController.getLeftTriggerAxis() > 0.1) {
            flywheelDir = !flywheelDir;
        }
        boolean flywheelRampUp = driverController.getRightBumper();
        boolean flywheelOff = driverController.getBButton();


        boolean climber = driverController.getXButton();


        if(cancelGyro) {
            RobotContainer.getInstance().getPigeonIMU().setYaw(0);
        }
        flywheel.shoot(flywheelDir, flywheelOff, flywheelRampUp);
        swerveDrive.drive(forward, strafe, rotation);
        SmartDashboard.putNumber("pigeon oreintation", RobotContainer.getInstance().getPigeonHeading());
    }

    // Optionally include autonomousPeriodic, disabledInit, etc.
}


