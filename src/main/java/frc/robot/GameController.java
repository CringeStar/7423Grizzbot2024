package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class GameController {
    private Joystick joystick;

    public GameController() {
        joystick = new Joystick(0); // Adjust port number as per your setup
    }

    public double getDriveSpeed() {
        // Get drive speed input from joystick
        return joystick.getRawAxis(1); // Adjust axis number as per your setup
    }

    public double getDriveRotation() {
        // Get drive rotation input from joystick
        return joystick.getRawAxis(2); // Adjust axis number as per your setup
    }

    public double getStrafeSpeed() {
        // Get strafe speed input from joystick
        return joystick.getRawAxis(0); // Adjust axis number as per your setup
    }
}
