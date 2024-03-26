package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;

@SuppressWarnings("removal")

public class SwerveModule {
    private CANSparkMax driveMotor;
    private WPI_VictorSPX turningMotor;
    private CANCoder canCoder;

    public SwerveModule(int driveMotorID, int turningMotorID, int canCoderID) {
        driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        turningMotor = new WPI_VictorSPX(turningMotorID);
        canCoder = new CANCoder(canCoderID);
    }

    public SwerveModuleState getState() {
        return new SwerveModuleState(driveMotor.getEncoder().getVelocity(), Rotation2d.fromDegrees(canCoder.getPosition()));
    }

    // public SwerveModulePosition getPosition(){
    //     return new SwerveModulePosition(canCoder.getPosition(), );
    // }

    public void setDesiredState(SwerveModuleState desiredState) {
        // Use SwerveModuleState.optimize() to get the state with the shortest rotation
        SwerveModuleState state = SwerveModuleState.optimize(desiredState, new Rotation2d(canCoder.getPosition()));
        
        if(this.driveMotor.getDeviceId() == 7) {
            setSpeeds(state.speedMetersPerSecond*-1, state.angle.getRadians());  //reverse back left neo
            return;
        }
        setSpeeds(state.speedMetersPerSecond, state.angle.getRadians());
    }

        public void setSpeeds(double driveSpeed, double targetAngleDegrees) {
        // Set the drive motor speed directly
        driveMotor.set(driveSpeed);

        // Convert the target angle to a format suitable for the turning motor
        // This could be a position in encoder ticks or a percentage output, depending on your setup
        double currentAngleDegrees = canCoder.getPosition();
        double angleDifference = targetAngleDegrees - currentAngleDegrees;

        // Normalize the angle difference to [-180, 180) for minimal movement
        angleDifference = ((angleDifference + 180) % 360 + 360) % 360 - 180;

        // Assuming the turning mechanism is direct (1:1 ratio) and that one full motor rotation corresponds to 360 degrees
        // The below conversion might need adjustments based on your gearing ratio or encoder resolution
        double turnOutput = angleDifference / 360 * 0.2421;

        // Set the turning motor speed or position
        // If your setup involves position control (e.g., to a specific encoder tick count), you'll need to convert the desired angle difference to ticks
        turningMotor.set(ControlMode.PercentOutput, turnOutput);
    }
}

