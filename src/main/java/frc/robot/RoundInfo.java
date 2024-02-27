package frc.robot;

import frc.robot.enums.Alliance;

public class RoundInfo {
    public static int aprilTagLimPipe;


    public RoundInfo(Alliance alliance) {
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
