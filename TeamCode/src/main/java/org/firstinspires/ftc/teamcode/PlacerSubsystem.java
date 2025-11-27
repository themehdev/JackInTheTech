package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PlacerSubsystem {

    public enum PlacingState{
        Stowed,
        ReadyToPlace,
        Placing,
        Releasing
    }

    private PlacerIO io_;

    private PlacerIO.PlacerIOInputs inputs_;

    private Gamepad gp_;

    private PlacingState state_;

    public PlacerSubsystem(PlacerIO io, Gamepad gp){
        io_ = io;

        gp_ = gp;

        inputs_ = new PlacerIO.PlacerIOInputs();

        state_ = PlacingState.Stowed;
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("placer", inputs_);

        Logger.output("PlacerSubsystem/state", state_.toString());
    }

    public void periodicTeleOp(){
        updateLogging();

        switch(state_){
            case Stowed:
                io_.setArmTargetPos(0);
                io_.setPincherTargetPos(-20);

                if(gp_.rightBumperWasPressed()){
                    io_.setPincherTargetPos(10);
                    io_.setArmTargetPos(180);
                    state_ = PlacingState.ReadyToPlace;
                }
                break;
            case ReadyToPlace:
                if(gp_.rightBumperWasPressed()){
                    io_.setArmTargetPos(210);
                    state_ = PlacingState.Placing;
                }
                break;
            case Placing:
                if(Math.abs(inputs_.armPos - inputs_.armTarget) < 5){
                    io_.setPincherTargetPos(-20);
                    state_ = PlacingState.Releasing;
                }
                break;
            case Releasing:
                if(Math.abs(inputs_.pinchPos - inputs_.pinchTarget) < 8){
                    io_.setArmTargetPos(0);
                    state_ = PlacingState.Stowed;
                }
        }
    }
}
