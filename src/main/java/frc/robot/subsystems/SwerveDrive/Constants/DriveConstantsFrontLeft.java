// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive.Constants;

/** Add your docs here. */
public class DriveConstantsFrontLeft extends DriveConstants {
    public DriveConstantsFrontLeft(){
        turnMotorId = 19;
        driveMotorId = 20;
        cancoderID = 21;
        CANbus = "Drivebase 2025";
        MagnetSensorOffset = -0.135742;
    }

}
