//package frc.robot.commands.Climb;
//
//import edu.wpi.first.wpilibj2.command.Command;
//import frc.robot.subsystems.ClimbLeft;
//import frc.robot.subsystems.ClimbRight;
//
//import java.util.function.DoubleSupplier;
//
//public class ClimbUpRightCommand extends Command {
//    ClimbRight climbRight;
//    DoubleSupplier speed;
//    public ClimbUpRightCommand(ClimbRight climbRight, DoubleSupplier speed){
//        this.climbRight = climbRight;
//        this.speed = speed;
//        addRequirements(climbRight);
//    }
//    public void initialize(){
//    }
//    public void execute() {
//        this.climbRight.upClimb(speed.getAsDouble());
//    }
//
//    public void end(boolean interrupted){
//        this.climbRight.stopClimb();
//    }
//    public boolean isFinished(){return super.isFinished();}
//}
