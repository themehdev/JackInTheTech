package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class IntakeIOHardware implements IntakeIO{

    private DcMotor intake_;

    public IntakeIOHardware(HardwareMap hardwareMap){
        intake_ = hardwareMap.get(DcMotor.class, "intake");

        intake_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        intake_.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void updateInputs(IntakeIOInputs inputs){
        inputs.intakePow = intake_.getPower();
        inputs.intakePos = intake_.getCurrentPosition();
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
