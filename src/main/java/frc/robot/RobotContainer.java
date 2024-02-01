package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.subsystems.*;

//hey
public class RobotContainer {


  //  private final Intake m_intake = new Intake();
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveTrain m_driveTrain = new DriveTrain();
  private final FlyWheel flyWheel = new FlyWheel();
  private final FloorIntake floorIntake = new FloorIntake();
  private final Climb climb = new Climb();
  private final SourceIntake sourceIntake = new SourceIntake();

//  private final driveTrainCommand m_driveTrainCommand = new driveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(2));
//  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_Floor_intake);

  private final DriveTrainCommand m_driveTrainCommand = new DriveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(4));
  private final FlyWheelCommand flyWheelCommand = new FlyWheelCommand(flyWheel);
  private final FloorIntakeCommand floorIntakeCommand = new FloorIntakeCommand(floorIntake);
  private final ClimbUpCommand climbUpCommand = new ClimbUpCommand(climb);
  private final ClimbDownCommand climbDownCommand = new ClimbDownCommand(climb);
//  private final SourceIntakeCommand sourceIntakeCommand = new SourceIntakeCommand(sourceIntake);


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
    m_driverController.rightBumper().whileTrue(flyWheelCommand);
    m_driverController.y().whileTrue(floorIntakeCommand);
//    m_driverController.x().whileTrue(sourceIntakeCommand)
    m_driverController.rightTrigger().onTrue(climbUpCommand);
    m_driverController.leftTrigger().onTrue(climbDownCommand);

    m_driveTrain.setDefaultCommand(m_driveTrainCommand);

  }




  public Command getAutonomousCommand() {

//    return Autos.exampleAuto(m_exampleSubsystem);
    return new TurnToAngle(m_driveTrain, 90);
  }
}
