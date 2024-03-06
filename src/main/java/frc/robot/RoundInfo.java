package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import frc.robot.enums.Alliancee;

import java.util.Optional;

import static edu.wpi.first.wpilibj.DriverStation.Alliance.*;

public class RoundInfo {
    public static int aprilTagLimPipe;


    public RoundInfo() {
        Optional<DriverStation.Alliance> alliancee = DriverStation.getAlliance();
        if (alliancee.isPresent()) {
            if (alliancee.equals(Red)) {
                aprilTagLimPipe = 4;
            } else if (alliancee.equals(Blue)) {
                aprilTagLimPipe = 7;
            }
        }
    }

    public int getAprilTagLimPipe() {
        return aprilTagLimPipe;
    }
}
