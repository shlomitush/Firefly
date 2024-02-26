package frc.robot;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Climb.ClimbDownCommand;
import frc.robot.commands.Climb.ClimbUpCommand;
import frc.robot.commands.Drive.*;
import frc.robot.commands.Intake.FeederIn;
import frc.robot.commands.Intake.FloorIn;
import frc.robot.commands.ThrowWheel.BackALittle;
import frc.robot.commands.ThrowWheel.StopThrow;
import frc.robot.commands.ThrowWheel.Throw;
import frc.robot.commands.ThrowWheel.ThrowAMP;
import frc.robot.subsystems.*;

//hey
public class RobotContainer {
  private final Robot robot = new Robot();
  private RoundInfo roundInfo = new RoundInfo(robot);


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

//  private final driveTrainCommand m_driveTrainCommand = new driveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(2));
//  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_Floor_intake);


  // commands
  private final DriveTrainCommand m_driveTrainCommand = new DriveTrainCommand(m_driveTrain, driverController1::getLeftY, ()->
          driverController1.getRawAxis(4));
  private final ClimbUpCommand climbUpCommand = new ClimbUpCommand(climb);
  private final ClimbDownCommand climbDownCommand = new ClimbDownCommand(climb);
  private final FeederIn feederIn = new FeederIn(pollyIntake, flyWheel);
  private final FloorIn floorIn = new FloorIn(pollyIntake, floorIntake);
  private final Throw aThrow = new Throw(pollyIntake, flyWheel);
  private final BackALittle backALittle = new BackALittle(pollyIntake, flyWheel);
  private final DownOut downOut = new DownOut(pollyIntake, flyWheel, floorIntake);
  private final UpOut upOut = new UpOut(pollyIntake, flyWheel, floorIntake);
  private final SlowUpOut slowUpOut = new SlowUpOut(pollyIntake, flyWheel, floorIntake);
  private final StopThrow stopThrow = new StopThrow(pollyIntake, flyWheel);
  private final AlignToNote alignToNote = new AlignToNote(m_driveTrain);
  private final DriveToNote driveToNote = new DriveToNote(m_driveTrain, floorIntake);
  private final ThrowAMP throwAMP = new ThrowAMP(pollyIntake, flyWheel);
  private final DriveXCentim driveXCentim = new DriveXCentim(m_driveTrain, 10);


  private final Command completeThrow = backALittle.withTimeout(1.8).andThen(aThrow).withTimeout(2);
  private final Command autoNote = alignToNote.repeatedly().withTimeout(1).andThen(driveToNote);


  public RobotContainer() {
    configureBindings();
//    SmartDashboard.putData(m_driveTrainCommand);
  }


  private void configureBindings() {
    // controller 1 - driver:
    driverController1.rightBumper().whileTrue(floorIn);
    driverController1.povUp().whileTrue(upOut);
    driverController1.povDown().whileTrue(downOut);
    driverController1.a().onTrue(new TurnInAngle(m_driveTrain, 180));
    driverController1.rightTrigger().whileTrue(climbUpCommand);
    driverController1.leftTrigger().whileTrue(climbDownCommand);

    // controller 2 - buttons
    driverController2.rightBumper().whileTrue(backALittle.withTimeout(1.8).andThen(aThrow));
    driverController2.rightBumper().onFalse(stopThrow);
    driverController2.x().whileTrue(feederIn);
    driverController2.b().whileTrue(throwAMP);
    driverController2.povUp().whileTrue(upOut);
    driverController2.povDown().whileTrue(downOut);
    driverController2.rightTrigger().whileTrue(climbUpCommand);
    driverController2.leftTrigger().whileTrue(climbDownCommand);
    driverController2.a().whileTrue(alignToNote.repeatedly().withTimeout(1).andThen(driveToNote));


    m_driveTrain.setDefaultCommand(m_driveTrainCommand);

//    m_driverController.a().whileTrue(alignToNote.withTimeout(1).andThen(driveToNote));
//    m_driverController.a().onTrue(alignToNote.andThen(driveToNote).withTimeout(2));

  }




  public Command getAutonomousCommand() {

//    return Autos.exampleAuto(m_exampleSubsystem);
    return new BackALittle(pollyIntake, flyWheel).withTimeout(1.0)
            .andThen(new Throw(pollyIntake, flyWheel).withTimeout(2.5),
                    new DriveXCentim(m_driveTrain ,-50),
                    new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2),
                    new DriveXCentim(m_driveTrain, 250), backALittle.withTimeout(2.0),
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
    boolean limlim = robot.getLimLimForeNote();
    return completeThrow
            .andThen(new DriveXCentim(m_driveTrain ,-50),
                    limlim ? autoNote :
                            new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2),
                    new DriveXCentim(m_driveTrain, 250), backALittle.withTimeout(2.0),
                    new WaitCommand(0.5),
                    completeThrow);
  }

  public Command getAutonomousCommandLeft() {
    boolean limlim = robot.getLimLimForeNote();

    return new TurnInAngle(m_driveTrain, 70)
            .andThen(completeThrow,
                    new TurnInAngle(m_driveTrain, -70),
                    new DriveXCentim(m_driveTrain ,-50),
                    limlim ? autoNote :
                            new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2),
                    new DriveXCentim(m_driveTrain, 300), backALittle.withTimeout(2.0), // לבדוק כמה בדיוק - זה קריטי
                    new TurnInAngle(m_driveTrain, 70),
                    completeThrow);
  }

  public Command getAutonomousCommandRight() {
    boolean limlim = robot.getLimLimForeNote();

    return new TurnInAngle(m_driveTrain, -70)
            .andThen(completeThrow,
                    new TurnInAngle(m_driveTrain, 70),
                    new DriveXCentim(m_driveTrain, -50),
                    limlim ? autoNote :
                            new DriveToNoteBrute(m_driveTrain, floorIntake, pollyIntake).withTimeout(2),
                    new DriveXCentim(m_driveTrain, 300), backALittle.withTimeout(2.0), // לבדוק כמה בדיוק - זה קריטי
                    new TurnInAngle(m_driveTrain, -70),
                    completeThrow);
    }
}
