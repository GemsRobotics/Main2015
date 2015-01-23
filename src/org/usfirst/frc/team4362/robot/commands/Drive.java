package org.usfirst.frc.team4362.robot.commands;

import org.usfirst.frc.team4362.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * Drive extends the CommandBase class.
 *
 * This class will be laid out such that each function can be called as a command.
 *
 * To Do List -- add code as necessary for the function of the robot. Move shifting to it's own file.
 * 
 * Drive() -- essentially a init for this command. Checks what is required and inits the joysticks.
 *
 * initialize() -- initializes driving resources.
 * execute() -- gets shifter trigger and driving axis positions and sets tankdrive and shifter to those values.
 * isFinished() -- returns if driving is finished.
 * end() -- called once if isFinished returns true.
 * interrupted() -- called if drive's subsystems are required by another command.
 */

public class Drive extends CommandBase {
	Joystick leftStick;
	Joystick rightStick; /*!< Creates new Joystick leftStick and rightStick. */
	Button leftTrigger;
	Button rightTrigger; /*!< Creates new Button leftTrigger and rightTrigger. */
	Solenoid upShifter; /*!< Creates new Solenoid shifter. */
	Solenoid downShifter; /*!< Creates new Solenoid shifter. */
	DriverStation ds; /*!< Creates new DriverStation ds. */
	boolean left = false;
	boolean right = true;
    public Drive() {
    	//requires(chassis); /*!< Checks if chassis exists, and does not run if it is missing. */
    	//leftStick = oi.getLeftStick(); /*!< Gets an instance of leftStick from OI.java. */
    	//rightStick = oi.getRightStick(); /*!< Gets an instance of rightStick from OI.java. */
    	leftStick = new Joystick(0);
    	rightStick = new Joystick(1);
    	leftTrigger = new JoystickButton(leftStick, 1); /*!< Gets an instance of leftTrigger from OI.java. */
    	rightTrigger = new JoystickButton(rightStick, 1); /*!< Gets an instance of rightTrigger from OI.java. */
    	
    	upShifter = new Solenoid(0);/*!< Gets an instance of upShifter from OI.java. */
    	downShifter = new Solenoid(1); /*!< Gets an instance of downShifter from OI.java. */
    	ds = DriverStation.getInstance(); /*!< Gets an instance of the ds from the DriverStation. */
    }
   
    protected void initialize() {
    	chassis.tankDrive(0, 0); /*!< Sets the speed to zero when initialized. */
    }

    protected void execute() {
    	
    	if(leftTrigger.get()){ /*!< If left trigger is true (pressed) then continue. */
    		upShifter.set(false); /*!< Set shifter to true (High gear). */
    		downShifter.set(true); /*!< Set shifter to false (Low gear). */
    	}
    	if(rightTrigger.get()){ /*!< If right trigger is true (pressed) then continue. */
    		upShifter.set(true); /*!< Set shifter to true (High gear). */
    		downShifter.set(false); /*!< Set shifter to false (Low gear). */
    	}
    	double left = leftStick.getRawAxis(RobotMap.C_LEFTAXIS); /*!< Sets left to the current position of the left joystick's axis # C_LEFTAXIS. */
    	double right = -rightStick.getRawAxis(RobotMap.C_RIGHTAXIS); /*!< Sets right to the current position of the right joystick's axis # C_RIGHTAXIS. */
    	chassis.tankDrive(left, right); /*!< Sets the values of the chassis.tankDrive to the current joystick values. */
    }

    protected boolean isFinished() { 
    	return !ds.isOperatorControl(); /*!< This returns true if the robot is not in teleop mode. */
    }

    protected void end() {
    	/*!< This code runs once if isFinished() returns true. */
    }

    protected void interrupted() {
    	/*!< This code is called if another command tries to use the subsystem chassis while this class is active. */
    }
}
