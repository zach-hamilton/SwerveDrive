// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstants;

import static edu.wpi.first.units.Units.InchesPerSecond;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;

import org.littletonrobotics.junction.Logger;

public class Module extends SubsystemBase {

  private final ModuleIO io;
  private final ModuleIOInputsAutoLogged inputs = new ModuleIOInputsAutoLogged();
  private final Distance wheelRadius;
  private final int index;
  private String moduleName;

  public Module(ModuleIO io, String moduleName, int index, Distance wheelRadius) {
    this.io = io;
    this.index = index;
    this.wheelRadius = wheelRadius;
    this.moduleName = moduleName;

  }

  @Override
  public void periodic() {
    io.updateInputs(inputs);
    Logger.processInputs(moduleName, inputs);
  }

  public void setDriveVelocity(LinearVelocity velocity) {
    io.setDriveVelocity(velocity);
  }

  public void setTurnPosition(Rotation2d angleGoal) {
    io.setTurnPosition(angleGoal);
  }

  public SwerveModuleState getState() {
    return new SwerveModuleState(InchesPerSecond.of(inputs.driveVelocity.in(RadiansPerSecond) * 2),
        new Rotation2d(inputs.turnPosition));
  }

  public void setModuleState(SwerveModuleState state) {
    // state.optimize(new Rotation2d(inputs.turnPosition));  

    setTurnPosition(state.angle);
    setDriveVelocity(MetersPerSecond.of(state.speedMetersPerSecond));

  }
}
