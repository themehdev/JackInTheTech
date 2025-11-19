package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveBaseIOHardware implements DriveBaseIO{

    private DcMotor fl_;
    private DcMotor fr_;
    private DcMotor bl_;
    private DcMotor br_;

    private IMU imu_;

    private double yawRad;

    public DriveBaseIOHardware (HardwareMap hardwareMap){
        fl_ = hardwareMap.get(DcMotor.class, "front_left_drive");
        bl_ = hardwareMap.get(DcMotor.class, "back_left_drive");
        fr_ = hardwareMap.get(DcMotor.class, "front_right_drive");
        br_ = hardwareMap.get(DcMotor.class, "back_right_drive");

        fl_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fr_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        fl_.setDirection(DcMotor.Direction.FORWARD);
        bl_.setDirection(DcMotor.Direction.REVERSE);
        fr_.setDirection(DcMotor.Direction.FORWARD);
        br_.setDirection(DcMotor.Direction.FORWARD);

        imu_ = hardwareMap.get(IMU.class, "imu");
        imu_.initialize(
                new IMU.Parameters(
                        new RevHubOrientationOnRobot(
                                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                                RevHubOrientationOnRobot.UsbFacingDirection.UP
                        )

                )

        );

        imu_.resetYaw();
    }

    public void setDBPowers(double frPow, double flPow, double blPow, double brPow){
        fl_.setPower(flPow);
        fr_.setPower(frPow);
        bl_.setPower(blPow);
        br_.setPower(brPow);
    }

    public void resetYaw() {
        imu_.resetYaw();
    }

    @Override
    public void updateInputs(DriveBaseIOInputs inputs){
        inputs.blPos = bl_.getCurrentPosition();
        inputs.blPow = bl_.getPower();

        inputs.brPow = br_.getPower();
        inputs.brPos = br_.getCurrentPosition();

        inputs.flPos = fl_.getCurrentPosition();
        inputs.flPow = fl_.getPower();

        inputs.frPos = fr_.getCurrentPosition();
        inputs.frPow = fr_.getPower();

        inputs.imuYawRad = imu_.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
    }


}
