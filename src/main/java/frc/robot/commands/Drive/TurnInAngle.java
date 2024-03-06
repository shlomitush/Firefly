package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
//
//public class TurnInAngle extends Command {
//
//    private double setPoint1, setPoint2;
//    private DriveTrain driveTrain;
////    private PIDController pidController = new PIDController(0.01, 0, 0.0001);
//    private final ProfiledPIDController pidController = new ProfiledPIDController(0.04, 0, 0.0001,
//            new TrapezoidProfile.Constraints(100, 60));
//    public Trigger isAtSetPoint = new Trigger(()->Math.abs(pidController.getPositionError()) <= pidController.getPositionTolerance());
//
//
//    public TurnInAngle(DriveTrain driveTrain, double setPoint) {
//        this.driveTrain = driveTrain;
//        this.setPoint1 = setPoint;
//
//        addRequirements(driveTrain);
//
////        pidController.enableContinuousInput(-180, 180);
////        pidController.enableContinuousInput(0, 360);
//
////        pidController.setTolerance(10);
//        pidController.setTolerance(10, 200);
//
//    }
//
//    @Override
//    public void initialize() {
//        pidController.reset(driveTrain.getYaw());
//        this.setPoint2 = setPoint1 + driveTrain.getYaw();
//        pidController.setGoal(this.setPoint2);
////        pidController.setSetpoint(this.setPoint2);
//    }
//
//    @Override
//    public void execute() {
//        double rotation = this.pidController.calculate(driveTrain.getRobotAngle(), this.setPoint2);
//        System.out.println("in execute, rotating at speed: " + rotation);
//
//        driveTrain.drive(0, rotation);
//
//        System.out.println(pidController.getPositionError());
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        driveTrain.stopDrive();
//    }
//
//    @Override
//    public boolean isFinished() {
//        System.out.println("set point: " + pidController.getGoal().toString());
//        System.out.println("in is finished error is: " + pidController.getPositionError());
//        System.out.println(isAtSetPoint.getAsBoolean());
//
////        System.out.println("current position tolerance: " + pidController.getPositionTolerance());
////        System.out.println("current gyro" + pidController.get);
////        System.out.println("set point: " + pidController.getSetpoint());
////        double error = Math.abs(pidController.getPositionError());
////        return error <= pidController.getPositionTolerance();
////        return error < 10;
////        return isAtSetPoint.getAsBoolean();
//
//           return this.isAtSetPoint.getAsBoolean();
//    }
//}


//public class TurnInAngle extends Command {
//
//    private double setPoint;
//    private DriveTrain driveTrain;
//    private final ProfiledPIDController pidController = new ProfiledPIDController(0.009, 0, 0,
//            new TrapezoidProfile.Constraints(100, 60)); // Adjust PID gains as needed
//
//    public TurnInAngle(DriveTrain driveTrain, double setPoint) {
//        this.driveTrain = driveTrain;
//        this.setPoint = setPoint;
//        addRequirements(driveTrain);
//        pidController.setTolerance(3); // Adjust tolerance as needed
//    }
//
//    @Override
//    public void initialize() {
//        double currentAngle = driveTrain.getYaw();
//        // Calculate the setpoint within a desired range, for example, -180 to 180 degrees
////        this.setPoint = (currentAngle + setPoint + 180) % 360 - 180;
//        this.setPoint = currentAngle + setPoint;
//
//
//        pidController.reset(currentAngle);
//        pidController.setGoal(setPoint);
//    }
//
//    @Override
//    public void execute() {
//        double rotation = pidController.calculate(driveTrain.getYaw());
//        driveTrain.drive(0, rotation);
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        driveTrain.stopDrive();
//    }
//
//    @Override
//    public boolean isFinished() {
//        return pidController.atGoal();
//    }
//}



public class TurnInAngle extends Command {
    private final DriveTrain drive;
    private double leftDistance, leftDistance2;
    private double rightDistance, rightDistance2;
    private final double targetAngle;
    private double leftSpeed, rightSpeed;


    public TurnInAngle(DriveTrain drive, double targetAngle) {
        this.drive = drive;
        this.targetAngle = targetAngle;

        // Calculate the distance each side of the drive train must travel
        // Assuming a method getWheelbaseWidth() that returns the wheelbase width
        double wheelbaseWidth = drive.getWheelbaseWidth();
        double distancePerDegree = (Math.PI * wheelbaseWidth) / 360.0;
        double totalDistance = distancePerDegree * targetAngle;
        totalDistance = Math.abs(totalDistance);

        if (targetAngle>0) {
            this.leftDistance = totalDistance;
            this.rightDistance = -totalDistance;
        }
        else {
            this.leftDistance = -totalDistance;
            this.rightDistance = totalDistance;
        }

        addRequirements(drive);
    }

    @Override
    public void initialize() {

        this.leftDistance2 = drive.getLeftTravelDistanceMetres() + leftDistance;
        this.rightDistance2 = drive.getRightTravelDistanceMetres() + rightDistance;
        System.out.println("left target: " + this.leftDistance2);
        System.out.println("right target: " + rightDistance2);
        if (leftDistance2 > drive.getLeftTravelDistanceMetres()) {
            leftSpeed = 0.6;
            rightSpeed = -0.6;
        }else {
            leftSpeed = -0.6;
            rightSpeed = 0.6;
        }
    }

    @Override
    public void execute() {
        // Update speeds based on distance left to cover, potentially using PID
        // Here we're using constant speeds for simplicity
        drive.tankDrive(leftSpeed, rightSpeed);
    }

    @Override
    public boolean isFinished() {
        // Check if we've reached the target distance
        double leftDist = drive.getLeftTravelDistanceMetres();
        double rightDist = drive.getRightTravelDistanceMetres();

        return (Math.abs(leftDist) >= Math.abs(leftDistance2) && Math.abs(rightDist) >= Math.abs(rightDistance2)) ||
                Math.abs(leftDist) <= Math.abs(leftDistance2) && Math.abs(rightDist) <= Math.abs(rightDistance2);
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopDrive();
    }
}

