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
    public static final int kDriverControllerPort = 0;
  }

  public static class driveTrain{
    public static final int rightFollowerPort = 11;
    public static final int leftFollowerPort = 14;
    public static final int leftLeaderPort = 13;
    public static final int rightLeaderPort = 12;

    public static final double sensitivity = 0.85;
  }

  public static class InTake {
    public static final int intakemotorid = 20;
    public static final int intakepistonforward = 21;
    public static final int intakepistonrevers = 22;
  }

  public static class FlyWheel {
    public static final int flyWheelMotorID = 21;
    public static final double flyWheelMotorSpeed = 0.3;
  }



}
