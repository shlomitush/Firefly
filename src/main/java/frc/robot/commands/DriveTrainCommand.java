package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

import java.util.function.DoubleSupplier;

public class DriveTrainCommand extends Command {
    private DriveTrain m_driveTrain;
    private DoubleSupplier speed;
    private DoubleSupplier rot;

    public DriveTrainCommand(DriveTrain driveTrain, DoubleSupplier speed, DoubleSupplier rot){
        m_driveTrain = driveTrain;
        addRequirements(driveTrain);
        this.speed = speed;
        this.rot = rot;
    }


    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_driveTrain.drive(speed.getAsDouble(), rot.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        m_driveTrain.drive(0,0);
    }

    @Override
    public boolean isFinished() {
        return super.isFinished();
    }
}
