package frc.robot;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Climb.ClimbCommand;
import frc.robot.commands.Climb.ClimbDownCommand;
//import frc.robot.commands.Climb.ClimbDownRightCommand;
import frc.robot.commands.Climb.ClimbUpCommand;
//import frc.robot.commands.Climb.ClimbUpRightCommand;
//import frc.robot.commands.Climb.ClimbUpRightCommand;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Intake.FeederIn;
import frc.robot.commands.Intake.FloorIn;
import frc.robot.commands.ThrowWheel.BackALittle;
import frc.robot.commands.ThrowWheel.StopThrow;
import frc.robot.commands.ThrowWheel.Throw;
import frc.robot.commands.ThrowWheel.ThrowAMP;
import frc.robot.enums.LimeLightState;
import frc.robot.subsystems.*;

import java.util.function.DoubleSupplier;

//hey
public class RobotContainer {
  private boolean limlim;

  public RobotContainer(boolean limlim) {
    this.limlim = limlim;
    configureBindings();
  }

  public void setLimlim(LimeLightState limeLightState) {
    switch (limeLightState){
      case NO:
        this.limlim = false;
      case YES:
        this.limlim = true;
    }
  }
//  private final Robot robot = new Robot();
//  private RoundInfo roundInfo = new RoundInfo(robot.getAlliance());


  //  private final Intake m_intake = new Intake();
  private final CommandXboxController driverController1 = new CommandXboxController(OperatorConstants.kDriverControllerPort1);

  private final CommandXboxController driverController2 =
          new CommandXboxController(OperatorConstants.kDriverControllerPort2);

  // subsystems
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final FlyWheel flyWheel = new FlyWheel();
  private final FloorIntake floorIntake = new FloorIntake();
  private final Climb climb = new Climb();
  private final PollyIntake pollyIntake = new PollyIntake();
  private final ClimbRight climbRight = new ClimbRight();
  private final ClimbLeft climbLeft = new ClimbLeft();


  // commands
  private final DriveTrainCommand m_driveTrainCommand = new DriveTrainCommand(m_driveTrain, driverController1::getLeftY, ()->
          driverController1.getRawAxis(4), driverController1.leftBumper());

  private final ClimbCommand climbCommand = new ClimbCommand(climbRight, climbLeft,
          driverController2::getRightY, driverController2::getLeftY, driverController2.leftBumper());
//  private final ClimbUpRightCommand climbUpRightCommand = new ClimbUpRightCommand(new ClimbRight(),
//          driverController2::getRightY);


//  private final ClimbUpCommand climbUpCommand = new ClimbUpCommand(climb);
//  private final ClimbDownCommand climbDownCommand = new ClimbDownCommand(climb);
  private final FeederIn feederIn = new FeederIn(pollyIntake, flyWheel);
  private final FloorIn floorIn = new FloorIn(pollyIntake, floorIntake);
  private final Throw aThrow = new Throw(pollyIntake, flyWheel);
  private final BackALittle backALittle = new BackALittle(pollyIntake, flyWheel);
  private final DownOut downOut = new DownOut(pollyIntake, flyWheel, floorIntake);
  private final UpOut upOut = new UpOut(pollyIntake, flyWheel, floorIntake);
  private final SlowUpOut slowUpOut = new SlowUpOut(pollyIntake, flyWheel, floorIntake);
  private final StopThrow stopThrow = new StopThrow(pollyIntake, flyWheel);
  private final AlignToNote alignToNote = new AlignToNote(m_driveTrain);
  private final DriveToNote driveToNote = new DriveToNote(m_driveTrain, floorIntake, pollyIntake);
  private final ThrowAMP throwAMP = new ThrowAMP(pollyIntake, flyWheel);


//  public RobotContainer() {
//    configureBindings();
////    SmartDashboard.putData(m_driveTrainCommand);
//  }


