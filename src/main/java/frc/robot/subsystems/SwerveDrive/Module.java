// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.SwerveDrive.Constants.DriveConstants;

import org.littletonrobotics.junction.Logger;

public class Module extends SubsystemBase {
  
  private final ModuleIO io;
  private final ModuleIOInputsAutoLogged inputs = new ModuleIOInputsAutoLogged();
  private final Distance wheelRadius;
  private final int index;


  public Module(ModuleIO io, int index, Distance wheelRadius){
  this.io = io;
  this.index = index;
  this.wheelRadius = wheelRadius;
  
  }
  
  @Override
  public void periodic(){
    io.updateInputs(inputs);
    Logger.processInputs(getName(), inputs);
  }
  public void setDriveVelocity(AngularVelocity velocity){
    io.setDriveVelocity(velocity);
  }
  public void setTurnPosition(Angle angleGoal){
    io.setTurnPosition(angleGoal);
  }
}
