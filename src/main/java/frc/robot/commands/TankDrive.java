package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.DriveTrainConstants;
import frc.robot.subsystems.DriveTrain;

public class TankDrive extends CommandBase {

    private DriveTrain dt;
    private CommandXboxController joy;

    private SlewRateLimiter xLimiter;

    public TankDrive(DriveTrain dt, CommandXboxController joy) {
        addRequirements(dt);
        this.dt = dt;
        this.joy = joy;

        xLimiter = new SlewRateLimiter(DriveTrainConstants.RATE_LIMIT_SECONDS);
    }

    @Override
    public void execute() {
        double xVelocity = xLimiter.calculate(joy.getLeftY() * DriveTrainConstants.THEORETICAL_MAX_MPS);
        double omegaRotations = joy.getRightX() * DriveTrainConstants.THEORETICAL_MAX_MPS;

        dt.drive(xVelocity, omegaRotations);
    }
    
}
