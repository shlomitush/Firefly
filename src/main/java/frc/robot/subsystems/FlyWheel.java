package frc.robot.subsystems;


import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import static frc.robot.Constants.FlyWheel.*;

public class FlyWheel extends SubsystemBase {
    private final CANSparkMax flyWheelMotor = new CANSparkMax(flyWheelMotorID, CANSparkLowLevel.MotorType.kBrushed);

    public void throwWheel() {
        flyWheelMotor.set(flyWheelMotorSpeed);
    }

    public void stopThrow() {
        flyWheelMotor.set(0);
    }

}
