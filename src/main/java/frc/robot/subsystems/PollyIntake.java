package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.PollyIntake.*;

// and moves from the flour intake to the fly wheel.
public class PollyIntake extends SubsystemBase {

    private final TalonSRX pollyIntakeMotor = new TalonSRX(pollyIntakeMotorID);

    public PollyIntake() {
        pollyIntakeMotor.setInverted(true);
    }




    public void floorIn() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, pollyIntakeSpeedFloor);
    }

    public void downOut() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -1);
    }

    public void upOut() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, 1);
    }

    public void inFeeder1() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -pollyIntakeSpeedFeeder);
    }



    public void outFeeder() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -pollyIntakeSpeedFeeder);
    }

    public void backALittle() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, -1);
    }

    public void stop() {
        pollyIntakeMotor.set(ControlMode.PercentOutput, 0);
    }




}
