// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.hardware.Pigeon2;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDrive.ModuleIOTalonFX;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstantsBackLeft;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstantsBackRight;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstantsFrontLeft;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstantsFrontRight;
import frc.robot.commands.Crab;
import frc.robot.commands.Move;
import frc.robot.subsystems.SwerveDrive.Drivebase;
import frc.robot.subsystems.SwerveDrive.GyroIO;
import frc.robot.subsystems.SwerveDrive.GyroIOPigeon;
import frc.robot.subsystems.SwerveDrive.Module;


public class RobotContainer {
  Joystick joystick;
  Distance wheelRadius;
  Module backLeftModule;
  Module backRightModule;
  Module frontLeftModule;
  Module frontRightModule;
  Drivebase drivebase;
  ModuleIOTalonFX backLeftTalonFX;
  ModuleIOTalonFX backRightTalonFX;
  ModuleIOTalonFX frontLeftTalonFX;
  ModuleIOTalonFX frontRightTalonFX;
  GyroIOPigeon gyroIO;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    backLeftTalonFX = new ModuleIOTalonFX(new DriveConstantsBackLeft());
    backRightTalonFX = new ModuleIOTalonFX(new DriveConstantsBackRight());
    frontLeftTalonFX = new ModuleIOTalonFX(new DriveConstantsFrontLeft());
    frontRightTalonFX = new ModuleIOTalonFX(new DriveConstantsFrontRight());
    joystick = new Joystick(0);
    
    frontLeftModule = new Module(frontLeftTalonFX, 0, wheelRadius);
    frontRightModule = new Module(frontRightTalonFX, 0, wheelRadius);
    backRightModule = new Module(backRightTalonFX, 0, wheelRadius);
    backLeftModule = new Module(backLeftTalonFX, 0, wheelRadius);
    drivebase = new Drivebase(frontLeftModule, backLeftModule, frontRightModule, backRightModule, gyroIO);
    


  } 

  public Command getTeleCommand(){
    return new Crab(drivebase, joystick);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
