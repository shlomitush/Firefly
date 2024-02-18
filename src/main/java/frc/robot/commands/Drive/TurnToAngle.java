package frc.robot.commands.Drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.DriveTrain;

public class TurnToAngle extends Command {

    private double setPoint;
    private DriveTrain driveTrain;
    private PIDController pidController = new PIDController(0.003, 0, 0);
    public Trigger isAtSetPoint = new Trigger(()->Math.abs(pidController.getPositionError() - 180) <= pidController.getPositionTolerance());


    public TurnToAngle(DriveTrain driveTrain, double setPoint) {
        this.driveTrain = driveTrain;
        this.setPoint = setPoint;

        addRequirements(driveTrain);

        pidController.enableContinuousInput(-180, 180);
//        pidController.enableContinuousInput(0, 360);

        pidController.setTolerance(7);
        pidController.setSetpoint(this.setPoint);
    }

    @Override
    public void execute() {
        double rotation = this.pidController.calculate(driveTrain.getRobotAngle(), this.setPoint);
        System.out.println("in execute, rotating at speed: " + rotation);
//        System.out.println("in execute, rotating at speed: " + rotation);

        driveTrain.drive(0, rotation);
//        driveTrain.drive(0, rotation);

        System.out.println(pidController.getPositionError());
    }

    @Override
    public void end(boolean interrupted) {
        driveTrain.stopDrive();
    }

    @Override
    public boolean isFinished() {
        System.out.println("in is finished error is: " + pidController.getPositionError());
        System.out.println(isAtSetPoint.getAsBoolean());
//        System.out.println("current position tolerance: " + pidController.getPositionTolerance());
//        System.out.println("current gyro" + pidController.get);
//        System.out.println("set point: " + pidController.getSetpoint());
//        double error = Math.abs(pidController.getPositionError());
//        return error <= pidController.getPositionTolerance();
//        return error < 10;
//        return isAtSetPoint.getAsBoolean();

        return this.isAtSetPoint.getAsBoolean();
    }
}
