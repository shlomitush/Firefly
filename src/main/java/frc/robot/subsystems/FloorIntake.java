package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import static frc.robot.Constants.FloorIntake.*;
import static frc.robot.Constants.FlyWheel.flyWheelMotorID;
import static frc.robot.Constants.FlyWheel.flyWheelMotorSpeed;


public class FloorIntake extends SubsystemBase {

    private final CANSparkMax floorIntake = new CANSparkMax(floorIntakeID, CANSparkLowLevel.MotorType.kBrushed);

    public void pickUpFromFloor() {
        floorIntake.set(floorIntakeSpeed);
    }

    public void stopPickUpFromFloor() {
        floorIntake.set(0);
    }


}
