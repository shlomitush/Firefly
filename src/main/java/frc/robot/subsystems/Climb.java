package frc.robot.subsystems;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Climb.*;


public class Climb extends SubsystemBase {
    private final CANSparkMax climbMotor = new CANSparkMax(climbMotorID, CANSparkLowLevel.MotorType.kBrushed);

    public void upClimb(){
        climbMotor.set(climbMotorSpeed);

    }
    public void stopClimb() {
        climbMotor.set(0);
    }
    public void downClimb(){
        climbMotor.set(-climbMotorSpeed);
    }


}