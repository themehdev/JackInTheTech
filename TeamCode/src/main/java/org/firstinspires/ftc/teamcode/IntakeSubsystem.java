package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class IntakeSubsystem {

    private IntakeIO io_;

    private IntakeIO.IntakeIOInputs inputs_;

    private Gamepad gp_;

    public IntakeSubsystem(IntakeIO io, Gamepad gp){
        io_ = io;

        gp_ = gp;

        inputs_ = new IntakeIO.IntakeIOInputs();
    }

    public IntakeSubsystem(IntakeIO io){
        this(io, null);
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("intake", inputs_);
    }

    public void periodicTeleOp(){
        updateLogging();

        if(gp_.right_bumper){
            io_.setPow(1);
        }else if(gp_.left_bumper){
            io_.setPow(-1);
        }else{
            io_.setPow(0);
        }
    }
}
