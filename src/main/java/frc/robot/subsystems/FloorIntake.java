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

    public void pickUpFromFloor() {
        floorIntake.set(ControlMode.PercentOutput, floorIntakeSpeed);

//        floorIntake.set(TalonSRXControlMode.Current, floorIntakeSpeed);
    }

    public void downOut() {
        floorIntake.set(ControlMode.PercentOutput, -1);

    }

    public void upOut() {
        floorIntake.set(ControlMode.PercentOutput, 1);

    }

    public void stop() {
        floorIntake.set(ControlMode.PercentOutput, 0);

//        floorIntake.set(TalonSRXControlMode.Current, 0);
    }






}
