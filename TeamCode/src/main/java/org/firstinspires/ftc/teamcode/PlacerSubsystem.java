package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class PlacerSubsystem {

    public enum PlacingState{
        Stowed,
        PickingUp,
        ReadyToPlace,
        Placing,
        Releasing
    }

    private PlacerIO io_;

    private PlacerIO.PlacerIOInputs inputs_;

    private Gamepad gp_;

    private PlacingState state_;

    private long timer_;

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
        Logger.output("PlacerSubsystem/timer", timer_ - Logger.timestamp());
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
                io_.setPincherTargetPos(20);
                break;
            case PickingUp:
                io_.setPincherTargetPos(-10);
                io_.setArmTargetPos(0);

                if(Logger.timestamp() > timer_){
                    state_ = PlacingState.ReadyToPlace;
                }
                break;
            case ReadyToPlace:
                io_.setPincherTargetPos(-10);
                io_.setArmTargetPos(30);
                break;
            case Placing:
                io_.setArmTargetPos(10);
                io_.setPincherTargetPos(-10);
                if(Math.abs(inputs_.armPos - inputs_.armTarget) < 2){
                    state_ = PlacingState.Releasing;
                    timer_ = Logger.timestamp() + 300_000_000;
                }
                break;
            case Releasing:
                io_.setPincherTargetPos(20);
                io_.setArmTargetPos(20);
                if(Logger.timestamp() > timer_){
                    state_ = PlacingState.Stowed;
                }
        }
    }

    public void periodicTeleOp(){
        updateLogging();

        switch(state_){
            case Stowed:
                if(gp_.rightBumperWasPressed()){
                    timer_ = Logger.timestamp() + 500_000_000;
                    state_ = PlacingState.PickingUp;
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
