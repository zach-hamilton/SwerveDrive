// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.ironmaple.simulation.SimulatedArena;
import org.ironmaple.simulation.drivesims.SwerveDriveSimulation;
import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.hardware.Pigeon2;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
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
import frc.robot.subsystems.SwerveDrive.GyroIOSim;
import frc.robot.subsystems.SwerveDrive.Module;
import frc.robot.subsystems.SwerveDrive.ModuleIOSim;


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

  // SIM
  private SwerveDriveSimulation simulation = null;
  Drivebase driveSim;
  Module frontLeftSimModule;
  Module frontRightSimModule;
  Module backLeftSimModule;
  Module backRightSimModule;
  GyroIOSim simGyro;

  public RobotContainer() {
    
    // real robot stuff 
    // backLeftTalonFX = new ModuleIOTalonFX(new DriveConstantsBackLeft());
    // backRightTalonFX = new ModuleIOTalonFX(new DriveConstantsBackRight());
    // frontLeftTalonFX = new ModuleIOTalonFX(new DriveConstantsFrontLeft());
    // frontRightTalonFX = new ModuleIOTalonFX(new DriveConstantsFrontRight());
    joystickL = new Joystick(0);
    joystickR = new Joystick(1);
    // frontLeftModule = new Module(frontLeftTalonFX, "frontLeft", 0, wheelRadius);
    // frontRightModule = new Module(frontRightTalonFX, "frontRight", 0, wheelRadius);
    // backRightModule = new Module(backRightTalonFX,"backRight", 0, wheelRadius);
    // backLeftModule = new Module(backLeftTalonFX,"backLeft", 0, wheelRadius);
    // gyroIO = new GyroIOPigeon();
    // drivebase = new Drivebase(frontLeftModule, frontRightModule, backLeftModule, backRightModule, gyroIO);
    
    // SIM
    simulation = new SwerveDriveSimulation(Drivebase.mapleSimConfig, new Pose2d(6, 6, new Rotation2d()));
    SimulatedArena.getInstance().addDriveTrainSimulation(simulation);

    frontRightSimModule = new Module(new ModuleIOSim(simulation.getModules()[0]), "frontRightSim", 0, wheelRadius);
    frontLeftSimModule = new Module(new ModuleIOSim(simulation.getModules()[1]), "frontLeftSim", 0, wheelRadius);
    backRightSimModule = new Module(new ModuleIOSim(simulation.getModules()[2]), "backRightSim", 0, wheelRadius);
    backLeftSimModule = new Module(new ModuleIOSim(simulation.getModules()[3]), "backLeftSim", 0, wheelRadius);
    simGyro = new GyroIOSim(simulation.getGyroSimulation());

    driveSim = new Drivebase(frontLeftSimModule, frontRightSimModule, backLeftSimModule, backRightSimModule, simGyro);
  } 

  public Command getTeleCommand(){
    return new SwerveCommand(driveSim, joystickL, joystickR);
  }

  public Command getAutonomousCommand() {
    return null;
  }

  public void updateSimulation() {

        SimulatedArena.getInstance().simulationPeriodic();
        Logger.recordOutput("FieldSimulation/RobotPosition", simulation.getSimulatedDriveTrainPose());
    }
}
