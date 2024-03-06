package frc.robot.commands.ThrowWheel;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.PollyIntake;

public class ThrowAMP extends Command {
    private PollyIntake pollyIntake;
    private FlyWheel flyWheel;

    public ThrowAMP(PollyIntake pollyIntake, FlyWheel flyWheel) {
        this.flyWheel = flyWheel;
        this.pollyIntake = pollyIntake;
        addRequirements(pollyIntake, flyWheel);
    }

    @Override
    public void initialize() {
        this.flyWheel.throwAMP();
        this.pollyIntake.throwAMP();
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public void end(boolean interrupted) {
        pollyIntake.stop();
        flyWheel.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
