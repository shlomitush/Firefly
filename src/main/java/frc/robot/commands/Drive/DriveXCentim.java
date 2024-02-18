package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;

public class DriveXCentim extends Command {
    private double targetPosition;
    private final DriveTrain drive;


    public DriveXCentim(DriveTrain drive, double targetPosition) {
        this.targetPosition = targetPosition;
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        drive.resetpos();
        double speed = targetPosition > 0 ? 0.55 : -0.55;
        drive.drive(speed, 0);
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return (targetPosition > 0 && drive.getRightTravelDistanceMetres() >= targetPosition) ||
                (targetPosition < 0 && drive.getRightTravelDistanceMetres() <= targetPosition);
    }
}
