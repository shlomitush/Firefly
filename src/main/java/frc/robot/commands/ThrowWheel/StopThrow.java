package frc.robot.commands.ThrowWheel;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.PollyIntake;

public class StopThrow extends Command {
    private PollyIntake pollyIntake;
    private FlyWheel flyWheel;


    public StopThrow(PollyIntake pollyIntake, FlyWheel flyWheel) {
        this.flyWheel = flyWheel;
        this.pollyIntake = pollyIntake;
        addRequirements(pollyIntake, flyWheel);

    }

    @Override
    public void initialize() {
        this.flyWheel.stop();
        this.pollyIntake.stop();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        this.flyWheel.stop();
        this.pollyIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
