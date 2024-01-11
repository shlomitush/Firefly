package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.driveTrain.*;

public class DriveTrain extends SubsystemBase {

    private final Spark rightFollower = new Spark(rightFollowerPort);
    private final Spark leftLeader = new Spark(LeftLeaderPort);
    private final Spark leftFollower = new Spark(leftFollowerPort);
    private final Spark rightLeader = new Spark(rightLeaderPort);
    private final MotorControllerGroup rightMotors = new MotorControllerGroup(rightLeader, rightFollower);
    private final MotorControllerGroup leftMotors = new MotorControllerGroup(leftLeader, leftFollower);
    private final DifferentialDrive drive = new DifferentialDrive(rightMotors, leftMotors);
//private final DifferentialDrive drive = new DifferentialDrive(rightFollower, new Spark(6));

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);



    public DriveTrain(){

        leftMotors.setInverted(true);
    }




    public void drive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation);
    }

    public Command driveTrainCommand(DoubleSupplier speed, DoubleSupplier rot) {
//        gyro.getYaw();
        return new RunCommand(
                () -> drive(speed.getAsDouble(), rot.getAsDouble())
                , this
        );
    }


    @Override
    public void periodic() {
        SmartDashboard.putNumber("robot angle", gyro.getAngle() );
    }
}
