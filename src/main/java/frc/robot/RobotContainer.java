// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.Drive;
import frc.robot.commands.ShootAirplane;
import frc.robot.subsystems.AirplaneLauncher;
import frc.robot.subsystems.DriveTrain;

import frc.robot.utils.DualJoystick;
import frc.robot.utils.Constants.DriveTrain.DriveMode;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
  private DriveTrain driveTrain = new DriveTrain();

  private final DualJoystick joysticks = new DualJoystick(0, 1);
  private final JoystickButton button8 = new JoystickButton(joysticks.getRighJoystick(), 8);
  private final JoystickButton button1 = new JoystickButton(joysticks.getRighJoystick(), 1);

  private final AirplaneLauncher airplaneLauncher = new AirplaneLauncher();

  private final Drive drive = new Drive(driveTrain, joysticks);
  private final ShootAirplane shootAirplane = new ShootAirplane(airplaneLauncher, joysticks);

  public RobotContainer() {
    driveTrain.setDriveMode(DriveMode.ArcadeDrive);
    driveTrain.setDefaultCommand(drive);

    configureButtonBindings();
  }

  private void configureButtonBindings() {
    button8.whenPressed(new InstantCommand(() -> this.changeDriveMode()));
    button1.whenPressed(shootAirplane);

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
