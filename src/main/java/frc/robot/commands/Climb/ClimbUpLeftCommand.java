//package frc.robot.commands.Climb;
//
//import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.subsystems.ClimbLeft;
//
//import java.util.function.DoubleSupplier;
//
//public class ClimbUpLeftCommand extends Command {
//    ClimbLeft climbLeft;
//    DoubleSupplier speed;
//    public ClimbUpLeftCommand(ClimbLeft climbLeft, DoubleSupplier speed){
//        this.climbLeft = climbLeft;
//        this.speed = speed;
//        addRequirements(climbLeft);
//    }
//    public void initialize(){
//    }
//    public void execute() {
//        this.climbLeft.upClimb(this.speed.getAsDouble());
//    }
//
//    public void end(boolean interrupted){
//        this.climbLeft.stopClimb();
//    }
//    public boolean isFinished(){return super.isFinished();}
//}
//
