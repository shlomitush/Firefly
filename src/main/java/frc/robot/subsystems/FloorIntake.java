package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import static frc.robot.Constants.FloorIntake.*;


public class FloorIntake extends SubsystemBase {

    private final TalonSRX floorIntake = new TalonSRX(floorIntakeMotorID);

    public FloorIntake() {
        floorIntake.setInverted(true);
    }

    /**
     * for picking from floor
     */
    public void pickUpFromFloor() {
        floorIntake.set(ControlMode.PercentOutput, floorIntakeSpeed);
    }

    /**
     * for when throwing out to the floor
     */
    public void downOut() {
        floorIntake.set(ControlMode.PercentOutput, -1);
    }

    /**
     * for when throwing out up
     */
    public void upOut() {
        floorIntake.set(ControlMode.PercentOutput, 1);
    }

    /**
     * stops motors
     */
    public void stop() {
        floorIntake.set(ControlMode.PercentOutput, 0);
    }


    public void slowUpOut() {
        floorIntake.set(ControlMode.PercentOutput, floorIntakeSlowUpOutSpeed);
    }
}
