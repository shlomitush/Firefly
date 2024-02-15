package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.Climb.ClimbDownCommand;
import frc.robot.commands.Climb.ClimbUpCommand;
import frc.robot.commands.Drive.DriveTrainCommand;
import frc.robot.commands.Drive.TurnToAngle;
import frc.robot.commands.Intake.FeederIn;
import frc.robot.commands.Intake.FloorIn;
import frc.robot.commands.Intake.FloorIntakeCommand;
import frc.robot.commands.Intake.PollyIntakeCommand;
import frc.robot.commands.ThrowWheel.FlyWheelCommand;
import frc.robot.commands.ThrowWheel.Throw;
import frc.robot.subsystems.*;

//hey
public class RobotContainer {


  //  private final Intake m_intake = new Intake();
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveTrain m_driveTrain = new DriveTrain();
  private final FlyWheel flyWheel = new FlyWheel(6);
  private final FloorIntake floorIntake = new FloorIntake();
  private final Climb climb = new Climb();
  private final PollyIntake pollyIntake = new PollyIntake();

//  private final driveTrainCommand m_driveTrainCommand = new driveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(2));
//  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_Floor_intake);

  private final DriveTrainCommand m_driveTrainCommand = new DriveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(4));
  private final FlyWheelCommand flyWheelCommand = new FlyWheelCommand(flyWheel);
  private final FloorIntakeCommand floorIntakeCommand = new FloorIntakeCommand(floorIntake);
  private final ClimbUpCommand climbUpCommand = new ClimbUpCommand(climb);
  private final ClimbDownCommand climbDownCommand = new ClimbDownCommand(climb);
  private final PollyIntakeCommand pollyIntakeCommand = new PollyIntakeCommand(pollyIntake);

  private final FeederIn feederIn = new FeederIn(pollyIntake, flyWheel);


  private final FloorIn floorIn = new FloorIn(pollyIntake, floorIntake);
  private final Throw aThrow = new Throw(pollyIntake, flyWheel);
  private final DownOut downOut = new DownOut(pollyIntake, flyWheel, floorIntake);
  private final UpOut upOut = new UpOut(pollyIntake, flyWheel, floorIntake);





  public RobotContainer() {
    configureBindings();
//    SmartDashboard.putData(m_driveTrainCommand);
  }


  private void configureBindings() {
//    new Trigger(m_exampleSubsystem::exampleCondition).onTrue(new ExampleCommand(m_exampleSubsystem));
//    m_driverController.x().onTrue(m_intake.IntakeCommand());
//        m_driverController.a().onTrue(new intakeCommand(m_intake));
//
//

//    m_driveTrain.setDefaultCommand(new driveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(2)));
    m_driverController.rightBumper().whileTrue(aThrow);
    m_driverController.y().whileTrue(floorIn);
    m_driverController.x().whileTrue(feederIn);
    m_driverController.rightTrigger().whileTrue(upOut);
    m_driverController.leftTrigger().whileTrue(downOut);
//    m_driverController.rightTrigger().onTrue(climbUpCommand);
//    m_driverController.leftTrigger().onTrue(climbDownCommand);

    m_driveTrain.setDefaultCommand(m_driveTrainCommand);

  }




  public Command getAutonomousCommand() {

//    return Autos.exampleAuto(m_exampleSubsystem);
    return new TurnToAngle(m_driveTrain, 90);
//    return new FlyWheelCommand(flyWheel);
  }
}
