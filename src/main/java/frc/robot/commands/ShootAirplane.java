// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AirplaneLauncher;

public class ShootAirplane extends CommandBase {
  AirplaneLauncher airplaneLauncher;

  /** Creates a new ShootAirplane. */
  public ShootAirplane(AirplaneLauncher airplaneLauncher) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(airplaneLauncher);
    this.airplaneLauncher = airplaneLauncher;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    airplaneLauncher.shoot();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    airplaneLauncher.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
