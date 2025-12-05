package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PlacerSubsystem {

    private PlacerIO io_;

    private PlacerIO.PlacerIOInputs inputs_;

    private Gamepad gp_;

    private boolean grabbing_;

    private double timer_;

    public PlacerSubsystem(PlacerIO io, Gamepad gp){
        io_ = io;

        gp_ = gp;

        inputs_ = new PlacerIO.PlacerIOInputs();

        grabbing_ = false;

        timer_ = 0.0;
    }

    public PlacerSubsystem(PlacerIO io){
        this(io, null);
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("placer", inputs_);

        Logger.output("PlacerSubsystem/grabbing", grabbing_);
    }

    public void setGrabbing(boolean grabbing){
        grabbing_ = grabbing;
    }

    public void runGrabber(){
        if(grabbing_){
            io_.setPincherTargetPos(5);
        }else{
            io_.setPincherTargetPos(-20);
        }
    }

    public void setArmTargetVel(double vel){
        io_.setArmTargetVel(vel);
    }

    public void periodicTeleOp(){
        updateLogging();

        runGrabber();

        if(gp_.leftBumperWasPressed()){
            grabbing_ = !grabbing_;
        }

        io_.setArmTargetVel(gp_.right_trigger - gp_.left_trigger);
    }
}
