package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.*;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


import com.ctre.phoenix.sensors.WPI_PigeonIMU;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

import static frc.robot.Constants.driveTrain.*;

public class DriveTrain extends SubsystemBase {

    private final CANSparkMax rightLeader = new CANSparkMax(rightLeaderPort, CANSparkLowLevel.MotorType.kBrushed);
    private final CANSparkMax rightFollower = new CANSparkMax(rightFollowerPort, CANSparkLowLevel.MotorType.kBrushed);
    private final CANSparkMax leftLeader = new CANSparkMax(leftLeaderPort, CANSparkLowLevel.MotorType.kBrushed);
    private final CANSparkMax leftFollower = new CANSparkMax(leftFollowerPort, CANSparkLowLevel.MotorType.kBrushed);
    private final DifferentialDrive drive = new DifferentialDrive(rightLeader, leftLeader);
//    public static final DifferentialDriveKinematics KINEMATICS = new DifferentialDriveKinematics(0.44);

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);

    private final RelativeEncoder rightEncoder = rightLeader.getEncoder(SparkRelativeEncoder.Type.kQuadrature,360);


    private final RelativeEncoder leftEncoder = leftFollower.getEncoder(SparkRelativeEncoder.Type.kQuadrature,360);

    private final DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(
            gyro.getRotation2d(),
            getLeftTravelDistanceMetres(),
            getRightTravelDistanceMetres()
    );



    public DriveTrain(){
//        leftLeader.restoreFactoryDefaults();
//        rightLeader.restoreFactoryDefaults();
        leftFollower.setIdleMode(CANSparkBase.IdleMode.kBrake);
        leftLeader.setIdleMode(CANSparkBase.IdleMode.kBrake);
        rightLeader.setIdleMode(CANSparkBase.IdleMode.kBrake);
        rightFollower.setIdleMode(CANSparkBase.IdleMode.kBrake);

//        leftLeader.setSmartCurrentLimit(50);
//        leftFollower.setSmartCurrentLimit(50);
//        rightLeader.setSmartCurrentLimit(50);
//        rightFollower.setSmartCurrentLimit(50);

        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);
        rightLeader.setInverted(false);
        leftLeader.setInverted(true);

        leftEncoder.setInverted(true);
        rightEncoder.setInverted(false);

        rightEncoder.setPositionConversionFactor(1 / 0.4);
        leftEncoder.setPositionConversionFactor(1 / 0.4);

        gyro.zeroYaw();

        this.drive.setSafetyEnabled(false);
    }


    public void drive(double speed, double rotation) {
        drive.arcadeDrive(speed, rotation, false);
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        drive.tankDrive(leftSpeed, rightSpeed);
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
//        SmartDashboard.putNumber("gyro angle", getRobotAngle());
//
//        SmartDashboard.putNumber("gyro orientation", gyro.getAngle() % 360);
//        SmartDashboard.putNumber("left travel dist", getLeftTravelDistanceMetres());
//        SmartDashboard.putNumber("right travel dist", getRightTravelDistanceMetres());
//        SmartDashboard.putNumber("left enc pos", leftEncoder.getPosition());
//        SmartDashboard.putNumber("right enc pos", rightEncoder.getPosition());

        odometry.update(
                gyro.getRotation2d(),
                getLeftTravelDistanceMetres(),
                getRightTravelDistanceMetres()
        );
    }


    public double getRobotAngle(){
        return gyro.getAngle();
    }

    /**
     * Reset the odometry to a known pose.
     *
     * This is highly relevant at the start of a match.
     *
     * @param poseMetres the new pose estimate of the robot.
     */
    public void resetPoseMetres(Pose2d poseMetres) {
        odometry.resetPosition(
                gyro.getRotation2d(),
                getRightTravelDistanceMetres(),
                getLeftTravelDistanceMetres(),
                poseMetres
        );
    }


    public void resetpos() {
        leftEncoder.setPosition(0);
//        rightEncoder.setPosition(0);
    }

    /**
     * @return the total distance in metres the left side of the robot traveled since the last
     * encoder reset
     */
    public double getLeftTravelDistanceMetres() {
        return leftEncoder.getPosition() * Units.inchesToMeters(6) * Math.PI * 10.71;
    }

    /**
     * @return the total distance in metres the right side of the robot traveled since the last
     * encoder reset
     */
    public double getRightTravelDistanceMetres() {
        return rightEncoder.getPosition() * Units.inchesToMeters(6) * Math.PI * 10.71;
    }

    /**
     * @return the total velocity in metres per second the left side of the robot traveled since the
     * last encoder reset
     */
//    public double getLeftTravelVelocityMetresPerSecond() {
//        return leftEncoder.getVelocity() / 60 * Units.inchesToMeters(6) * Math.PI;
//    }

    /**
     * @return the total velocity in metres per second the right side of the robot traveled since the
     * last encoder reset
     */
//    public double getRightTravelVelocityMetresPerSecond() {
//        return - rightEncoder.getVelocity() / 60 * Units.inchesToMeters(6) * Math.PI;
//    }

//    /**
//     * @return the speed in each side represented by differential drive
//     */
//    public DifferentialDriveWheelSpeeds getSpeeds() {
//        return new DifferentialDriveWheelSpeeds(getLeftTravelVelocityMetresPerSecond(), getRightTravelVelocityMetresPerSecond());
//    }

    /**
     * Set the speed of the motors according to the voltage
     * @param left    the voltage in the left side
     * @param right   the voltage in the right side
     */
    public void setVoltage(double left, double right) {
        leftLeader.setVoltage(left / 12);
        rightLeader.setVoltage(right / 12);
    }

    /**
     * @return the current pose estimate of the robot in metres, in field coordinate.
     */
    public Pose2d getPoseMetres() {
        return odometry.getPoseMeters();
    }

    public void resetYaw() {
        gyro.zeroYaw();
    }

    public double getYaw() {
        return gyro.getYaw();
    }

    public AHRS getGyro() {
        return gyro;
    }

    public double getRightPosition() {
        return rightEncoder.getPosition();
    }

    public void startGyro() {
//        this.gyro.reset();
        this.gyro.zeroYaw();
//        this.gyro.setAngleAdjustment();
    }

    public double getWheelbaseWidth() {
        return 53;
    }


}