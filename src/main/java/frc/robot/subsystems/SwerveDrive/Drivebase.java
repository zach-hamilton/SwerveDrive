// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import static edu.wpi.first.units.Units.RotationsPerSecond;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;      

public class Drivebase extends SubsystemBase {
  public Module frontLeftModule;
  Module frontRightModule;
  public Module backLeftModule;
  public Module backRightModule;
  GyroIO gyroIO; 

  private Translation2d FLPosition = new Translation2d(11.375,11.375);
  private Translation2d FRPosition = new Translation2d(11.375,-11.375);
  private Translation2d BRPosition = new Translation2d(-11.375,11.375);
  private Translation2d BLPosition = new Translation2d(-11.375,-11.375);

  private SwerveDriveKinematics swerveDriveKinematics = new SwerveDriveKinematics(FLPosition, FRPosition, BLPosition, BRPosition); 

  public Drivebase(Module frontLeftModule, Module backLeftModule, Module frontRightModule, Module backRightModule, GyroIO gyroIO) {
    this.gyroIO = gyroIO;
    this.backLeftModule = backLeftModule;
    this.backRightModule = backRightModule;
    this.frontLeftModule = frontLeftModule;
    this.frontRightModule = frontRightModule;

  }

  @Override
  public void periodic() {
  }

  public void doSwerve(LinearVelocity x, LinearVelocity y, AngularVelocity omega){
    ChassisSpeeds chassisSpeeds = new ChassisSpeeds(x, y, omega);
    SwerveModuleState[] SwerveModuleStates = swerveDriveKinematics.toSwerveModuleStates(chassisSpeeds);

    frontLeftModule.setTurnPosition(SwerveModuleStates[0].angle.getMeasure());
    frontLeftModule.setDriveVelocity(RotationsPerSecond.of(SwerveModuleStates[0].speedMetersPerSecond));
    frontRightModule.setTurnPosition(SwerveModuleStates[1].angle.getMeasure());
    frontRightModule.setDriveVelocity(RotationsPerSecond.of(SwerveModuleStates[1].speedMetersPerSecond));
    backLeftModule.setTurnPosition(SwerveModuleStates[2].angle.getMeasure());
    backLeftModule.setDriveVelocity(RotationsPerSecond.of(SwerveModuleStates[2].speedMetersPerSecond));
    backRightModule.setTurnPosition(SwerveModuleStates[2].angle.getMeasure());
    backRightModule.setDriveVelocity(RotationsPerSecond.of(SwerveModuleStates[3].speedMetersPerSecond));
  }

  public void setModuleStates(Angle angleGoal, AngularVelocity velocity){
    frontLeftModule.setDriveVelocity(velocity);
    frontLeftModule.setTurnPosition(angleGoal);

    frontRightModule.setDriveVelocity(velocity);
    frontRightModule.setTurnPosition(angleGoal);

    backLeftModule.setDriveVelocity(velocity);
    backLeftModule.setTurnPosition(angleGoal);

    backRightModule.setDriveVelocity(velocity);
    backRightModule.setTurnPosition(angleGoal);

  }

}
               