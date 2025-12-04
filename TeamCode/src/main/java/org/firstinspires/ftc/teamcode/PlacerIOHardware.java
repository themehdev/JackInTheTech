package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class PlacerIOHardware implements PlacerIO{

    private CRServo arm_;
    private double armTarget_;

    private Servo pinch_;
    private double pinchTarget_;

    private static double ARM_OFFSET = 0.0;
    private static double ARM_GEAR_RATIO = 5.0;

    private static double PINCH_OFFSET = 0.0;

    public PlacerIOHardware(HardwareMap hardwareMap){
        arm_ = hardwareMap.get(CRServo.class, "arm");
        pinch_ = hardwareMap.get(Servo.class, "pinch");

        arm_.setDirection(CRServo.Direction.FORWARD);
        pinch_.setDirection(Servo.Direction.FORWARD);
    }

    private double degsToServo(double degs, double offset, double gearRatio){
        return (degs + offset) * gearRatio/300;
    }

    private double degsToServo(double degs, double offest){
        return degsToServo(degs, offest, 1);
    }

    private double servoToDegs(double servo, double offset, double gearRatio){
        return (servo * 300 + offset)/gearRatio;
    }

    private double servoToDegs(double servo, double offset){
        return servoToDegs(servo, offset);
    }

    public void setArmTargetVel(double pow) {
        armTarget_ = pow;
        arm_.setPower(pow);
    }

    public void setPincherTargetPos(double degs){
        pinchTarget_ = degs;
        pinch_.setPosition(degsToServo(pinchTarget_, PINCH_OFFSET));
    }

    public void updateInputs(PlacerIOInputs inputs){
        inputs.armPow = arm_.getPower();
        inputs.armTarget = armTarget_;

        inputs.pinchPos = servoToDegs(pinch_.getPosition(), PINCH_OFFSET);
        inputs.pinchTarget = pinchTarget_;
    }
}
