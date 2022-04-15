// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveTrain;

import frc.robot.utils.DualJoystick;
import frc.robot.utils.Constants.DriveTrain.DriveMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private DriveTrain driveTrain = new DriveTrain();

  private final DualJoystick joysticks = new DualJoystick(0, 1);
  private final JoystickButton driveModeButton_1 = new JoystickButton(joysticks.getRighJoystick(), 8);

  private final Drive drive = new Drive(driveTrain, joysticks);

  public RobotContainer() {
    driveTrain.setDriveMode(DriveMode.ArcadeDrive);
    driveTrain.setDefaultCommand(drive);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    driveModeButton_1.whenPressed(new InstantCommand(() -> this.changeDriveMode()));

  }

  public void changeDriveMode() {
    switch (driveTrain.getDriveMode()) {
      case ArcadeDrive:
        driveTrain.setDriveMode(DriveMode.TankDrive);

        break;
      case TankDrive:
        driveTrain.setDriveMode(DriveMode.ArcadeDrive);

        break;
      default:
        driveTrain.setDriveMode(DriveMode.ArcadeDrive);

        break;
    }

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
