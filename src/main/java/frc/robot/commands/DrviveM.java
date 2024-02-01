package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveTrain;


public class DriveMCommand extends Command {
    private DriveTrain m_drive;
    private double targetPosition;
    /** Creates a new DriveM. */
    public DriveM(DriveTrain m_drive, double targetPosition) {
        this.m_drive = m_drive;
        this.targetPosition = targetPosition;

        addRequirements(m_drive);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_drive.resetpos();
        double speed = targetPosition > 0 ? 0.55 : -0.55;
        m_drive.drive(speed, 0);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_drive.stopMotor();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return (targetPosition > 0 && m_drive.getRightTravelDistanceMetres() >= targetPosition) ||
                (targetPosition < 0 && m_drive.getRightTravelDistanceMetres() <= targetPosition);
    }
}


