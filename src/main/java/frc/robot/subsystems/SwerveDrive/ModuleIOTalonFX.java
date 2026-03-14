// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import static edu.wpi.first.units.Units.Degree;
import static edu.wpi.first.units.Units.Radians;

import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.StatusSignal;
import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Current;
import edu.wpi.first.units.measure.Voltage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    System.out.println("Turn Motor id:" + constants.turnMotorId);
    System.out.println("Drive Motor id:" + constants.driveMotorId);
    System.out.println("Cancoder id:" + constants.cancoderID);
    driveMotor = new TalonFX(constants.driveMotorId, constants.CANbus);
    turnMotor = new TalonFX(constants.turnMotorId, constants.CANbus);
    cancoder = new CANcoder(constants.cancoderID, constants.CANbus);
    System.out.println("Turn Motor id:" + constants.turnMotorId);
    System.out.println("Drive Motor id:" + constants.driveMotorId);
    System.out.println("Cancoder id:" + constants.cancoderID);

    CANcoderConfiguration cancoderConfig = new CANcoderConfiguration();
    cancoderConfig.MagnetSensor.AbsoluteSensorDiscontinuityPoint = 0.5;
    cancoderConfig.MagnetSensor.SensorDirection = SensorDirectionValue.Clockwise_Positive;
    cancoderConfig.MagnetSensor.MagnetOffset = constants.MagnetSensorOffset;

    TalonFXConfiguration driveMotorConfig = new TalonFXConfiguration();
    driveMotorConfig.Slot0.kP = 0.25;
    driveMotorConfig.Slot0.kI = 0.0;
    driveMotorConfig.Slot0.kD = 0.0;

    TalonFXConfiguration turnMotorConfig = new TalonFXConfiguration();
    turnMotorConfig.Slot0.kP = 100;
    turnMotorConfig.Slot0.kI = 0.0;
    turnMotorConfig.Slot0.kD = 0.0;
    turnMotorConfig.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    turnMotorConfig.ClosedLoopGeneral.ContinuousWrap = true;
    turnMotorConfig.Feedback.FeedbackRemoteSensorID = constants.cancoderID;
    turnMotorConfig.Feedback.FeedbackSensorSource = FeedbackSensorSourceValue.RemoteCANcoder; //TODO: update this

    driveMotor.getConfigurator().apply(new TalonFXConfiguration());
    turnMotor.getConfigurator().apply(new TalonFXConfiguration());
    cancoder.getConfigurator().apply(new CANcoderConfiguration());
    driveMotor.getConfigurator().apply(driveMotorConfig);
    turnMotor.getConfigurator().apply(turnMotorConfig);
    cancoder.getConfigurator().apply(cancoderConfig);

    drivePositionAngle = driveMotor.getPosition();
    driveCurrent = driveMotor.getStatorCurrent();
    driveVelocity = driveMotor.getVelocity();
    driveVoltage = driveMotor.getMotorVoltage();

    turnPositionAngle = turnMotor.getPosition();
    turnCurrent = turnMotor.getStatorCurrent();
    turnVelocity = turnMotor.getVelocity();
    turnVoltage = turnMotor.getMotorVoltage();
  }

  
  @Override
  public void setDriveVelocity(AngularVelocity velocity){
    driveMotor.setControl(velocityVoltage.withVelocity(velocity));
  }
  @Override
  public void setTurnPosition(Angle angleGoal){
    turnMotor.setControl(positionVoltage.withPosition(angleGoal));
    SmartDashboard.putNumber("pid output", angleGoal.in(Degree));
  
  }
  @Override
  public void updateInputs(ModuleIOInputs inputs){
    BaseStatusSignal.refreshAll(driveCurrent, driveVelocity, drivePositionAngle, driveVoltage, turnVoltage, turnVelocity, turnCurrent, turnPositionAngle);

    inputs.driveCurrent = driveCurrent.getValueAsDouble();
    inputs.driveVoltage = driveVoltage.getValueAsDouble();
    inputs.drivePosition = drivePositionAngle.getValueAsDouble();
    inputs.driveVelocity = driveVelocity.getValueAsDouble();
    inputs.turnCurrent = turnCurrent.getValueAsDouble();
    inputs.turnVoltage = turnVoltage.getValueAsDouble();
    inputs.turnPosition = turnPositionAngle.getValueAsDouble();
    inputs.turnVelocity = turnVelocity.getValueAsDouble();
  }
}
