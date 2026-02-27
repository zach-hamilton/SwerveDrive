// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public interface GyroIO {
  
  public static class GyroIOInputs {
    public Angle yaw;
  }

  public default void updateInputs(GyroIOInputs inputs){}

}
