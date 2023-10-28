package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.commands.TankDrive;

public class DriveTrain extends SubsystemBase {

    private CANSparkMax rearLeft;
    private CANSparkMax topLeft;
    private CANSparkMax rearRight;
    private CANSparkMax topRight;

    private MotorControllerGroup left;
    private MotorControllerGroup right;

    private DifferentialDriveKinematics dtKinematics;

    public DriveTrain() {
        rearLeft = new CANSparkMax(DriveTrainConstants.REAR_LEFT_ID, MotorType.kBrushless);
        topLeft = new CANSparkMax(DriveTrainConstants.TOP_LEFT_ID, MotorType.kBrushless);
        rearRight = new CANSparkMax(DriveTrainConstants.REAR_RIGHT_ID, MotorType.kBrushless);
        topRight = new CANSparkMax(DriveTrainConstants.TOP_RIGHT_ID, MotorType.kBrushless);

        left = new MotorControllerGroup(rearLeft, topLeft);
        right = new MotorControllerGroup(rearRight, topRight);

        right.setInverted(true);

        dtKinematics = new DifferentialDriveKinematics(DriveTrainConstants.TRACK_WIDTH_METERS);
    }

    public void drive(double xVelocity, double omegaRotations) {
        DifferentialDriveWheelSpeeds wheelSpeeds = dtKinematics.toWheelSpeeds(new ChassisSpeeds(
            xVelocity, 0, omegaRotations)
        );

        left.set(wheelSpeeds.leftMetersPerSecond);
        right.set(wheelSpeeds.rightMetersPerSecond);
    }

    public void stop() {
        left.stopMotor();
        right.stopMotor();
    }

    public Command getTankDrive(CommandXboxController joy) {
        return new TankDrive(this, joy);
    }
    
}
