package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;

public class ClimbDownLeftCommand extends Command {
    Climb climb;
    public ClimbDownLeftCommand(Climb climb){
        this.climb = climb;
        addRequirements(climb);
    }
    public void initialize(){
        this.climb.downClimb();
    }
    public void execute() {
    }

    public void end(boolean interrupted){
        this.climb.stopClimb();
    }
    public boolean isFinished(){return super.isFinished();}
}
