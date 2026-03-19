// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDrive.Drivebase;
import frc.robot.subsystems.SwerveDrive.Module;

/* You should consider using the more terse Command factories API instead https://docs.wpilib.org/en/stable/docs/software/commandbased/organizing-command-based.html#defining-commands */
public class SwerveCommand extends Command {
  private Drivebase drivebase;

  private Joystick joystickL;
  private Joystick joystickR;

  private LinearVelocity joystickLinearVelocityX;
  private LinearVelocity joystickLinearVelocityY;
  private AngularVelocity joystickAngularVelocity;
  public SwerveCommand(Drivebase drivebase, Joystick joystickL, Joystick joystickR) {
    this.drivebase = drivebase;
    this.joystickL = joystickL;
    this.joystickR = joystickR;
  }  

  @Override
  public void initialize() {}

  @Override
  public void execute() {


    joystickLinearVelocityX = MetersPerSecond.of(joystickL.getY()* 2);
    joystickLinearVelocityY = MetersPerSecond.of(joystickL.getX() * 2);
    joystickAngularVelocity = RotationsPerSecond.of(joystickR.getX() * 0.1);

    drivebase.doSwerve(joystickLinearVelocityX, joystickLinearVelocityY, joystickAngularVelocity);
  }


  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
