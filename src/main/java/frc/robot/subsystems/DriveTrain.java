// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;

public class DriveTrain extends SubsystemBase {
  // declare our variables
  private TalonSRX frontL;
  private TalonSRX frontR;
  private TalonSRX backL;
  private TalonSRX backR;

  private boolean slowModeOn = true;

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonSRX(Constants.ID.DRIVETRAIN_FRONT_LEFT);
    frontR = new TalonSRX(Constants.ID.DRIVETRAIN_FRONT_RIGHT);
    backL = new TalonSRX(Constants.ID.DRIVETRAIN_BACK_LEFT);
    backR = new TalonSRX(Constants.ID.DRIVETRAIN_BACK_RIGHT);

    backL.follow(frontL);
    backR.follow(frontR);

    frontL.setInverted(true);
    backL.setInverted(true);
    frontR.setInverted(false);
    backR.setInverted(false);

    frontL.setNeutralMode(NeutralMode.Brake);
    frontR.setNeutralMode(NeutralMode.Brake);
    backL.setNeutralMode(NeutralMode.Brake);
    backR.setNeutralMode(NeutralMode.Brake);

  }

  @Override
  public void periodic() {
  }

  public void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }

  public void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y - x);
    frontR.set(ControlMode.PercentOutput, y + x);
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public void setSlowMode(boolean slowModeOn) {
    this.slowModeOn = slowModeOn;
  }

  public boolean getSlowMode() {
    return slowModeOn;
  }

}