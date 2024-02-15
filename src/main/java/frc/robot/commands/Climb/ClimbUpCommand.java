package frc.robot.commands.Climb;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;

public class ClimbUpCommand extends Command {
    Climb m_climb;
    public ClimbUpCommand(Climb m_climb){
        this.m_climb = m_climb;
        addRequirements(m_climb);
    }
    public void initialize(){
        this.m_climb.upClimb();
    }

    public void execute() {
    }
    public void end(boolean interrupted){
        this.m_climb.stopClimb();
    }
    public boolean isFinished(){return super.isFinished();}}