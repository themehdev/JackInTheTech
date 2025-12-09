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
            io_.setPincherTargetPow(0.5);
        }else{
            if(timer_ > Logger.timestamp()){
                io_.setPincherTargetPow(-1.0);
            }else{
                io_.setPincherTargetPow(0.0);
            }
        }
    }

    public void setArmTargetVel(double vel){
        io_.setArmTargetPow(vel);
    }

    public void periodicTeleOp(){
        updateLogging();

        runGrabber();

        if(gp_.leftBumperWasPressed()){
            grabbing_ = !grabbing_;
            timer_ = Logger.timestamp() + 175_000_000;
        }

        io_.setArmTargetPow(gp_.right_trigger - gp_.left_trigger);
    }
}
