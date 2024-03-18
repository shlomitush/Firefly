package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbLeft;
import frc.robot.subsystems.ClimbRight;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class ClimbCommand extends Command {
    private final ClimbLeft climbLeft;
    private final ClimbRight climbRight;
    private final DoubleSupplier speedLeft;
    private final DoubleSupplier speedRight;
    private final BooleanSupplier safe;
    public ClimbCommand(ClimbRight climbRight, ClimbLeft climbLeft, DoubleSupplier speedRight,
                        DoubleSupplier speedLeft, BooleanSupplier safe){
        this.climbLeft = climbLeft;
        this.climbRight = climbRight;
        this.speedLeft = speedLeft;
        this.speedRight = speedRight;
        this.safe = safe;
        addRequirements(climbLeft, climbRight);
    }
    public void initialize(){
    }
    public void execute() {
        if (safe.getAsBoolean()) {
            this.climbLeft.climb(this.speedLeft.getAsDouble());
            this.climbRight.climb(this.speedRight.getAsDouble());
        }
        else {
            this.climbLeft.stopClimb();
            this.climbRight.stopClimb();
        }
    }

    public void end(boolean interrupted){
        this.climbLeft.stopClimb();
    }
    public boolean isFinished(){return super.isFinished();}
}
