package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;
import java.util.function.Predicate;

import static frc.robot.Constants.Climb.*;

public class ClimbLeft extends SubsystemBase {
    private final TalonSRX climbMotorLeft = new TalonSRX(climbMotorLeftID);
//    private final DigitalInput climbLeftLimitSwitch = new DigitalInput(climbLeftLimitSwitchID);


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
        } else if (speed > 0){
            speed = Math.min(speed, climbMotorSpeedDown);
        }

//        if (!isSafe()) {
//            speed = 0;
//        }

        climbMotorLeft.set(TalonSRXControlMode.PercentOutput, -speed);
    }

//    public boolean isSafe() {
//        return climbLeftLimitSwitch.get();
//    }
}

//dddf