package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.PollyIntake.*;

// and moves from the flour intake to the fly wheel.
public class PollyIntake extends SubsystemBase {

    private final TalonSRX pollyIntakeMotor = new TalonSRX(pollyIntakeMotorID);

    public PollyIntake() {
        pollyIntakeMotor.setInverted(true);
    }


    /**
     * for when taking from the floor
     */
    public void floorIn() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, pollyIntakeSpeedFloorIn);
    }

    /**
     * for when throwing out to the floor
     */
    public void downOut() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -1);
    }

    /**
     * for when throwing out up
     */
    public void upOut() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, 1);
    }

    /**
     * for when taking a note from the feeder
     */
    public void inFeeder() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -pollyIntakeSpeedFeederIn);
    }


    /**
     * for if we want to implement amp and want to take out from the feeder nicely
     */
    public void outFeeder() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, pollyIntakeSpeedFeederOut);
    }

    /**
     * before throwing to speeker - moves the note away from the flywheel
     */
    public void backALittle() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -pollyIntakeSpeedBack);
    }

    /**
     * stops motor
     */
    public void stop() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, 0);
    }


    public void slowUpOut() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, pollyIntakeSlowUpOutSpeed);
    }

    public void throwAMP() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, pollyIntakeAMPThrow);
    }
}
