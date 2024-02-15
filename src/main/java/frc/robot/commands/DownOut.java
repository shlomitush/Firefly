package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.FlyWheel;
import frc.robot.subsystems.PollyIntake;

public class DownOut extends Command {
    private PollyIntake pollyIntake;
    private FlyWheel flyWheel;
    private FloorIntake floorIntake;

    public DownOut(PollyIntake pollyIntake, FlyWheel flyWheel, FloorIntake floorIntake) {
        this.flyWheel = flyWheel;
        this.pollyIntake = pollyIntake;
        this.floorIntake = floorIntake;
        addRequirements(pollyIntake, flyWheel, floorIntake);
    }

    @Override
    public void initialize() {
        this.pollyIntake.downOut();
        //wait 0.5 s
        this.flyWheel.downOut();
        // wait 0.2 s
        this.floorIntake.downOut();
    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        this.flyWheel.stop();
        this.pollyIntake.stop();
        this.floorIntake.stop();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