  private void configureBindings() {
    // controller 1 - driver:
    driverController1.rightBumper().whileTrue(floorIn);
    driverController1.povUp().whileTrue(upOut);
    driverController1.povDown().whileTrue(downOut);
//    driverController1.a().onTrue(new TurnInAngle(m_driveTrain, 180).withTimeout(0.4));
    driverController1.rightTrigger().whileTrue(new ClimbUpCommand(climb));
    driverController1.leftTrigger().whileTrue(new ClimbDownCommand(climb).withTimeout(2));

    // controller 2 - buttons
    driverController2.rightBumper().whileTrue(completeThrow());
    driverController2.rightBumper().onFalse(stopThrow);
    driverController2.x().whileTrue(feederIn);
    driverController2.b().whileTrue(throwAMP);
    driverController2.povUp().whileTrue(upOut);
    driverController2.povDown().whileTrue(downOut);
    driverController2.rightTrigger().whileTrue(new ClimbUpCommand(climb));
    driverController2.leftTrigger().whileTrue(new ClimbDownCommand(climb));
    driverController2.a().whileTrue(autoNote());
//    driverController2.y().onTrue(new TurnInAngle(m_driveTrain, 60));


    m_driveTrain.setDefaultCommand(m_driveTrainCommand);
    climbLeft.setDefaultCommand(climbCommand);
    climbRight.setDefaultCommand(climbCommand);



//    m_driverController.a().whileTrue(alignToNote.withTimeout(1).andThen(driveToNote));
//    m_driverController.a().onTrue(alignToNote.andThen(driveToNote).withTimeout(2));

  }




  public Command getAutonomousCommand() {

    return new BackALittle(pollyIntake, flyWheel).withTimeout(1.0)
            .andThen(new Throw(pollyIntake, flyWheel).withTimeout(2.5),
                    new DriveXCentim(m_driveTrain,floorIntake, pollyIntake, -50).withTimeout(5),
                    new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2),
                    new DriveXCentim(m_driveTrain, floorIntake,pollyIntake, 250),
                    new WaitCommand(0.5),
                    new BackALittle(pollyIntake, flyWheel).withTimeout(2.0),
                    new Throw(pollyIntake, flyWheel).withTimeout(3.0));
//            .andThen()
//            .andThen(driveToNote).withTimeout(4)
////            .andThen(alignToNote).andThen(driveToNote).withTimeout(2)
//            .andThen(new DriveXCentim(m_driveTrain, 110)).andThen(new WaitCommand(0.5))
//            .andThen( backALittle.withTimeout(1.5)).andThen(aThrow)

  }


  public Command getAutonomousCommandCenter() {
    return completeThrow()
            .andThen(new DriveXCentim(m_driveTrain , floorIntake, pollyIntake, -155).withTimeout(5),
//                    limlim ? autoNote() :
                            new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -50).withTimeout(5),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 215).withTimeout(5),
//                    new WaitCommand(0.2),
                    completeThrow().withTimeout(3));
  }

  public Command getAutonomousCommandLeft() {
//    return new TurnInAngle(m_driveTrain, 60);
    return completeThrow()
            .andThen(new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -20).withTimeout(3),
                    new WaitCommand(0.2),
                    new TurnInAngle(m_driveTrain, 70),
                    new WaitCommand(0.2),
                    new DriveXCentim(m_driveTrain , floorIntake, pollyIntake, -160).withTimeout(3),
                    limlim ? autoNote() :
                            new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -50).withTimeout(3),
                    new WaitCommand(0.2),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 210).withTimeout(3),
                    new WaitCommand(0.2),
                    new TurnInAngle(m_driveTrain, -70),
                    new WaitCommand(0.2),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 25).withTimeout(3),
                    new FeederIn(pollyIntake, flyWheel).withTimeout(0.2),
                    completeThrow());
  }

  public Command getAutonomousCommandRight() {
    return completeThrow()
            .andThen(new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -20).withTimeout(3),
                    new WaitCommand(0.2),
                    new TurnInAngle(m_driveTrain, -70),
                    new WaitCommand(0.2),
                    new DriveXCentim(m_driveTrain , floorIntake, pollyIntake, -160).withTimeout(3),
                    limlim ? autoNote() :
                            new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -50).withTimeout(3),
                    new WaitCommand(0.2),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 210).withTimeout(3),
                    new WaitCommand(0.2),
                    new TurnInAngle(m_driveTrain, 70),
                    new WaitCommand(0.2),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 25).withTimeout(3),
                    new FeederIn(pollyIntake, flyWheel).withTimeout(0.2),
                    completeThrow());
