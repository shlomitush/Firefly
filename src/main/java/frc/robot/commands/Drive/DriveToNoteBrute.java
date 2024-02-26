package frc.robot.commands.Drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.PollyIntake;
import frc.robot.utilities.LimelightHelpers;

public class DriveToNoteBrute extends Command {

    private final DriveTrain drive;
    private final FloorIntake floorIntake;
    private PollyIntake pollyIntake;


    public DriveToNoteBrute(DriveTrain drive, FloorIntake floorIntake, PollyIntake pollyIntake) {
        this.drive = drive;
        this.floorIntake = floorIntake;
        this.pollyIntake = pollyIntake;
        addRequirements(drive, floorIntake);
    }

    @Override
    public void initialize() {
        floorIntake.pickUpFromFloor();
        drive.drive(0.3, 0);
        this.pollyIntake.floorIn();
    }

    @Override
    public void execute() {
        drive.drive(0.25, 0);
    }

    @Override
    public void end(boolean interrupted) {
        this.drive.stopDrive();
        this.floorIntake.stop();
        this.pollyIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();

//        return !this.hasTarget;
    }
}
