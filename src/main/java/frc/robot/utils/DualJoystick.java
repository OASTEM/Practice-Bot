package frc.robot.utils;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;

public class DualJoystick extends GenericHID {

	private Joystick right;
	private Joystick left;

	public DualJoystick(int rightPort, int leftPort) {
		super(0);
		this.right = new Joystick(rightPort);
		this.left = new Joystick(leftPort);

	}

	public double getRightRawAxis(int axis) {
		return right.getRawAxis(axis);
	}

	public double getLeftRawAxis(int axis) {
		return left.getRawAxis(axis);

	}
}