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

    public PlacerSubsystem(PlacerIO io){
        this(io, null);
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("placer", inputs_);

        Logger.output("PlacerSubsystem/state", state_.toString());
    }

    public void setState(PlacingState state) {
        state_ = state;
    }

    public PlacingState getState() {
        return state_;
    }

    public void runState(){
        if(inputs_.armFinished){
            io_.setArmPower(0.1);
        }else{
            io_.setArmPower(0.5);
        }

        switch(state_){
            case Stowed:
                io_.setArmTargetPos(0);
                io_.setPincherTargetPos(-20);
                break;
            case ReadyToPlace:
                io_.setPincherTargetPos(10);
                io_.setArmTargetPos(30);
                break;
            case Placing:
                io_.setArmTargetPos(20);
                if(Math.abs(inputs_.armPos - inputs_.armTarget) < 5){
                    state_ = PlacingState.Releasing;
                }
                break;
            case Releasing:
                io_.setPincherTargetPos(-20);
                if(Math.abs(inputs_.pinchPos - inputs_.pinchTarget) < 8){
                    state_ = PlacingState.Stowed;
                }
        }
    }

    public void periodicTeleOp(){
        updateLogging();

        switch(state_){
            case Stowed:
                if(gp_.rightBumperWasPressed()){
                    state_ = PlacingState.ReadyToPlace;
                }
                break;
            case ReadyToPlace:
                if(gp_.rightBumperWasPressed()){
                    state_ = PlacingState.Placing;
                }
                break;
        }

        runState();

        if(gp_.leftBumperWasPressed()){
            state_ = PlacingState.Stowed;
        }
    }
}
