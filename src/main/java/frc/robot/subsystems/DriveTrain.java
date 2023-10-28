package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
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

        left = new MotorControllerGroup(rearLeft, topLeft); //FIXME - MotorControllerGroup is an old class. instead use the spark max follow() methods to link up sides
        right = new MotorControllerGroup(rearRight, topRight);

        left.setInverted(false); //FIXME - U need to make both booleans seperate constants, can't hardcode the true/false of inversions
        right.setInverted(true);

        dtKinematics = new DifferentialDriveKinematics(DriveTrainConstants.TRACK_WIDTH_METERS);
    }

    public void drive(double xVelocity, double omegaRotations) { //FIXME - put units in the xVelocity and omegaRotations variables
        DifferentialDriveWheelSpeeds wheelSpeeds = dtKinematics.toWheelSpeeds(new ChassisSpeeds(
            xVelocity, 0, omegaRotations)
        );

        left.set(wheelSpeeds.leftMetersPerSecond);
        right.set(wheelSpeeds.rightMetersPerSecond);
        //BIG FIXME - remember, the pure set() method only takes a number from [-1, 1]. In order to set a real world speed u need PIDs
        //This means that you need 2 SparkMaxPIDControllers, one for each side.
    }

    public void stop() {
        left.stopMotor();
        right.stopMotor();
    }

    public Command getTankDrive(CommandXboxController joy) {
        return new TankDrive(this, joy);
    }
    
}
