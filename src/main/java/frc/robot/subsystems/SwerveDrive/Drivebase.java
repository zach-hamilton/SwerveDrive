// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivebase extends SubsystemBase {
  Module frontLeftModule;
  Module frontRightModule;
  Module backLeftModule;
  Module backRightModule;
  GyroIO gyroIO;
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

  public void setModuleStates(Angle angleGoal, AngularVelocity angle){
    frontLeftModule.setDriveVelocity(angle);
    frontLeftModule.setTurnPosition(angleGoal);

    frontRightModule.setDriveVelocity(angle);
    frontRightModule.setTurnPosition(angleGoal);

    backLeftModule.setDriveVelocity(angle);
    backLeftModule.setTurnPosition(angleGoal);

    backRightModule.setDriveVelocity(angle);
    backRightModule.setTurnPosition(angleGoal);

  }

}
               