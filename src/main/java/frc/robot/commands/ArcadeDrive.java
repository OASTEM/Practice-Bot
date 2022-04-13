// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.Constants;
import frc.robot.utils.DualJoystick;

public class ArcadeDrive extends CommandBase {
  DriveTrain driveTrain;
  DualJoystick joystick;

  public ArcadeDrive(DriveTrain driveTrain, DualJoystick joystick) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.joystick = joystick;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double y_axis = joystick.getLeftRawAxis(Constants.DualJoystick.Y_AXIS);
    double x_axis = joystick.getRightRawAxis(Constants.DualJoystick.X_AXIS);

    if (driveTrain.getSlowMode()) {
      driveTrain.arcadeDrive(x_axis * Constants.DriveTrain.SLOW_MODE, y_axis * Constants.DriveTrain.SLOW_MODE);
    } else {
      driveTrain.arcadeDrive(x_axis, y_axis);

    }
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
