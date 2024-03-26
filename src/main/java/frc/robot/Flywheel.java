// package frc.robot;

// import com.revrobotics.CANSparkMax;
// import com.revrobotics.ControlType;

// @SuppressWarnings("removal")

// public class Flywheel {
//     private CANSparkMax flywheelMotor1;
//     private CANSparkMax flywheelMotor2;
//     private static final double kP = 0.1; // PID constants - tune as needed
//     private static final double kI = 0.0;
//     private static final double kD = 0.0;
//     private static final double kF = 0.0;

//     public Flywheel(CANSparkMax flywheelMotor1, CANSparkMax flywheelMotor2) {
//         this.flywheelMotor1 = flywheelMotor1;
//         this.flywheelMotor2 = flywheelMotor2;

//         // Configure PID controllers for both motors
//         configurePID(flywheelMotor1);
//         configurePID(flywheelMotor2);
//     }

//     private void configurePID(CANSparkMax motor) {
//         motor.getPIDController().setP(kP);
//         motor.getPIDController().setI(kI);
//         motor.getPIDController().setD(kD);
//         motor.getPIDController().setFF(kF);
//         motor.getPIDController().setOutputRange(0.0, 1.0); // Adjust output range as needed
//     }

//     public void setSpeed(double speed) {
//         // Set flywheel speed using PID control for both motors
//         flywheelMotor1.getPIDController().setReference(speed, ControlType.kVelocity);
//         flywheelMotor2.getPIDController().setReference(speed, ControlType.kVelocity);
//     }
    
// }

