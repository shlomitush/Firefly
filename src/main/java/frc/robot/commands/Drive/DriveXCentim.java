package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.PollyIntake;

public class DriveXCentim extends Command {
    private double targetPosition;
    private double targetPosition1;
    private double startDist;


    private final DriveTrain drive;
    private final FloorIntake floorIntake;
    private PollyIntake pollyIntake;


    public DriveXCentim(DriveTrain drive, FloorIntake floorIntake, PollyIntake pollyIntake,double targetPosition) {
        this.targetPosition1 = targetPosition;
        System.out.println("the target position is: " + this.targetPosition);
        this.drive = drive;
        this.floorIntake = floorIntake;
        this.pollyIntake = pollyIntake;
        addRequirements(drive, floorIntake, pollyIntake);
    }

    @Override
    public void initialize() {
//        drive.resetpos();
//        this.targetPosition
//        drive.getRightTravelDistanceMetres();
        startDist = drive.getRightTravelDistanceMetres();
        this.targetPosition = this.targetPosition1 + startDist;
        double speed = targetPosition > startDist ? -0.35 : 0.35;
        System.out.println("speed is: " + speed);
        drive.drive(speed, 0);
        System.out.println("the target position is: " + this.targetPosition);
        floorIntake.pickUpFromFloor();
        pollyIntake.floorIn();

    }

    @Override
    public void execute()    {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        drive.stopDrive();
        pollyIntake.stop();
        floorIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return (targetPosition > startDist && drive.getRightTravelDistanceMetres() >= targetPosition) ||

                (targetPosition < startDist && drive.getRightTravelDistanceMetres() <= targetPosition);
    }
}
