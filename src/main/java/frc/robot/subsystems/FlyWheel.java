package frc.robot.subsystems;


import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.FlyWheel.*;

public class FlyWheel extends SubsystemBase {
    private final CANSparkMax flyWheelMotorLeader = new CANSparkMax(flyWheelMotorIDLeader,
            CANSparkLowLevel.MotorType.kBrushed);

    private final CANSparkMax flyWheelMotorFollower = new CANSparkMax(flyWheelMotorIDFollower, CANSparkLowLevel.MotorType.kBrushed);

    public FlyWheel(int x){
//        flyWheelMotorFollower.setInverted(true);
//        flyWheelMotorFollower.follow(flyWheelMotorLeader);
//        flyWheelMotorFollower.setInverted(true);
//        flyWheelMotorLeader.setInverted(false);
//        flyWheelMotorFollower.setInverted(true);
//        flyWheelMotorLeader.setInverted(true);


    }


    public void throwWheel() {
        flyWheelMotorLeader.set(-flyWheelMotorSpeedThrow);
        flyWheelMotorFollower.set(-flyWheelMotorSpeedThrow);
    }

    public void stop() {
        flyWheelMotorLeader.set(0);
        flyWheelMotorFollower.set(0);
    }

    public void upOut() {
        flyWheelMotorLeader.set(-1);
        flyWheelMotorFollower.set(-1);
    }

    public void downOut() {
        flyWheelMotorLeader.set(1);
        flyWheelMotorFollower.set(1);
    }

    public void wheelIn() {
        flyWheelMotorLeader.set(-flyWheelMotorSpeedIn);
        flyWheelMotorFollower.set(-flyWheelMotorSpeedIn);
    }

}
