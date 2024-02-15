package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.driveTrain.*;

public class DriveTrain extends SubsystemBase {

    private final CANSparkMax rightLeader = new CANSparkMax(rightLeaderPort, CANSparkLowLevel.MotorType.kBrushed);
    private final CANSparkMax rightFollower = new CANSparkMax(rightFollowerPort, CANSparkLowLevel.MotorType.kBrushed);
    private final CANSparkMax leftLeader = new CANSparkMax(leftLeaderPort, CANSparkLowLevel.MotorType.kBrushed);
    private final CANSparkMax leftFollower = new CANSparkMax(leftFollowerPort, CANSparkLowLevel.MotorType.kBrushed);
    private final DifferentialDrive drive = new DifferentialDrive(rightLeader, leftLeader);
//private final DifferentialDrive drive = new DifferentialDrive(rightFollower, new Spark(6));

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);



    public DriveTrain(){
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);
        rightLeader.setInverted(true);

        gyro.reset();
    }

    private double speedSens(double speed) {
//        return Math.min(1, speed + 0.3);
        return speed;
    }

    public void drive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public void stopDrive() {
        drive.stopMotor();
    }

//    public Command driveTrainCommand(DoubleSupplier speed, DoubleSupplier rot) {
////        gyro.getYaw();
//        return new RunCommand(
//                () -> drive(speed.getAsDouble(), rot.getAsDouble())
//                , this
//        );
//    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("robot angle", getRobotAngle());
    }

    public double getRobotAngle(){
        return gyro.getAngle();
    }
}
