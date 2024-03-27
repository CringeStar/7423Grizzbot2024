package frc.robot.Flywheel;

public class FlywheelController {
    private Flywheel flywheel;

    public FlywheelController(Flywheel flywheel) {
        this.flywheel = flywheel;
    }

    public void setFlywheelSpeed(double speed) {
        flywheel.setSpeed(speed);
    }

    // public double getFlywheelSpeed(){
    //     return flywheel.getSpeed();
    // }

}
