// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.SwerveDrive.GyroIO.GyroIOInputs;

public class Drivebase extends SubsystemBase {
  public Module frontLeftModule;
  Module frontRightModule;
  public Module backLeftModule;
  public Module backRightModule;
  GyroIO gyroIO;
  GyroIOInputsAutoLogged gyroInputs = new GyroIOInputsAutoLogged();

  private Translation2d FLPosition = new Translation2d(11.375, 11.375);
  private Translation2d FRPosition = new Translation2d(11.375, -11.375);
  private Translation2d BLPosition = new Translation2d(-11.375, 11.375);
  private Translation2d BRPosition = new Translation2d(-11.375, -11.375);

  private SwerveDriveKinematics swerveDriveKinematics = new SwerveDriveKinematics(FLPosition, FRPosition, BLPosition,
      BRPosition);

  public Drivebase(Module frontLeftModule, Module frontRightModule, Module backLeftModule, Module backRightModule,
      GyroIO gyroIO) {
    this.gyroIO = gyroIO;
    this.backLeftModule = backLeftModule;
    this.backRightModule = backRightModule;
    this.frontLeftModule = frontLeftModule;
    this.frontRightModule = frontRightModule;

  }

  @Override
  public void periodic() {
    gyroIO.updateInputs(gyroInputs);
    Logger.processInputs("gyro", gyroInputs);
  }

  public void doSwerve(LinearVelocity x, LinearVelocity y, AngularVelocity omega) {
    ChassisSpeeds speeds = new ChassisSpeeds(x, y, omega);
    ChassisSpeeds chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(speeds, gyroInputs.yaw);
    Logger.recordOutput("gyro inputs yaw", gyroInputs.yaw.getDegrees());
    Logger.recordOutput("chassis speeds", chassisSpeeds);
    ChassisSpeeds discreteSpeeds = ChassisSpeeds.discretize(chassisSpeeds, 0.02);

    SwerveModuleState[] swerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(discreteSpeeds);
    Logger.recordOutput("swerve module setpoints", swerveModuleStates);

    frontLeftModule.setModuleState(swerveModuleStates[0]);
    frontRightModule.setModuleState(swerveModuleStates[1]);
    backLeftModule.setModuleState(swerveModuleStates[2]);
    backRightModule.setModuleState(swerveModuleStates[3]);

    Logger.recordOutput("swerve module optimized", swerveModuleStates);
  }

  public Rotation2d robotAngle() {
    return gyroInputs.yaw;
  }

  // public void setModuleStates(Angle angleGoal, LinearVelocity velocity) {
  // frontLeftModule.setDriveVelocity(velocity);
  // frontLeftModule.setTurnPosition(angleGoal);

  // frontRightModule.setDriveVelocity(velocity);
  // frontRightModule.setTurnPosition(angleGoal);

  // backLeftModule.setDriveVelocity(velocity);
  // backLeftModule.setTurnPosition(angleGoal);

  // backRightModule.setDriveVelocity(velocity);
  // backRightModule.setTurnPosition(angleGoal);

  // }

  @AutoLogOutput(key = "swerve states measured")
  private SwerveModuleState[] getModuleStates() {
    SwerveModuleState[] states = new SwerveModuleState[4];
    backRightModule.getState();
    states[0] = frontLeftModule.getState();
    states[1] = frontRightModule.getState();
    states[2] = backLeftModule.getState();
    states[3] = backRightModule.getState();
    return states;
  }

}
