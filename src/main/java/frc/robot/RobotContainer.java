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
import frc.robot.commands.SwerveCommand;
import frc.robot.subsystems.SwerveDrive.Drivebase;
import frc.robot.subsystems.SwerveDrive.GyroIO;
import frc.robot.subsystems.SwerveDrive.GyroIOPigeon;
import frc.robot.subsystems.SwerveDrive.Module;


public class RobotContainer {
  Joystick joystickL;
  Joystick joystickR;
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
    joystickL = new Joystick(0);
    joystickR = new Joystick(1);
    frontLeftModule = new Module(frontLeftTalonFX, "frontLeft", 0, wheelRadius);
    frontRightModule = new Module(frontRightTalonFX, "frontRight", 0, wheelRadius);
    backRightModule = new Module(backRightTalonFX,"backRight", 0, wheelRadius);
    backLeftModule = new Module(backLeftTalonFX,"backLeft", 0, wheelRadius);
    gyroIO = new GyroIOPigeon();
    drivebase = new Drivebase(frontLeftModule, frontRightModule, backLeftModule, backRightModule, gyroIO);
    



  } 

  public Command getTeleCommand(){
    return new SwerveCommand(drivebase, joystickL, joystickR);
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
