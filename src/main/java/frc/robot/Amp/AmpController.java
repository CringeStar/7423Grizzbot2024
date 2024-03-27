package frc.robot.Amp;

public class AmpController {
    private Amp amp;

    public AmpController(Amp amp) {
        this.amp = amp;
    }

    public void setFlywheelSpeed(double speed) {
        amp.setSpeed(speed);
    }
}
