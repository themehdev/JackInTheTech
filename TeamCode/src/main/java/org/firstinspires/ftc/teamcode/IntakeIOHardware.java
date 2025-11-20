package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class IntakeIOHardware implements IntakeIO{

    private CRServo intake_;

    public IntakeIOHardware(HardwareMap hardwareMap){
        intake_ = hardwareMap.get(CRServo.class, "intake");

        intake_.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs){
        inputs.intakePow = intake_.getPower();
    }

    @Override
    public void setPow(double pow){
        intake_.setPower(pow);
    }

    @Override
    public void stop() {
        setPow(0);
    }
}
