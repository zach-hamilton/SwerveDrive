// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstants;


public class ModuleIOTalonFX implements ModuleIO {
  
  private final TalonFX driveMotor;
  private final TalonFX turnMotor;
  private final CANcoder cancoder;

  private final PositionVoltage positionVoltage = new PositionVoltage(0);
  private final VelocityVoltage velocityVoltage = new VelocityVoltage(0);

  private final StatusSignal<Angle> drivePositionAngle;
  private final StatusSignal<Current> driveCurrent;
  private final StatusSignal<Voltage> driveVoltage;
  private final StatusSignal<AngularVelocity> driveVelocity;
  private final StatusSignal<Angle> turnPositionAngle;
  private final StatusSignal<Current> turnCurrent;
  private final StatusSignal<Voltage> turnVoltage;
  private final StatusSignal<AngularVelocity> turnVelocity;

  
  public ModuleIOTalonFX(DriveConstants constants) {
    driveMotor = new TalonFX(constants.driveMotorId);
    turnMotor = new TalonFX(constants.turnMotorId);
    cancoder = new CANcoder(constants.cancoderID);

    drivePositionAngle = driveMotor.getPosition();
    driveCurrent = driveMotor.getStatorCurrent();
    driveVelocity = driveMotor.getVelocity();
    driveVoltage = driveMotor.getMotorVoltage();

    turnPositionAngle = driveMotor.getPosition();
    turnCurrent = driveMotor.getStatorCurrent();
    turnVelocity = driveMotor.getVelocity();
    turnVoltage = driveMotor.getMotorVoltage();
  }

  

  public void setDriveVelocity(AngularVelocity velocity){
    driveMotor.setControl(velocityVoltage.withVelocity(velocity));
  }
  
  public void setTurnPosition(Angle angleGoal){
    turnMotor.setControl(positionVoltage.withPosition(angleGoal));
  }

  public void updateInputs(ModuleIOInputs inputs){
    BaseStatusSignal.refreshAll(driveCurrent, driveVelocity, drivePositionAngle, driveVoltage, turnVoltage, turnVelocity, turnCurrent, turnPositionAngle);

    inputs.driveCurrent = driveCurrent.getValueAsDouble();
    inputs.driveVoltage = driveVoltage.getValueAsDouble();
    inputs.drivePosition = Units.rotationsToDegrees(drivePositionAngle.getValueAsDouble());
    inputs.driveVelocity = Units.rotationsToDegrees(driveVelocity.getValueAsDouble());
    inputs.turnCurrent = turnCurrent.getValueAsDouble();
    inputs.turnVoltage = turnVoltage.getValueAsDouble();
    inputs.turnPosition = Units.rotationsToDegrees(turnPositionAngle.getValueAsDouble());
    inputs.turnVelocity = Units.rotationsToDegrees(turnVelocity.getValueAsDouble());
  }
}
