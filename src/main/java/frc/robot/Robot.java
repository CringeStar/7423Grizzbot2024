package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.Subsystems.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Robot extends TimedRobot {
    private final SwerveDriveSubsystem swerveDrive = new SwerveDriveSubsystem();
    private final CommandXboxController driverController = new CommandXboxController(Constants.DRIVER_CONTROLLER_PORT);
    private final FlywheelSubsystem flywheel = new FlywheelSubsystem();
    private final AmpSubsystem amp = new AmpSubsystem();
    private final ClimberSubsystem climber = new ClimberSubsystem();

    @Override
    public void autonomousInit(){
        RobotContainer.getInstance().getPigeonIMU().setYaw(180);
    }

    @Override
    public void autonomousPeriodic() {
        //ramp up flywheel
        //move to speaker
        //ram againt it
        //wait 1 sec
        //shoot flywheel
        //wait 1 sec 
        //turn off flywheel
        //move away
    }
    
    @Override
    public void robotInit() {
    }

    @Override
    public void teleopPeriodic() {
        double forward = driverController.getLeftY() * Constants.MAX_DRIVE_SPEED; 
        double strafe = driverController.getLeftX() * Constants.MAX_DRIVE_SPEED;
        double rotation = driverController.getRightX() * Constants.MAX_TURN_SPEED;
        boolean cancelGyro = driverController.getHID().getBackButton();
    
        boolean rightTrigVal = driverController.rightTrigger(0.25).getAsBoolean();
        boolean leftTrigVal = driverController.leftTrigger(0.25).getAsBoolean();


        boolean flywheelDir = rightTrigVal;
        if(leftTrigVal) {
            flywheelDir = false;
        }
        boolean flywheelRampUp = driverController.getHID().getRightBumper();
        boolean flywheelOff = false;
        if(!leftTrigVal && !rightTrigVal && !flywheelRampUp) {
            flywheelOff = true;
        } else {
            flywheelOff = driverController.getHID().getBButton();
        }

        
        boolean ampDir = driverController.getHID().getAButton();
        if(driverController.getHID().getXButton()) {
            ampDir = false;
        }
        boolean ampOff = !(driverController.getHID().getXButton()) && !(driverController.getHID().getAButton());


        boolean climberOn = driverController.getHID().getYButton();


        if(cancelGyro) {
            RobotContainer.getInstance().getPigeonIMU().setYaw(0);
        }

        
        flywheel.shoot(flywheelDir, flywheelOff, flywheelRampUp);
        amp.shoot(ampDir, ampOff);
        swerveDrive.drive(forward, strafe, rotation);

        if(DriverStation.getMatchTime() < 15) { //assuming last 15 second of 2:15 teleop is endgame
            climber.climb(climberOn);
        }
        // SmartDashboard.putNumber("pigeon oreintation", RobotContainer.getInstance().getPigeonHeading());
    }
}


