// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class DriveTrainConstants {
    public static final int REAR_LEFT_ID = 0;
    public static final int TOP_LEFT_ID = 0;
    public static final int REAR_RIGHT_ID = 0;
    public static final int TOP_RIGHT_ID = 0;

    public static final double TRACK_WIDTH_INCHES = 0.0;
    public static final double TRACK_WIDTH_METERS = Units.inchesToMeters(TRACK_WIDTH_INCHES);

    public static final double NEO_MAX_SPEED_RPM = 5676.0;
    public static final double GEARING = 1.0;

    public static final double WHEEL_DIAMETER = Units.inchesToMeters(6);
    public static final double THEORETICAL_MAX_MPS = (NEO_MAX_SPEED_RPM * GEARING * Math.PI * WHEEL_DIAMETER) / 60.0;
    public static final double VELOCITY_CONVERSION_FACTOR = (WHEEL_DIAMETER * Math.PI * GEARING) / 60.0;

    public static final double RATE_LIMIT_SECONDS = 0.5;

    public static final boolean RIGHT_INVERTED = false;
    public static final boolean LEFT_INVERTED = true;

    public static final double RIGHT_KP = 0.0;
    public static final double LEFT_KP = 0.0;
  }
}
