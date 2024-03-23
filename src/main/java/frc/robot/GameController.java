package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public class GameController {
    private Joystick joystick;

    public GameController(int port) {
        joystick = new Joystick(port);
    }

    public double getDriveSpeed() {
        // Example: Get drive speed from joystick axis
        return joystick.getRawAxis(1); // Adjust axis number as needed
    }

    public double getDriveRotation() {
        // Example: Get drive rotation from joystick axis
        return joystick.getRawAxis(2); // Adjust axis number as needed
    }

    public double getStrafeSpeed() {
        // Example: Get strafe speed from joystick axis
        return joystick.getRawAxis(0); // Adjust axis number as needed
    }

    public boolean isFlywheelButtonPressed() {
        // Example: Check if a button is pressed
        return joystick.getRawButton(1); // Adjust button number as needed
    }

    // Add other methods as needed for additional controls
}

