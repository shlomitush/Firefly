package frc.robot.commands.Drive;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class DriveTrainCommand extends Command {
    private DriveTrain m_driveTrain;
    private DoubleSupplier speed;
    private BooleanSupplier slow;
    private DoubleSupplier rot;

    public DriveTrainCommand(DriveTrain driveTrain, DoubleSupplier speed, DoubleSupplier rot, BooleanSupplier slow){
        m_driveTrain = driveTrain;
        addRequirements(driveTrain);
        this.speed = speed;
        this.rot = rot;
        this.slow = slow;
    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
//        if (speed.getAsDouble() > 0) m_driveTrain.drive(Math.pow(speed.getAsDouble(), 1.5), Math.pow(rot.getAsDouble(), 1.2));
//        else m_driveTrain.drive(-Math.pow(speed.getAsDouble(), 1.5), -Math.pow(rot.getAsDouble(), 1.2));
        if (slow.getAsBoolean()) {
            m_driveTrain.drive(speed.getAsDouble() / 3.0, rot.getAsDouble() / 3.0);
        } else {
            m_driveTrain.drive(speed.getAsDouble(), rot.getAsDouble());

        }
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
