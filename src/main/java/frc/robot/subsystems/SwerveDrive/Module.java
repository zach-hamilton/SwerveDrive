// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.SwerveDrive;

import edu.wpi.first.units.measure.Distance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Module extends SubsystemBase {
  
  private final ModuleIO io;
  private final int index;
  private final Distance wheelRadius;
  // private final Distance wheelRadius;

  public Module(ModuleIO io, int index, Distance wheelRadius){
  this.io = io;
  this.index = index;
  this.wheelRadius = wheelRadius;
  }
  
  
  public void periodic(){
  }
}
