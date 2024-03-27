package frc.robot.Amp;

import frc.robot.Flywheel.Flywheel;

public class AmpController {
    private Amp amp;

    public AmpController(Amp amp) {
        this.amp = amp;
    }

    public void setFlywheelSpeed(double speed) {
        amp.setSpeed(speed);
    }
}
