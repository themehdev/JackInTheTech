package org.firstinspires.ftc.teamcode.subsystems.placer;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class PlacerIOHardware implements PlacerIO{

    private CRServo arm_;
    private double armTarget_;

    private CRServo pinch_;
    private double pinchTarget_;

    public PlacerIOHardware(HardwareMap hardwareMap){
        arm_ = hardwareMap.get(CRServo.class, "arm");
        pinch_ = hardwareMap.get(CRServo.class, "pinch");

        arm_.setDirection(CRServo.Direction.REVERSE);
        pinch_.setDirection(CRServo.Direction.REVERSE);

        //pinch_.setPosition(degsToServo(0, PINCH_OFFSET));
    }

    public void setArmTargetPow(double pow) {
        armTarget_ = pow;
        arm_.setPower(pow);
    }

    public void setPincherTargetPow(double pow){
        pinchTarget_ = pow;
        pinch_.setPower(pow);
    }

    public void updateInputs(PlacerIOInputs inputs){
        inputs.armPow = arm_.getPower();
        inputs.armTarget = armTarget_;

        inputs.pinchPow = pinch_.getPower();
        inputs.pinchTarget = pinchTarget_;
    }
}
