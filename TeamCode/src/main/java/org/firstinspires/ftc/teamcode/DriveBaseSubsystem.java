package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class DriveBaseSubsystem {
    private DriveBaseIO io_;

    private DriveBaseIO.DriveBaseIOInputs inputs_;

    private Gamepad gp_;

    public DriveBaseSubsystem (DriveBaseIO io, Gamepad gp){
        io_ = io;
        gp_ = gp;

        inputs_ = new DriveBaseIO.DriveBaseIOInputs();
    }

    public DriveBaseSubsystem (DriveBaseIOHardware dbHardware){
        this(dbHardware, null);
    }

    public void setDBPowers(double frPow, double flPow, double brPow, double blPow){
        io_.setDBPowers(frPow, flPow, brPow, blPow);
    }

    public void setDBPowers(double leftPow, double rightPow){
        setDBPowers(rightPow, leftPow, leftPow, rightPow);
    }

    public void setDBPowers(double pow){
        setDBPowers(pow, pow);
    }

    public void stop(){
        setDBPowers(0);
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("DriveBase", inputs_);
    }

    public void periodicTeleOp(){
        updateLogging();

        double max;

        if(gp_.triangle && gp_.circle) {
            io_.resetYaw();
        }

        double gpAngleRad = Math.atan2(-gp_.left_stick_y, gp_.left_stick_x);

        Logger.output("gpAngle", gpAngleRad);

        double robotRelativeAngle = gpAngleRad - inputs_.imuYawRad;

        Logger.output("robotRelativeAngle", robotRelativeAngle);

        double robotRelativeX = Math.cos(robotRelativeAngle);
        double robotRelativeY = Math.sin(robotRelativeAngle);

        double gpMagnitude = Math.sqrt(gp_.left_stick_y * gp_.left_stick_y + gp_.left_stick_x * gp_.left_stick_x);

        Logger.output("gpMagnitude", gpMagnitude);
        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = robotRelativeY * gpMagnitude;  // Note: pushing stick forward gives negative value
        double lateral = robotRelativeX * gpMagnitude;
        double yaw     =  gp_.right_stick_x;

        Logger.output("axial", axial);
        Logger.output("lateral", lateral);
        Logger.output("yaw", yaw);

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double frontLeftPower  = axial + lateral + yaw;
        double frontRightPower = axial - lateral - yaw;
        double backLeftPower   = axial - lateral + yaw;
        double backRightPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(frontLeftPower), Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(backRightPower));

        if (max > 1.0) {
            frontLeftPower  /= max;
            frontRightPower /= max;
            backLeftPower   /= max;
            backRightPower  /= max;
        }

        // This is test code:
        //
        // Uncomment the following code to test your motor directions.
        // Each button should make the corresponding motor run FORWARD.
        //   1) First get all the motors to take to correct positions on the robot
        //      by adjusting your Robot Configuration if necessary.
        //   2) Then make sure they run in the correct direction by modifying the
        //      the setDirection() calls above.
        // Once the correct motors move in the correct direction re-comment this code.

            /*
            frontLeftPower  = gamepad1.x ? 1.0 : 0.0;  // X gamepad
            backLeftPower   = gamepad1.a ? 1.0 : 0.0;  // A gamepad
            frontRightPower = gamepad1.y ? 1.0 : 0.0;  // Y gamepad
            backRightPower  = gamepad1.b ? 1.0 : 0.0;  // B gamepad
            */

        Logger.output("db/flPower", frontLeftPower);
        Logger.output("db/frPower", frontRightPower);
        Logger.output("db/blPower", backLeftPower);
        Logger.output("db/brPower", backRightPower);

        // Send calculated power to wheels
        io_.setDBPowers(frontRightPower, frontLeftPower, backLeftPower, backRightPower);

        // Show the elapsed game time and wheel power.

    }
}
