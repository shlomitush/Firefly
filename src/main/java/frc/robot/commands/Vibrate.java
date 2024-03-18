package frc.robot.commands;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.PollyIntake;

public class Vibrate extends Command {
    private final PollyIntake pollyIntake;
    private final CommandXboxController driverController1;
    private final CommandXboxController driverController2;
    public Vibrate(PollyIntake pollyIntake, CommandXboxController driverController1,
                   CommandXboxController driverController2) {
        this.pollyIntake = pollyIntake;
        this.driverController1 = driverController1;
        this.driverController2 = driverController2;
    }

    @Override
    public void initialize() {
        if (pollyIntake.noteIn()) {
            driverController1.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0.7);
            driverController2.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0.7);
        }
        else {
            driverController1.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0);
            driverController2.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0);
        }
    }

    @Override
    public void execute() {
        if (pollyIntake.noteIn()) {
            driverController1.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0.7);
            driverController2.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0.7);
        }
        else {
            driverController1.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0);
            driverController2.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0);
        }
    }

    @Override
    public void end(boolean interrupted) {
        driverController1.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0);
        driverController2.getHID().setRumble(GenericHID.RumbleType.kBothRumble, 0);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
