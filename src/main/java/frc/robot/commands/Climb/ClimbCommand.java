package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbLeft;
import frc.robot.subsystems.ClimbRight;

import java.util.function.DoubleSupplier;

public class ClimbCommand extends Command {
    private final ClimbLeft climbLeft;
    private final ClimbRight climbRight;
    private final DoubleSupplier speedLeft;
    private final DoubleSupplier speedRight;
    public ClimbCommand(ClimbRight climbRight, ClimbLeft climbLeft, DoubleSupplier speedRight,
                        DoubleSupplier speedLeft){
        this.climbLeft = climbLeft;
        this.climbRight = climbRight;
        this.speedLeft = speedLeft;
        this.speedRight = speedRight;
        addRequirements(climbLeft, climbRight);
    }
    public void initialize(){
    }
    public void execute() {
        this.climbLeft.climb(this.speedLeft.getAsDouble());
        this.climbRight.climb(this.speedRight.getAsDouble());
    }

    public void end(boolean interrupted){
        this.climbLeft.stopClimb();
    }
    public boolean isFinished(){return super.isFinished();}
}
