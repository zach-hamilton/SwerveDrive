// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class GyroIOPigeon implements GyroIO {
  private final Pigeon2 gyro;
  private final StatusSignal<Angle> yaw;
  public GyroIOPigeon() {
    gyro = new Pigeon2(22);
      yaw = gyro.getYaw();
  }

  public void updateInputs(GyroIOInputs inputs){
  inputs.yaw = yaw.getValue();
  }

}
