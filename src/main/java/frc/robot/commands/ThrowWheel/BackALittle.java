package frc.robot.commands.ThrowWheel;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.PollyIntake;

public class BackALittle extends Command {
    private PollyIntake pollyIntake;
    private FlyWheel flyWheel;
    private Timer timer = new Timer();


    public BackALittle(PollyIntake pollyIntake, FlyWheel flyWheel) {
        this.pollyIntake = pollyIntake;
        this.flyWheel = flyWheel;
        addRequirements(pollyIntake, flyWheel);

    }

    @Override
    public void initialize() {
        this.pollyIntake.backALittle();
        this.flyWheel.throwWheel();
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
