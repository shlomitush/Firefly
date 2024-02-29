package frc.robot;

import frc.robot.enums.Alliancee;

public class RoundInfo {
    public static int aprilTagLimPipe;


    public RoundInfo(Alliancee alliance) {
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
