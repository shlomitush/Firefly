package frc.robot.commands.Intake;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.PollyIntake;


public class FloorIn extends Command {
    private final PollyIntake pollyIntake;
    private final FloorIntake floorIntake;
    private final CommandXboxController driverController1;
    private final CommandXboxController driverController2;

    public FloorIn(PollyIntake pollyIntake, FloorIntake floorIntake, CommandXboxController driverController1, CommandXboxController driverController2) {
        this.pollyIntake = pollyIntake;
        this.floorIntake = floorIntake;
        this.driverController1 = driverController1;
        this.driverController2 = driverController2;

        addRequirements(pollyIntake, floorIntake);
    }

    @Override
    public void initialize() {
        floorIntake.pickUpFromFloor();
        this.pollyIntake.floorIn();
    }

    @Override
    public void execute() {
        if (pollyIntake.noteIn()) {
            driverController1.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0.7);
            driverController2.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0.7);
        }
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
