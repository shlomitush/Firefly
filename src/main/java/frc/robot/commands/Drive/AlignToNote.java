package frc.robot.commands.Drive;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ProfiledPIDCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utilities.LimelightHelpers;

public class AlignToNote extends Command {

//    private final ProfiledPIDController profiledPIDController = new ProfiledPIDController(0.3, 0, 0,
//            new TrapezoidProfile.Constraints(1, 0.2));
    private final ProfiledPIDController pidController = new ProfiledPIDController(0.03, 0, 0,
        new TrapezoidProfile.Constraints(100, 60));
    private final DriveTrain drive;
    private final String name = "limelight-limlim";





    public AlignToNote(DriveTrain drive) {
        this.drive = drive;
        addRequirements(drive);

        pidController.setTolerance(1, 200);


    }

    @Override
    public void initialize() {

        
        pidController.reset(LimelightHelpers.getTX(name));
    }

    @Override
    public void execute() {
        boolean hasTarget = LimelightHelpers.getTV(name);
        double pixel = LimelightHelpers.getTX(name);


        SmartDashboard.putNumber("pixel", pixel);
        SmartDashboard.putBoolean("hasTarget", hasTarget);

        if (!hasTarget) {
            this.drive.stopDrive();
        }else {
            var output = pidController.calculate(pixel);
            this.drive.drive(0, output);
            SmartDashboard.putNumber("speed", output);
            SmartDashboard.putNumber("time", Timer.getFPGATimestamp());
            SmartDashboard.putNumber("setpoint", pidController.getSetpoint().position);
        }
    }

    @Override
    public void end(boolean interrupted) {
        this.drive.stopDrive();
    }

    @Override
    public boolean isFinished() {
        return pidController.atGoal();
//        return Math.abs(LimelightHelpers.getTX(name)) < 1.5;
    }
}
