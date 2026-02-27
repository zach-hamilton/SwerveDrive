// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import static edu.wpi.first.units.Units.RotationsPerSecond;
import static edu.wpi.first.units.Units.Degrees;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDrive.Drivebase;

public class Crab extends Command {
  Drivebase drivebase;
  Joystick joystick;
  double joystickX;
  double joystickY;
  AngularVelocity velocity;
  Angle angleGoal;
  public Crab(Drivebase drivebase, Joystick joystick) {
  this.drivebase = drivebase;
  this.joystick = joystick;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    joystickY = joystick.getY();
    joystickX = joystick.getX();

    velocity = RotationsPerSecond.of(joystickY * 60);
    angleGoal = Degrees.of(joystickX);

    drivebase.setModuleStates(angleGoal, velocity);

  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
