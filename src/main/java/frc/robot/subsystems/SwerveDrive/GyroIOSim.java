// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import static edu.wpi.first.units.Units.Degrees;

import org.ironmaple.simulation.drivesims.GyroSimulation;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroIOSim implements GyroIO {
  private final GyroSimulation gyroSimulation;
  public GyroIOSim(GyroSimulation gyroSimulation) {
    this.gyroSimulation = gyroSimulation;

  }

  @Override
  public void updateInputs(GyroIOInputs inputs){
    inputs.yaw = gyroSimulation.getGyroReading();
  }

}

