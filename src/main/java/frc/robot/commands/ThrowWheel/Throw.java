package frc.robot.commands.ThrowWheel;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.PollyIntake;
import edu.wpi.first.wpilibj.Timer;


public class Throw extends Command {
    private PollyIntake pollyIntake;
    private FlyWheel flyWheel;
    private Timer timer = new Timer();


    public Throw(PollyIntake pollyIntake, FlyWheel flyWheel) {
        this.flyWheel = flyWheel;
        this.pollyIntake = pollyIntake;
        addRequirements(pollyIntake, flyWheel);

    }

    @Override
    public void initialize() {
        this.flyWheel.throwWheel();
//        this.timer.advanceIfElapsed(1.5);
        this.pollyIntake.upOut();
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
