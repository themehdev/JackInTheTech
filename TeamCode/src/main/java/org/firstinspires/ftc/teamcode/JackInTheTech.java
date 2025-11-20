package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.LoggedLinearOpMode;
import com.blazedeveloper.chrono.Logger;
import com.blazedeveloper.chrono.dataflow.rlog.RLOGServer;
import com.blazedeveloper.chrono.dataflow.rlog.RLOGWriter;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/*
 * This file contains an example of a Linear "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When a selection is made from the menu, the corresponding OpMode is executed.
 *
 * This particular OpMode illustrates driving a 4-motor Omni-Directional (or Holonomic) robot.
 * This code will work with either a Mecanum-Drive or an X-Drive train.
 * Both of these drives are illustrated at https://gm0.org/en/latest/docs/robot-design/drivetrains/holonomic.html
 * Note that a Mecanum drive must display an X roller-pattern when viewed from above.
 *
 * Also note that it is critical to set the correct rotation direction for each motor.  See details below.
 *
 * Holonomic drives provide the ability for the robot to move in three axes (directions) simultaneously.
 * Each motion axis is controlled by one Joystick axis.
 *
 * 1) Axial:    Driving forward and backward               Left-joystick Forward/Backward
 * 2) Lateral:  Strafing right and left                     Left-joystick Right and Left
 * 3) Yaw:      Rotating Clockwise and counter clockwise    Right-joystick Right and Left
 *
 * This code is written assuming that the right-side motors need to be reversed for the robot to drive forward.
 * When you first test your robot, if it moves backward when you push the left stick forward, then you must flip
 * the direction of all 4 motors (see code below).
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@TeleOp

public class JackInTheTech extends LoggedLinearOpMode {
    // Declare OpMode members for each of the 4 motors.
    private DriveBaseSubsystem db_;
    private IntakeSubsystem intake_;
    private PlacerSubsystem placer_;

    public JackInTheTech (){
        Logger.addReceiver(new RLOGServer());
        Logger.addReceiver(new RLOGWriter());
    }
   
    public void runLoggedOpMode() {

        db_ = new DriveBaseSubsystem(new DriveBaseIOHardware(hardwareMap), gamepad1);
        intake_ = new IntakeSubsystem(new IntakeIOHardware(hardwareMap), gamepad1);
        placer_ = new PlacerSubsystem(new PlacerIOHardware(hardwareMap), gamepad1);
        // Wait for the game to start (driver presses START)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        db_.updateLogging();
        intake_.updateLogging();
        placer_.updateLogging();

        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (isActive()) {
            preCycle();
            db_.periodicTeleOp();
            intake_.periodicTeleOp();
            placer_.periodicTeleOp();
            postCycle();
        }
    }
}
