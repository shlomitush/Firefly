package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.DriveTrainCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.commands.FlyWheelCommand;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.TurnToAngle;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.FloorIntake;
import frc.robot.subsystems.FlyWheel;


public class RobotContainer {


  //  private final Intake m_intake = new Intake();
  private final CommandXboxController m_driverController = new CommandXboxController(OperatorConstants.kDriverControllerPort);

  private final DriveTrain m_driveTrain = new DriveTrain();
  private final FlyWheel flyWheel = new FlyWheel();
//  private final driveTrainCommand m_driveTrainCommand = new driveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(2));
  private final FloorIntake m_Floor_intake = new FloorIntake();
//  private final IntakeCommand m_intakeCommand = new IntakeCommand(m_Floor_intake);

  private final DriveTrainCommand m_driveTrainCommand = new DriveTrainCommand(m_driveTrain, m_driverController::getLeftY, ()-> -m_driverController.getRawAxis(4));
  private final FlyWheelCommand flyWheelCommand = new FlyWheelCommand(flyWheel);


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
    m_driverController.a().onTrue(flyWheelCommand);

    m_driveTrain.setDefaultCommand(m_driveTrainCommand);

  }




  public Command getAutonomousCommand() {

//    return Autos.exampleAuto(m_exampleSubsystem);
    return new TurnToAngle(m_driveTrain, 90);
  }
}
