package frc.robot.subsystems;


import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.FlyWheel.*;

public class FlyWheel extends SubsystemBase {
    private final CANSparkMax flyWheelMotorLeader = new CANSparkMax(flyWheelMotorIDLeader,
            CANSparkLowLevel.MotorType.kBrushless);

    private final CANSparkMax flyWheelMotorFollower = new CANSparkMax(flyWheelMotorIDFollower,
            CANSparkLowLevel.MotorType.kBrushless);

    public FlyWheel(){

    }


    /**
     * starts flywheel motor at the appropriate speed for throwing to the speaker
     */
    public void throwWheel() {
        flyWheelMotorLeader.set(-flyWheelMotorSpeedThrow);
        flyWheelMotorFollower.set(-flyWheelMotorSpeedThrow);
    }

    public void throwAMP() {
        flyWheelMotorLeader.set(-flyWheelMotorSpeedAMP);
        flyWheelMotorFollower.set(-flyWheelMotorSpeedAMP);
    }


    /**
     * for when throwing out up
     */
    public void upOut() {
        flyWheelMotorLeader.set(-1);
        flyWheelMotorFollower.set(-1);
    }
    /**
     * for when throwing out to the floor
     */
    public void downOut() {
        flyWheelMotorLeader.set(1);
        flyWheelMotorFollower.set(1);
    }

    /**
     * when taking in a wheel from the feeder
     */
    public void wheelIn() {
        flyWheelMotorLeader.set(flyWheelMotorSpeedFeederIn);
        flyWheelMotorFollower.set(flyWheelMotorSpeedFeederIn);
    }

    /**
     * stops motors
     */
    public void stop() {
        flyWheelMotorLeader.set(0);
        flyWheelMotorFollower.set(0);
    }

    public void slowUpOut() {
        flyWheelMotorLeader.set(-flyWheelSlowUpOutSpeed);
        flyWheelMotorLeader.set(-flyWheelSlowUpOutSpeed);
    }
}
