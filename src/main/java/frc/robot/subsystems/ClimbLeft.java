package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;
import java.util.function.Predicate;

import static frc.robot.Constants.Climb.*;

public class ClimbLeft extends SubsystemBase {
    private final TalonSRX climbMotorLeft = new TalonSRX(climbMotorLeftID);


    public ClimbLeft() {
        climbMotorLeft.setNeutralMode(NeutralMode.Brake);
    }

    public void upClimb(double speed){
        climbMotorLeft.set(TalonSRXControlMode.PercentOutput, Math.min(speed, climbMotorSpeedUp));
    }
    public void stopClimb() {
        climbMotorLeft.set(TalonSRXControlMode.PercentOutput, 0);
    }
    public void downClimb(double speed){
        climbMotorLeft.set(TalonSRXControlMode.PercentOutput, Math.max(speed, -climbMotorSpeedDown));
    }

    public void climb(double speed) {
        if (speed < 0) {
            speed = Math.max(speed, -climbMotorSpeedUp);
        } if (speed > 0){
            speed = Math.min(speed, climbMotorSpeedDown);
        }
        climbMotorLeft.set(TalonSRXControlMode.PercentOutput, speed);
    }
}
