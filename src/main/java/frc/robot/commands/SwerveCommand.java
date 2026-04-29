// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.RotationsPerSecond;

import org.littletonrobotics.junction.Logger;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.units.measure.AngularVelocity;
import edu.wpi.first.units.measure.LinearVelocity;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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


    joystickLinearVelocityX = MetersPerSecond.of(MathUtil.applyDeadband(joystickL.getY(), 0.1) * -0.6);
    joystickLinearVelocityY = MetersPerSecond.of(MathUtil.applyDeadband(joystickL.getX(), 0.1) * -0.6);
    joystickAngularVelocity = RotationsPerSecond.of(MathUtil.applyDeadband(joystickR.getX(), 0.1) * -0.01);

    Logger.recordOutput("joystick", joystickLinearVelocityX);

    drivebase.doSwerve(joystickLinearVelocityX, joystickLinearVelocityY, joystickAngularVelocity);
  }


  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
