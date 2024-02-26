package frc.robot;

import enums.Alliance;

public class RoundInfo {
    public static int aprilTagLimPipe;

    private Robot robot;

    public RoundInfo(Robot robot) {
        this.robot = robot;

        Alliance alliance = robot.getAlliance();
        switch (alliance) {
            case RED:
                aprilTagLimPipe = 4;
            case BLUE:
                aprilTagLimPipe = 7;
        }
    }

    public int getAprilTagLimPipe() {
        return aprilTagLimPipe;
    }
}
