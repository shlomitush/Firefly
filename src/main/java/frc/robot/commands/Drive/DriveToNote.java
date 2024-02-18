package frc.robot.commands.Drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FloorIntake;
import frc.robot.utilities.LimelightHelpers;

public class DriveToNote extends Command {

    private final PIDController pidController = new PIDController(0.015, 0, 0);
    private final DriveTrain drive;
    private final FloorIntake floorIntake;
    private final String name = "limelight-limlim";
    private boolean hasTarget = LimelightHelpers.getTV(name);

    public DriveToNote(DriveTrain drive, FloorIntake floorIntake) {
        this.drive = drive;
        this.floorIntake = floorIntake;
        addRequirements(drive, floorIntake);
    }

    @Override
    public void initialize() {
        floorIntake.pickUpFromFloor();
        drive.drive(-0.3, 0);
    }

    @Override
    public void execute() {
        drive.drive(-0.15, 0);
//        if (!hasTarget) {
//            this.drive.stopDrive();
//        }
    }

    @Override
    public void end(boolean interrupted) {
        this.drive.stopDrive();
        this.floorIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();

//        return !this.hasTarget;
    }
}
