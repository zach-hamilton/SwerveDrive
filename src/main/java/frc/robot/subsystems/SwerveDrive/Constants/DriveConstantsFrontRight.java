// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive.Constants;

/** Add your docs here. */
public class DriveConstantsFrontRight extends DriveConstants {
    public DriveConstantsFrontRight(){
        turnMotorId = 13;
        driveMotorId = 14;
        cancoderID = 15;
        CANbus = "Drivebase 2025";
        MagnetSensorOffset = 0;
    }
}
