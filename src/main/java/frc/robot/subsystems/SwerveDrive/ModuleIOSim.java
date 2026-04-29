// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import static edu.wpi.first.units.Units.Amps;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RadiansPerSecond;
import static edu.wpi.first.units.Units.Rotations;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Volts;
import static edu.wpi.first.units.Units.VoltsPerRadianPerSecond;

import org.ironmaple.simulation.drivesims.SwerveModuleSimulation;
import org.ironmaple.simulation.motorsims.SimulatedMotorController;
import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj.simulation.DCMotorSim;


public class ModuleIOSim implements ModuleIO {
  
  private final SwerveModuleSimulation moduleSimulation;
  private final SimulatedMotorController.GenericMotorController driveMotor;
  private final SimulatedMotorController.GenericMotorController turnMotor;
  private final PIDController driveController;
  private final PIDController turnController;

  private final double KV;
  private final double KS;
  private double driveAppliedVolts;
  private double driveFFVolts;
  private double turnAppliedVolts;

  public ModuleIOSim(SwerveModuleSimulation moduleSimulation) {
    this.moduleSimulation = moduleSimulation;
    this.driveMotor = moduleSimulation.useGenericMotorControllerForDrive();
    this.turnMotor = moduleSimulation.useGenericControllerForSteer();

    this.driveController = new PIDController(0.08498, 0, 0);
    this.turnController = new PIDController(100, 0, 0.5);

    turnController.enableContinuousInput(-Math.PI, Math.PI);

    KV = 12.0/1.9;
    KS = 0.001;
  }

  @Override
  public void updateInputs(ModuleIOInputs inputs) {

    driveAppliedVolts = driveFFVolts + driveController.calculate(moduleSimulation.getDriveWheelFinalSpeed().in(RadiansPerSecond));
    turnAppliedVolts =  turnController.calculate(moduleSimulation.getSteerAbsoluteFacing().getRadians());

    driveMotor.requestVoltage(Volts.of(driveAppliedVolts));
    turnMotor.requestVoltage(Volts.of(turnAppliedVolts));

    inputs.driveCurrent = moduleSimulation.getDriveMotorStatorCurrent().in(Amps);
    inputs.driveVoltage = moduleSimulation.getDriveMotorAppliedVoltage().in(Volts);
    inputs.drivePosition = moduleSimulation.getDriveWheelFinalPosition().in(Rotations);
    inputs.driveVelocity = moduleSimulation.getDriveWheelFinalSpeed();

    inputs.turnCurrent = moduleSimulation.getSteerMotorStatorCurrent().in(Amps);
    inputs.turnVoltage = moduleSimulation.getSteerMotorAppliedVoltage().in(Volts);
    inputs.turnPosition = moduleSimulation.getSteerAbsoluteAngle();
    inputs.turnVelocity = moduleSimulation.getSteerAbsoluteEncoderSpeed();
  }

  @Override
  public void setDriveVelocity(LinearVelocity velocity) {
    driveFFVolts = KS * Math.signum(velocity.in(MetersPerSecond)) + KV * velocity.in(MetersPerSecond);
    driveController.setSetpoint(velocity.in(MetersPerSecond));
  }

  @Override
  public void setTurnPosition(Rotation2d angleGoal) {
   turnController.setSetpoint(angleGoal.getRadians());
  }

}
