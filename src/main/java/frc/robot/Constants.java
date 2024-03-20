// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static class OperatorConstants {
    public static final int kDriverControllerPort1 = 0;
    public static final int kDriverControllerPort2 = 1;


  }

  public static class driveTrain{
    public static final int rightFollowerPort = 11;
    public static final int leftFollowerPort = 14;
    public static final int leftLeaderPort = 13;
    public static final int rightLeaderPort = 12;

    public static final double sensitivity = 0.85;
  }


  public static class FlyWheel {
    public static final int flyWheelMotorIDLeader = 21;

    public static final int flyWheelMotorIDFollower = 22;
    public static final double flyWheelMotorSpeedThrow = 0.8;
    public static final double flyWheelMotorSpeedFeederIn = 0.8;

    public static final double flyWheelSlowUpOutSpeed = 0.3;

    public static final double flyWheelMotorSpeedAMP = 0.2;



  }

  public static class FloorIntake {
    public static final int floorIntakeMotorID = 31;
    public static final double floorIntakeSpeed = 0.7;
    public static final double floorIntakeSlowUpOutSpeed = 0.3;

  }

  public static class Climb {
    public static final int climbMotorLeftID = 42;
    public static final int climbMotorRightID = 41;
    public static final int climbLeftLimitSwitchID = 1;
    public static final int climbRightLimitSwitchID = 0;

    public static final double climbMotorSpeedUp = 0.8;
    public static final double climbMotorSpeedDown = 0.5;

  }

  public static class PollyIntake {
    public static final int pollyIntakeMotorID = 32;
    public static final int pollyIntakeBeamBreakID = 2;

    public static final double pollyIntakeSpeedFloorIn = 0.3;
    public static final double pollyIntakeSpeedFeederIn = 0.7;
    public static final double pollyIntakeSpeedFeederOut = 0.4;

    public static final double pollyIntakeSpeedBack = 0.3;

    public static final double pollyIntakeSlowUpOutSpeed = 0.3;
    public static final double pollyIntakeAMPThrow = 1;


  }

public static class TurnToAngle {
    public static double kp = 0.003;
  public static double ki = 0;
  public static double kd = 0;

}


}
