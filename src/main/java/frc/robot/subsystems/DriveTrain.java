// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.hal.HAL;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Constants;
import frc.robot.utils.Constants.DriveTrain.DriveMode;

public class DriveTrain extends SubsystemBase {
  // declare our variables
  private TalonSRX frontL;
  private TalonSRX frontR;
  private TalonSRX backL;
  private TalonSRX backR;

  private DriveMode driveMode = DriveMode.ArcadeDrive;

  DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(2.2462);
  double kS = 1.1867;
  double kV = 55.986;
  double kA = 17.415;
  double kP = 0.29328;
  double kMaxSpeedMetersPerSecond = 3;
  public static final double kRamseteB = 2;
  public static final double kRamseteZeta = 0.7;
  AHRS navX = new AHRS(Port.kMXP, (byte) 50);

  /** Creates a new DriveTrain. */
  public DriveTrain() {
    frontL = new TalonSRX(Constants.ID.DRIVETRAIN_FRONT_LEFT);
    frontR = new TalonSRX(Constants.ID.DRIVETRAIN_FRONT_RIGHT);
    backL = new TalonSRX(Constants.ID.DRIVETRAIN_BACK_LEFT);
    backR = new TalonSRX(Constants.ID.DRIVETRAIN_BACK_RIGHT);

    // frontL.
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

  public void resetEncoders() {
    frontL.setSelectedSensorPosition(0);
    frontR.setSelectedSensorPosition(0);
  }

  private double getRightEncoder() {
    return frontR.getSelectedSensorPosition();
  }

  private double getLeftEncoder() {
    return frontL.getSelectedSensorPosition();
  }

  public double getNativeUnitsFromInches(double inches) {
    return inches * 10.71 / (Math.PI * 6) * 4096;
  }

  public double getInchesFromNativeUnits(double native_units) {
    return native_units / 10.71 * (Math.PI * 6) / 4096;
  }

  public void drive(double leftSpeed, double rightSpeed) {
    switch (driveMode) {
      case ArcadeDrive:
        this.arcadeDrive(leftSpeed, rightSpeed);
        break;
      case TankDrive:
        this.tankDrive(leftSpeed, rightSpeed);
        break;
      default:
        this.arcadeDrive(leftSpeed, rightSpeed);
        break;
    }
  }

  private void tankDrive(double leftSpeed, double rightSpeed) {
    frontL.set(ControlMode.PercentOutput, leftSpeed);
    frontR.set(ControlMode.PercentOutput, rightSpeed);
  }

  private void arcadeDrive(double x, double y) {
    frontL.set(ControlMode.PercentOutput, y - x);
    frontR.set(ControlMode.PercentOutput, y + x);
  }

  public void stop() {
    frontL.set(ControlMode.PercentOutput, 0.0);
    frontR.set(ControlMode.PercentOutput, 0.0);
  }

  public DriveMode getDriveMode() {
    return driveMode;
  }

  public void setDriveMode(DriveMode driveMode) {
    this.driveMode = driveMode;
  }
}
