package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheel;

public class FlyWheelCommand extends Command {

private FlyWheel flyWheel;

    public FlyWheelCommand(FlyWheel flyWheel) {
        this.flyWheel = flyWheel;
        addRequirements(flyWheel);
    }

    @Override
    public void initialize() {
        this.flyWheel.throwWheel();

    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        this.flyWheel.stopThrow();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
