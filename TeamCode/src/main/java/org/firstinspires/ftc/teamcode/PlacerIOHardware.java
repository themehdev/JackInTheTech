package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlacerIOHardware implements PlacerIO{

    private DcMotor arm_;

    private Servo pincher_;

    public PlacerIOHardware(HardwareMap hardwareMap){
        arm_ = hardwareMap.get(DcMotor.class, "arm");
        pincher_ = hardwareMap.get(Servo.class, "pincher");

        arm_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        arm_.setDirection(DcMotor.Direction.FORWARD);
        pincher_.setDirection(Servo.Direction.FORWARD);
    }

    @Override
    public void setArmTargetPos(double degs) {

        arm_.setTargetPosition((int) Math.round(degs*537.7/360));
    }

    public void setPincherTargetPos(double pos){
        pincher_.setPosition(pos);
    }

    public void updateInputs(PlacerIOInputs inputs){
        inputs.armTarget = arm_.getTargetPosition();
        inputs.armPos = arm_.getCurrentPosition();
        inputs.armPow = arm_.getPower();

        inputs.pincherPos = pincher_.getPosition();
    }
}
