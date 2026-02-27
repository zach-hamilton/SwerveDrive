// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDrive.Module;
import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.RotationsPerSecond;


public class Move extends Command {

  Module module;
  double joystickX;
  double joystickY;
  Joystick joystick;
  

  public Move(Module module, Joystick joystick) {
    this.module = module;
    this.joystick = joystick;
  }

  @Override
  public void initialize() {
    
  }

  @Override
  public void execute() {
    joystickY = joystick.getY();
    module.setDriveVelocity(RotationsPerSecond.of(joystickY * 60));

    joystickX = joystick.getX() * 180;
    module.setTurnPosition(Degrees.of(joystickX));

  }

  @Override
  public void end(boolean interrupted) {
    module.setDriveVelocity(RotationsPerSecond.of(0.0));
    module.setTurnPosition(Degrees.of(0.0));
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
