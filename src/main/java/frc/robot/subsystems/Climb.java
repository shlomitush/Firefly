package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.Climb.*;
import static frc.robot.Constants.FloorIntake.floorIntakeSpeed;


public class Climb extends SubsystemBase {
    private final TalonSRX climbMotorLeader = new TalonSRX(climbMotorRightID);
//private final TalonSRX climbMotorLeader = new TalonSRX(climbMotorLeftID);

    private final TalonSRX climbMotorFollower = new TalonSRX(climbMotorLeftID);

    public Climb() {
//        climbMotorLeader.setI
        climbMotorFollower.follow(climbMotorLeader);


        climbMotorLeader.setNeutralMode(NeutralMode.Brake);
        climbMotorFollower.setNeutralMode(NeutralMode.Brake);


    }

    public void upClimb(){
        climbMotorLeader.set(TalonSRXControlMode.PercentOutput, climbMotorSpeedUp);
    }
    public void stopClimb() {
        climbMotorLeader.set(TalonSRXControlMode.PercentOutput, 0);
    }
    public void downClimb(){
        climbMotorLeader.set(TalonSRXControlMode.PercentOutput, -climbMotorSpeedDown);
    }


}