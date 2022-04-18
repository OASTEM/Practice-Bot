// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class AirplaneLauncher extends SubsystemBase {
  TalonSRX right = new TalonSRX(Constants.AirplaneLauncher.RIGHT);
  TalonSRX left = new TalonSRX(Constants.AirplaneLauncher.LEFT);

  /** Creates a new AirplaneLauncher. */
  public AirplaneLauncher() {

  }

  public void shoot() {
    right.set(ControlMode.PercentOutput, 0.1);
    left.set(ControlMode.PercentOutput, -0.1);

  }

  public void stop() {
    right.set(ControlMode.PercentOutput, 0);
    left.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
