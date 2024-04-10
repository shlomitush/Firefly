package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.GroupMotorControllers;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;

import java.util.function.DoubleSupplier;

public class Tank extends SubsystemBase {
    private final WPI_TalonSRX LF = new WPI_TalonSRX(6);
    private final WPI_TalonSRX LB = new WPI_TalonSRX(1);
    private final WPI_TalonSRX RF = new WPI_TalonSRX(8);
    private final WPI_TalonSRX RB = new WPI_TalonSRX(4);

    private final AHRS gyro = new AHRS(SerialPort.Port.kMXP);
    private boolean tankDrive = true;

//    private GenericEntry setpoint = Shuffleboard.getTab("PID").add("setpoint", 0).getEntry();
//    private GenericEntry P = Shuffleboard.getTab("PID").add("P", 0).getEntry();
//    private GenericEntry D = Shuffleboard.getTab("PID").add("D", 0).getEntry();
//    private GenericEntry I = Shuffleboard.getTab("PID").add("I", 0).getEntry();

    private final PIDController anglePIDcontroller = new PIDController(0.03, 0.00000001, 0.01);
//GroupMotorControllers left = new GroupMotorControllers();


//left.add(LF);

    private final DifferentialDrive drive = new DifferentialDrive(LB, RB);

    private Translation2d joystickTranslation;
    private Rotation2d angleOffset;

    public Tank(){
        LB.setInverted(false);
        LF.setInverted(false);
        RB.setInverted(true);
        RF.setInverted(true);

        anglePIDcontroller.enableContinuousInput(-180, 180);

        angleOffset = new Rotation2d();

    }

    public void setJoystickTranslation(double x, double y){
        this.joystickTranslation = new Translation2d(x, y);
    }

    public Rotation2d getDriveAngle(){
        return this.joystickTranslation.getAngle();
    }

    public double getDriveSpeed(){
        return -this.joystickTranslation.getNorm();
    }

    public double getPIDoutput(Rotation2d robotAngle, Rotation2d setpoint){
        return anglePIDcontroller.calculate(robotAngle.getDegrees(), setpoint.getDegrees());
    }

    public Rotation2d getRobotAngle(){
        return gyro.getRotation2d().plus(angleOffset).plus(Rotation2d.fromDegrees(180));
    }

    public void resetOffSet() {
        this.angleOffset = Rotation2d.fromDegrees(gyro.getAngle());
    }

    public void drive(double ly, double lx, double ry, double rx){
        if (tankDrive){
            if (Math.abs(ly) + Math.abs(lx) > 0.2) {
                setJoystickTranslation(ly, lx);
                drive.arcadeDrive(getDriveSpeed(), getPIDoutput(getRobotAngle(), getDriveAngle()), false);
            } else {
                drive.arcadeDrive(ry, rx);
            }
        } else {
            drive.arcadeDrive(ly, rx);
        }
    }

    public Command driveCommand(DoubleSupplier lxSupplier, DoubleSupplier lySupplier, DoubleSupplier rxSupplier,
                                DoubleSupplier rySupplier){
        return this.run(()-> drive(lxSupplier.getAsDouble(), lySupplier.getAsDouble(), rxSupplier.getAsDouble(),
                rySupplier.getAsDouble()));
    }

//    public Command turnToAngleCommand(){
//        return this.run(()-> {
//            drive.arcadeDrive(0,
//                    anglePIDcontroller.calculate(getRobotAngle().getDegrees(), setpoint.getDouble(0)));
//            anglePIDcontroller.setP(P.getDouble(0));
//            anglePIDcontroller.setD(D.getDouble(0));
//        });
//    }

    public void changeTankDrive(){
        this.tankDrive = !this.tankDrive;
    }

    public Command changeTankDriveCommand(){
        return this.runOnce(this::changeTankDrive);
    }

    public Command setOffSetCommand(){
        return this.runOnce(this::resetOffSet).ignoringDisable(true);
    }

    private XboxController xbox = new XboxController(0);
    public void periodic() {
                SmartDashboard.putNumber("gyro angle", getRobotAngle().getDegrees());
                SmartDashboard.putNumber("cont", new Rotation2d(-xbox.getLeftX(), -xbox.getLeftY()).getDegrees());
    }
}