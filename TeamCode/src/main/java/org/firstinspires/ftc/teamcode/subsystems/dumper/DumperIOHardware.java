package org.firstinspires.ftc.teamcode.subsystems.dumper;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DumperIOHardware implements DumperIO{
    DcMotor motor_;

    public DumperIOHardware(HardwareMap hardwareMap){
        motor_ = hardwareMap.get(DcMotor.class, "dumper");
        motor_.setDirection(DcMotor.Direction.FORWARD);
        motor_.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void setPow(double pow){
        motor_.setPower(pow);
    }

    public void updateInputs(DumperIOInputs inputs){
        inputs.pos = motor_.getCurrentPosition();
        inputs.pow = motor_.getPower();
    }
}
