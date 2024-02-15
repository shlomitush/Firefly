package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PollyIntake;


public class PollyIntakeCommand extends Command {

    private PollyIntake pollyIntake;

    public PollyIntakeCommand(PollyIntake pollyIntake) {
        this.pollyIntake = pollyIntake;
        addRequirements(pollyIntake);
    }

    @Override
    public void initialize() {
        this.pollyIntake.floorIn();

    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        this.pollyIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
