package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.Climb.*;

public class ClimbRight extends SubsystemBase {
    private final TalonSRX climbMotorRight = new TalonSRX(climbMotorRightID);


    public ClimbRight() {
        climbMotorRight.setNeutralMode(NeutralMode.Brake);
    }

    public void upClimb(double speed){
        climbMotorRight.set(TalonSRXControlMode.PercentOutput, Math.min(speed, climbMotorSpeedUp));
    }
    public void stopClimb() {
        climbMotorRight.set(TalonSRXControlMode.PercentOutput, 0);
    }
    public void downClimb(double speed){
        climbMotorRight.set(TalonSRXControlMode.PercentOutput, Math.max(speed, -climbMotorSpeedDown));
    }
    public void climb(double speed) {
        if (speed < 0) {
            speed = Math.max(speed, -climbMotorSpeedUp);
        } if (speed > 0){
            speed = Math.min(speed, climbMotorSpeedDown);
        }
        climbMotorRight.set(TalonSRXControlMode.PercentOutput, speed);
    }
}
