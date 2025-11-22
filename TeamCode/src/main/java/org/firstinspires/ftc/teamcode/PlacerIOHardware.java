package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlacerIOHardware implements PlacerIO{

    private DcMotor arm_;

    private Servo pinch_;

    private static double ARM_OFFSET = 0.0;
    private static double ARM_GEAR_RATIO = 1.0;

    private static double PINCH_OFFSET = 0.0;

    public PlacerIOHardware(HardwareMap hardwareMap){
        arm_ = hardwareMap.get(DcMotor.class, "arm");
        pinch_ = hardwareMap.get(Servo.class, "pincher");

        arm_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        setArmTargetPos(0);
        arm_.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        arm_.setDirection(DcMotor.Direction.FORWARD);
        pinch_.setDirection(Servo.Direction.FORWARD);
    }

    public void setArmTargetPos(double degs) {
        arm_.setTargetPosition((int) Math.round((((degs + ARM_OFFSET)*537.7/360) * ARM_GEAR_RATIO)));
    }

    public void setArmPower(double pow){
        arm_.setPower(pow);
    }

    public void setPincherTargetPos(double degs){
        pinch_.setPosition((degs + PINCH_OFFSET)/300);
    }

    public void updateInputs(PlacerIOInputs inputs){
        inputs.armTarget = ((arm_.getTargetPosition()/ARM_GEAR_RATIO) / (537.7/360)) - ARM_OFFSET;
        inputs.armPos = ((arm_.getCurrentPosition()/ARM_GEAR_RATIO) / (537.7/360)) - ARM_OFFSET;
        inputs.armPow = arm_.getPower();
        inputs.armFinished = !arm_.isBusy();

        inputs.pinchPos = pinch_.getPosition() * 300 - PINCH_OFFSET;
    }
}
