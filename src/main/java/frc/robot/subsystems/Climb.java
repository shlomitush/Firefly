package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Climb.*;
import static frc.robot.Constants.FloorIntake.floorIntakeSpeed;


public class Climb extends SubsystemBase {
    private final TalonSRX climbMotor = new TalonSRX(climbMotorID);

    public void upClimb(){
        climbMotor.set(TalonSRXControlMode.Current, climbMotorSpeed);
    }
    public void stopClimb() {
        climbMotor.set(TalonSRXControlMode.Current, 0);
    }
    public void downClimb(){
        climbMotor.set(TalonSRXControlMode.Current, -climbMotorSpeed);
    }


}