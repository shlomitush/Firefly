//package frc.robot.commands.Climb;
//
//import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.subsystems.Climb;
//import frc.robot.subsystems.ClimbLeft;
//
//import java.util.function.DoubleSupplier;
//
//public class ClimbDownLeftCommand extends Command {
//    ClimbLeft climbLeft;
//    private DoubleSupplier speed;
//
//    public ClimbDownLeftCommand(ClimbLeft climbLeft, DoubleSupplier speed){
//        this.climbLeft = climbLeft;
//        this.speed = speed;
//        addRequirements(climbLeft);
//    }
//    public void initialize(){
//    }
//    public void execute() {
//        this.climbLeft.downClimb(this.speed.getAsDouble());
//    }
//
//    public void end(boolean interrupted){
//        this.climbLeft.stopClimb();
//    }
//    public boolean isFinished(){return super.isFinished();}
//}
