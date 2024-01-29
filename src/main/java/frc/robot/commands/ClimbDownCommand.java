package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Climb;

public class ClimbDownCommand extends Command {
    Climb m_climb;
    public ClimbDownCommand(Climb m_climb){
        this.m_climb = m_climb;
        addRequirements(m_climb);
    }
    public void initialize(){
        this.m_climb.downClimb();
    }
    public void execute() {
    }

    public void end(boolean interrupted){
        this.m_climb.stopClimb();
    }
    public boolean isFinished(){return super.isFinished();}}