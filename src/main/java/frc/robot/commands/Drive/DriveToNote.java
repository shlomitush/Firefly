package frc.robot.commands.Drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.PollyIntake;
import frc.robot.utilities.LimelightHelpers;

public class DriveToNote extends Command {

//    private final PIDController pidController = new PIDController(0.008, 0, 0.00001);
    private final DriveTrain drive;
    private final FloorIntake floorIntake;
    private final PollyIntake pollyIntake;
    private final String name = "limelight-limlim";
    private boolean hasTarget = LimelightHelpers.getTV(name);

    public DriveToNote(DriveTrain drive, FloorIntake floorIntake, PollyIntake pollyIntake) {
        this.drive = drive;
        this.floorIntake = floorIntake;
        this.pollyIntake = pollyIntake;
        addRequirements(drive, floorIntake, pollyIntake);
    }

    @Override
    public void initialize() {
        floorIntake.pickUpFromFloor();
        pollyIntake.floorIn();
        drive.drive(0.3, 0);
    }

    @Override
    public void execute() {
        drive.drive(0.2, 0);
//        if (!hasTarget) {
//            this.drive.stopDrive();
//        }
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