//    return completeThrow()
//            .andThen(new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -20).withTimeout(3),
//                    new WaitCommand(0.2),
//                    new TurnInAngle(m_driveTrain, -70),
//                    new DriveXCentim(m_driveTrain , floorIntake, pollyIntake, -160).withTimeout(5),
//                    limlim ? autoNote() :
//                            new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2).withTimeout(5),
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 210).withTimeout(5),
//                    new WaitCommand(0.2),
//                    new TurnInAngle(m_driveTrain, 70),
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 25).withTimeout(5),
//                    completeThrow());
//    return new TurnInAngle(m_driveTrain, 70).andThen(
//            new WaitCommand(0.3),
//            new TurnInAngle(m_driveTrain, -70)
//    );
//    return completeThrow()
//            .andThen(new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -20).withTimeout(5),
//                    new TurnInAngle(m_driveTrain, -60).withTimeout(0.5),
//                    new DriveXCentim(m_driveTrain , floorIntake, pollyIntake, -160).withTimeout(5),
//                    limlim ? autoNote() :
//                            new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2).withTimeout(5),
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 210).withTimeout(5),
//                    new TurnInAngle(m_driveTrain, 60).withTimeout(5),
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 25).withTimeout(5),
//                    completeThrow());
//    return new TurnInAngle(m_driveTrain, -70)
//            .andThen(completeThrow,
//                    new TurnInAngle(m_driveTrain, 70),
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -50),
//                    limlim ? autoNote() :
//                            new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2),
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 300), backALittle.withTimeout(2.0), //
//                    // לבדוק כמה
//                    // בדיוק - זה קריטי
//                    new TurnInAngle(m_driveTrain, -70),
//                    completeThrow);
    }

  public Command getAutonomousCommandOUT() {
    return completeThrow().withTimeout(2)
            .andThen(new DriveTrainCommand(m_driveTrain, ()-> 0.8, ()-> 0, ()->false).withTimeout(1));
//                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -400).withTimeout(15));
  }

  public Command getAutonomousCommandOUTSideBlue() {
    return completeThrow()
            .andThen(new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -150).withTimeout(5),
                    new TurnInAngle(m_driveTrain, 70).withTimeout(5).withTimeout(5),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 200).withTimeout(5)
            );
  }

  public Command getAutonomousCommandOUTSideRed() {
    return completeThrow()
            .andThen(new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, -150).withTimeout(5),
                    new TurnInAngle(m_driveTrain, -70).withTimeout(5).withTimeout(5),
                    new DriveXCentim(m_driveTrain, floorIntake, pollyIntake, 200).withTimeout(5)
            );
  }

public Command completeThrow(){
    return new BackALittle(pollyIntake, flyWheel).withTimeout(0.7).andThen(new Throw(pollyIntake, flyWheel)).withTimeout(2);
}
public Command autoNote() {
  return new AlignToNote(m_driveTrain).repeatedly().withTimeout(1.5).andThen(new DriveXCentim(m_driveTrain, floorIntake
          ,pollyIntake, -50).withTimeout(2.5));
}
  public Command autoNoteLong() {
    return new AlignToNote(m_driveTrain).repeatedly().withTimeout(1.5).andThen(new DriveXCentim(m_driveTrain, floorIntake
            ,pollyIntake, -80).withTimeout(2.5));
  }
}
