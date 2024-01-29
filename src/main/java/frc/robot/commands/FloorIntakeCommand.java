package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.FloorIntake;


public class FloorIntakeCommand extends Command {
    private FloorIntake floorIntake;

    public FloorIntakeCommand(FloorIntake floorIntake) {
        this.floorIntake = floorIntake;
        addRequirements(floorIntake);
    }

    @Override
    public void initialize() {
        this.floorIntake.pickUpFromFloor ();
    }

    @Override
     public void execute() {
    }

    @Override
public void end (boolean interrupted){
        this.floorIntake.stopPickUpFromFloor();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}

