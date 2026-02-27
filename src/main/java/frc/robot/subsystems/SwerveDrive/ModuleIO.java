// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import org.littletonrobotics.junction.AutoLog;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;

public interface ModuleIO {

  @AutoLog
  public static class ModuleIOInputs {

  public double driveCurrent = 0;
  public double driveVoltage = 0;
  public double driveVelocity = 0;
  public double drivePosition = 0;
  public double turnCurrent = 0;
  public double turnVoltage = 0;
  public double turnVelocity = 0;
  public double turnPosition = 0;

  }

  public default void updateInputs(ModuleIOInputs inputs) {}

  public default void setDriveVelocity(AngularVelocity velocity) {}

  public default void setTurnPosition(Angle angleGoal) {}


}