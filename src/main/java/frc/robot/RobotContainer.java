// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.commands.TankDrive;
import frc.robot.subsystems.DriveTrain;
import frc.robot.utils.DualJoystick;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  private final DriveTrain driveTrain = new DriveTrain();
  private final DualJoystick joysticks = new DualJoystick(0, 1);

  public RobotContainer() {
    driveTrain.setDefaultCommand(new TankDrive(driveTrain, joysticks));
    // configureButtonBindings();
  }

  private void configureButtonBindings() {
    

  }

  public Command getAutonomousCommand() {
    return null;
  }
}
