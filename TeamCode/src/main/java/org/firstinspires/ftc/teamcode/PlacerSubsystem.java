package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PlacerSubsystem {

    private PlacerIO io_;

    private PlacerIO.PlacerIOInputs inputs_;

    private Gamepad gp_;

    public PlacerSubsystem(PlacerIO io, Gamepad gp){
        io_ = io;

        gp_ = gp;

        inputs_ = new PlacerIO.PlacerIOInputs();
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("placer", inputs_);
    }

    public void periodicTeleOp(){
        if(gp_.right_trigger > 0.5){
            io_.setPincherTargetPos(0.1);
        }else{
            io_.setPincherTargetPos(0);
        }

        if(gp_.left_trigger > 0.5){
            io_.setArmTargetPos(135);
        }else{
            io_.setArmTargetPos(0);
        }
    }
}
