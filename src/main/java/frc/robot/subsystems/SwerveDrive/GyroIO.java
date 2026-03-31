// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;
import static edu.wpi.first.units.Units.Degrees;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.units.measure.Angle;

public interface GyroIO {
  @AutoLog
  public static class GyroIOInputs {
    public Rotation2d yaw;
  }


  public default void updateInputs(GyroIOInputs inputs){}

}
