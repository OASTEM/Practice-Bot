// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.Constants;
import frc.robot.utils.DualJoystick;

public class TankDrive extends CommandBase {
  DriveTrain driveTrain;
  DualJoystick joystick;

  /** Creates a new TankDrive. */
  public TankDrive(DriveTrain driveTrain, DualJoystick joystick) {
    addRequirements(driveTrain);
    this.driveTrain = driveTrain;
    this.joystick = joystick;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double leftSpeed = joystick.getLeftRawAxis(Constants.DualJoystick.Y_AXIS);
    double rightSpeed = joystick.getRightRawAxis(Constants.DualJoystick.Y_AXIS);
    // double leftDial = joystick.getLeftRawAxis(Constants.DualJoystick.DIAL);
    // double rightDial = joystick.getRightRawAxis(Constants.DualJoystick.DIAL);

    if (driveTrain.getSlowMode()) {
      driveTrain.tankDrive(leftSpeed * Constants.DriveTrain.SLOW_MODE, rightSpeed * Constants.DriveTrain.SLOW_MODE);
    } else {
      driveTrain.tankDrive(leftSpeed, rightSpeed);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
