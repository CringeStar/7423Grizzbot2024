package frc.robot;

import com.revrobotics.CANSparkBase;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.IdleMode;
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
        setCurrentLimit(45);
        setBrakeMode(IdleMode.kBrake);
    }

    public SwerveModuleState getState() {
        // Retrieve speed from the drive motor encoder and angle from the CANCoder
        return new SwerveModuleState(driveMotor.getEncoder().getVelocity(), Rotation2d.fromDegrees(canCoder.getPosition()));
    }

    public void setCurrentLimit(int limit) {
        driveMotor.setSmartCurrentLimit(limit);
    }

    public void setBrakeMode(CANSparkBase.IdleMode mode){
        driveMotor.setIdleMode(mode);
    }

    public void setDesiredState(SwerveModuleState desiredState) {
        // Use SwerveModuleState.optimize() to get the state with the shortest rotation
        SwerveModuleState state = SwerveModuleState.optimize(desiredState, Rotation2d.fromDegrees(canCoder.getPosition()));
        
        // Set speeds for both drive and turning motors
        setSpeeds(state.speedMetersPerSecond, state.angle.getDegrees());
    }

    private void setSpeeds(double driveSpeed, double targetAngleDegrees) {
        // Set the drive motor speed directly
        if (driveMotor.getDeviceId() == 4 || driveMotor.getDeviceId() == 10) {
            driveMotor.set(driveSpeed*-1);
        } else {
            driveMotor.set(driveSpeed);
        }

        // Convert the target angle to a suitable output for the turning motor
        // Assuming the turning motor is controlled by percent output (-1 to 1)
        double currentAngleDegrees = canCoder.getPosition();
        double angleDifference = targetAngleDegrees - currentAngleDegrees;

        // Normalize the angle difference to [-180, 180) for minimal movement
        angleDifference = ((angleDifference + 180) % 360 + 360) % 360 - 180;

        // Convert the angle difference to percent output for the turning motor
        double turnOutput = angleDifference / 180.0;

        // Set the turning motor speed
        turningMotor.set(ControlMode.PercentOutput, turnOutput);
    }
}
