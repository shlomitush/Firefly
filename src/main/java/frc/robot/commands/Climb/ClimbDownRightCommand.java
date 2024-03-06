//package frc.robot.commands.Climb;
//
//import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.subsystems.ClimbRight;
//
//import java.util.function.DoubleSupplier;
//
//public class ClimbDownRightCommand extends Command {
//    DoubleSupplier speed;
//    ClimbRight climbRight;
//    public ClimbDownRightCommand(ClimbRight climbRight, DoubleSupplier speed){
//        this.speed = speed;
//        this.climbRight = climbRight;
//        addRequirements(climbRight);
//    }
//    public void initialize(){
//    }
//    public void execute() {
//        this.climbRight.downClimb(speed.getAsDouble());
//    }
//
//    public void end(boolean interrupted){
//        this.climbRight.stopClimb();
//    }
//    public boolean isFinished(){return super.isFinished();}
//}
