package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Flywheel.FlywheelSubsystem;

public class Robot extends TimedRobot {
    private final SwerveDriveSubsystem swerveDrive = new SwerveDriveSubsystem();
    private final XboxController driverController = new XboxController(Constants.DRIVER_CONTROLLER_PORT);
    private Joystick leftTrigger;
    private Joystick rightTrigger;
    private final FlywheelSubsystem flywheel = new FlywheelSubsystem();
    private final AmpSubsystem amp = new AmpSubsystem();
    
    @Override
    public void autonomousInit(){
        RobotContainer.getInstance().getPigeonIMU().setYaw(180);
    }

    @Override
    public void robotInit() {
    }


    @Override
    public void teleopPeriodic() {
        leftTrigger = new JoystickButton(controller, XboxController.Button.kLeftTrigger.value);
        rightTrigger =  = new JoystickButton(controller, XboxController.Button.kLRightTrigger.value);
        double forward = driverController.getLeftY() * Constants.MAX_DRIVE_SPEED; 
        double strafe = driverController.getLeftX() * Constants.MAX_DRIVE_SPEED;
        double rotation = driverController.getRightX() * Constants.MAX_TURN_SPEED;
        boolean cancelGyro = driverController.getBackButton();
    

        boolean flywheelDir = rightTrigger.getAsBoolean();
        if(leftTrigger.getAsBoolean()) {
            flywheelDir = !flywheelDir;
        }
        boolean flywheelRampUp = driverController.getRightBumper();
        boolean flywheelOff = driverController.getBButton();

        // boolean ampDir = ;
        // if() {
        //     ampDir = !ampDir;
        // }
        // boolean ampOff = ;


        

        boolean climber = driverController.getXButton();


        if(cancelGyro) {
            RobotContainer.getInstance().getPigeonIMU().setYaw(0);
        }
        flywheel.shoot(flywheelDir, flywheelOff, flywheelRampUp);
        // amp.shoot(ampDir, ampOff);
        swerveDrive.drive(forward, strafe, rotation);
        SmartDashboard.putNumber("pigeon oreintation", RobotContainer.getInstance().getPigeonHeading());
    }

    // Optionally include autonomousPeriodic, disabledInit, etc.
}


