package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
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

    private SparkMaxPIDController leftController;
    private SparkMaxPIDController rightController;

    private DifferentialDriveKinematics dtKinematics;

    public DriveTrain() {
        rearLeft = new CANSparkMax(DriveTrainConstants.REAR_LEFT_ID, MotorType.kBrushless);
        topLeft = new CANSparkMax(DriveTrainConstants.TOP_LEFT_ID, MotorType.kBrushless);
        rearRight = new CANSparkMax(DriveTrainConstants.REAR_RIGHT_ID, MotorType.kBrushless);
        topRight = new CANSparkMax(DriveTrainConstants.TOP_RIGHT_ID, MotorType.kBrushless);

        rearLeft.setInverted(DriveTrainConstants.LEFT_INVERTED);
        topLeft.setInverted(DriveTrainConstants.LEFT_INVERTED);
        rearRight.setInverted(DriveTrainConstants.RIGHT_INVERTED);
        topRight.setInverted(DriveTrainConstants.RIGHT_INVERTED);

        topLeft.follow(rearLeft);
        topRight.follow(rearRight);

        leftController = rearLeft.getPIDController();
        rightController = rearRight.getPIDController();

        leftController.setP(DriveTrainConstants.LEFT_KP);
        rightController.setP(DriveTrainConstants.RIGHT_KP);

        dtKinematics = new DifferentialDriveKinematics(DriveTrainConstants.TRACK_WIDTH_METERS);
    }

    public void drive(double xVelocityMPS, double omegaRotationsMPS) {
        DifferentialDriveWheelSpeeds wheelSpeeds = dtKinematics.toWheelSpeeds(new ChassisSpeeds(
            xVelocityMPS, 0, omegaRotationsMPS)
        );

        leftController.setReference(wheelSpeeds.leftMetersPerSecond, ControlType.kVelocity);
        rightController.setReference(wheelSpeeds.rightMetersPerSecond, ControlType.kVelocity);
    }

    public void stop() {

    }

    public Command getTankDrive(CommandXboxController joy) {
        return new TankDrive(this, joy);
    }
    
}
