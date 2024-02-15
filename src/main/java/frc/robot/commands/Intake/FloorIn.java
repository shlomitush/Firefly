package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.PollyIntake;


public class FloorIn extends Command {
    private PollyIntake pollyIntake;
    private FloorIntake floorIntake;

    public FloorIn(PollyIntake pollyIntake, FloorIntake floorIntake) {

        this.pollyIntake = pollyIntake;
        this.floorIntake = floorIntake;
        addRequirements(pollyIntake, floorIntake);
    }

    @Override
    public void initialize() {
        floorIntake.pickUpFromFloor();
        this.pollyIntake.floorIn();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        this.floorIntake.stop();
        this.pollyIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
