package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class DriveXCentim extends Command {
    private double targetPosition;
    private double targetPosition1;
    private double startDist;


    private final DriveTrain drive;


    public DriveXCentim(DriveTrain drive, double targetPosition) {
        this.targetPosition1 = targetPosition;
        System.out.println("the target position is: " + this.targetPosition);
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
//        drive.resetpos();
//        this.targetPosition
//        drive.getRightTravelDistanceMetres();
        startDist = drive.getRightTravelDistanceMetres();
        this.targetPosition = this.targetPosition1 + startDist;
        double speed = targetPosition > startDist ? -0.2 : 0.2;
        System.out.println("speed is: " + speed);
        drive.drive(speed, 0);
        System.out.println("the target position is: " + this.targetPosition);

    }

    @Override
    public void execute()    {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return (targetPosition > startDist && drive.getRightTravelDistanceMetres() >= targetPosition) ||

                (targetPosition < startDist && drive.getRightTravelDistanceMetres() <= targetPosition);
    }
}
