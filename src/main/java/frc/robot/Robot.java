//// Copyright (c) FIRST and other WPILib contributors.
//// Open Source Software; you can modify and/or share it under the terms of
//// the WPILib BSD license file in the root directory of this project.
//
//package frc.robot;
//
//import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.Command;
//import edu.wpi.first.wpilibj2.command.CommandScheduler;
//import frc.robot.enums.AutonomousCommand;
//import frc.robot.enums.Alliancee;
//import frc.robot.enums.LimeLightState;
//
//import java.util.Optional;
//
//import static edu.wpi.first.wpilibj.DriverStation.Alliance.*;
//
///**
// * The VM is configured to automatically run this class, and to call the functions corresponding to
// * each mode, as described in the TimedRobot documentation. If you change the name of this class or
// * the package after creating this project, you must also update the build.gradle file in the
// * project.
// */
//public class Robot extends TimedRobot {
//  private Command m_autonomousCommand;
//  private RobotContainer m_robotContainer;
//
//  private final SendableChooser<Alliancee> allianceChooser = new SendableChooser<>(); //there is a command for that we
//  // do not have to choose
//  private final SendableChooser<AutonomousCommand> autoCommandChooser = new SendableChooser<>();
//  private final SendableChooser<LimeLightState> limlimStateChooser = new SendableChooser<LimeLightState>();
//
//
//  /**
//   * This function is run when the robot is first started up and should be used for any
//   * initialization code.
//   */
//  @Override
//  public void robotInit() {
//    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
//    // autonomous chooser on the dashboard.
//    m_robotContainer = new RobotContainer(false);
//
//    allianceChooser.setDefaultOption("Red", Alliancee.RED);
//    allianceChooser.addOption("Blue", Alliancee.BLUE);
//    SmartDashboard.putData("Alliance Color", allianceChooser);
//
//    autoCommandChooser.setDefaultOption("Center", AutonomousCommand.CENTER);
//    autoCommandChooser.addOption("Left", AutonomousCommand.LEFT);
//    autoCommandChooser.addOption("Right", AutonomousCommand.RIGHT);
//    autoCommandChooser.addOption("OUT", AutonomousCommand.OUT);
//    autoCommandChooser.addOption("SIDE_OUT", AutonomousCommand.SIDE_OUT);
//
//
//
//    SmartDashboard.putData("Autonomous Command", autoCommandChooser);
//
//
//    limlimStateChooser.setDefaultOption("not working / not callibrated", LimeLightState.NO);
//    limlimStateChooser.addOption("working fine, can be used", LimeLightState.YES);
//    SmartDashboard.putData("limelight for pikking note state", limlimStateChooser);
//  }
//
//  /**
//   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
//   * that you want ran during disabled, autonomous, teleoperated and test.
//   *
//   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
//   * SmartDashboard integrated updating.
//   */
//  @Override
//  public void robotPeriodic() {
//    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
//    // commands, running already-scheduled commands, removing finished or interrupted commands,
//    // and running subsystem periodic() methods.  This must be called from the robot's periodic
//    // block in order for anything in the Command-based framework to work.
//    CommandScheduler.getInstance().run();
//  }
//
//  /** This function is called once each time the robot enters Disabled mode. */
//  @Override
//  public void disabledInit() {}
//
//  @Override
//  public void disabledPeriodic() {}
//
//  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
//  @Override
//  public void autonomousInit() {
//    m_robotContainer.setLimlim(limlimStateChooser.getSelected());
//    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
//
//
//    Alliancee selectedAlliance = allianceChooser.getSelected();
//    AutonomousCommand selectedCommand = autoCommandChooser.getSelected();
//
//    switch (selectedCommand) {
//      // center
//      case CENTER:
//        m_autonomousCommand = m_robotContainer.getAutonomousCommandCenter();
//        break;
//      case LEFT: // left
//        m_autonomousCommand = m_robotContainer.getAutonomousCommandLeft();
//        break;
//      case RIGHT:
//        m_autonomousCommand = m_robotContainer.getAutonomousCommandRight();
//        break;
//      case OUT:
//        m_autonomousCommand = m_robotContainer.getAutonomousCommandOUT();
//        break;
//      case SIDE_OUT:
//        Optional<DriverStation.Alliance> alliancee = DriverStation.getAlliance();
//        if (alliancee.isPresent()) {
//          if (alliancee.equals(Blue)) {
//            m_autonomousCommand = m_robotContainer.getAutonomousCommandOUTSideBlue();
//          } else if (alliancee.equals(Red)) {
//            m_autonomousCommand = m_robotContainer.getAutonomousCommandOUTSideRed();
//          }
//        } else {
//          m_autonomousCommand = m_robotContainer.getAutonomousCommandOUTSideRed();
//        }
//        break;
//    }
//
//    // schedule the autonomous command (example)
//    if (m_autonomousCommand != null) {
//      m_autonomousCommand.schedule();
//    }
//  }
//
//  /** This function is called periodically during autonomous. */
//  @Override
//  public void autonomousPeriodic() {}
//
//  @Override
//  public void teleopInit() {
//    // This makes sure that the autonomous stops running when
//    // teleop starts running. If you want the autonomous to
//    // continue until interrupted by another command, remove
//    // this line or comment it out.
//    if (m_autonomousCommand != null) {
//      m_autonomousCommand.cancel();
//
//    }
//  }
//
//  /** This function is called periodically during operator control. */
//  @Override
//  public void teleopPeriodic() {}
//
//  @Override
//  public void testInit() {
//    // Cancels all running commands at the start of test mode.
//    CommandScheduler.getInstance().cancelAll();
//  }
//
//  /** This function is called periodically during test mode. */
//  @Override
//  public void testPeriodic() {}
//
//  /** This function is called once when the robot is first started up. */
//  @Override
//  public void simulationInit() {}
//
//  /** This function is called periodically whilst in simulation. */
//  @Override
//  public void simulationPeriodic() {}
//
//  public Alliancee getAlliance() {
////    return Alliance.RED;
//    return allianceChooser.getSelected();
//  }
//
//  public AutonomousCommand getAutoCommand() {
//    return autoCommandChooser.getSelected();
//  }
//
//  public boolean getLimLimForeNote() {
//    LimeLightState c = this.limlimStateChooser.getSelected();
//      return switch (c) {
//          case NO -> false;
//          case YES -> true;
//      };
//  }
//
//}


// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.enums.AutonomousCommand;
import frc.robot.enums.Alliancee;
import frc.robot.enums.LimeLightState;

import java.util.Optional;

import static edu.wpi.first.wpilibj.DriverStation.Alliance.*;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;
  private RobotContainer m_robotContainer;

  private final SendableChooser<Alliancee> allianceChooser = new SendableChooser<>(); //there is a command for that we
  // do not have to choose
  private final SendableChooser<AutonomousCommand> autoCommandChooser = new SendableChooser<>();
  private final SendableChooser<LimeLightState> limlimStateChooser = new SendableChooser<LimeLightState>();


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer(false);

    allianceChooser.setDefaultOption("Red", Alliancee.RED);
    allianceChooser.addOption("Blue", Alliancee.BLUE);
    SmartDashboard.putData("Alliance Color", allianceChooser);

    autoCommandChooser.setDefaultOption("Center", AutonomousCommand.CENTER);
    autoCommandChooser.addOption("Left", AutonomousCommand.LEFT);
    autoCommandChooser.addOption("Right", AutonomousCommand.RIGHT);
    autoCommandChooser.addOption("OUT", AutonomousCommand.OUT);
    autoCommandChooser.addOption("SIDE_OUT", AutonomousCommand.SIDE_OUT);



    SmartDashboard.putData("Autonomous Command", autoCommandChooser);


    limlimStateChooser.setDefaultOption("not working / not callibrated", LimeLightState.NO);
    limlimStateChooser.addOption("working fine, can be used", LimeLightState.YES);
    SmartDashboard.putData("limelight for pikking note state", limlimStateChooser);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();

    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {}

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}

  public Alliancee getAlliance() {
//    return Alliance.RED;
    return allianceChooser.getSelected();
  }

  public AutonomousCommand getAutoCommand() {
    return autoCommandChooser.getSelected();
  }

  public boolean getLimLimForeNote() {
    LimeLightState c = this.limlimStateChooser.getSelected();
    return switch (c) {
      case NO -> false;
      case YES -> true;
    };
  }

}
