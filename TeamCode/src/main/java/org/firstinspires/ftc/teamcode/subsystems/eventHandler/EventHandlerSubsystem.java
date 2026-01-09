package org.firstinspires.ftc.teamcode.subsystems.eventHandler;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.RobotState;

public class EventHandlerSubsystem{

    private EventHandlerIO io_;

    private EventHandlerIO.EventHandlerIOInputs inputs_;

    private Gamepad gp_;

    public EventHandlerSubsystem(EventHandlerIO io, Gamepad gp){
        io_ = io;
        gp_ = gp;
        inputs_ = new EventHandlerIO.EventHandlerIOInputs();
    }

    public RobotState getState(){
        return inputs_.state;
    }

    public void setState(RobotState state){
        io_.setState(state);
    }

    public void resetTimer(double seconds){
        io_.resetTimer(seconds);
    }

    public boolean timerIsDone(){
        return inputs_.isDone;
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("EventHandler", inputs_);
    }

    public void periodicTeleOp(){
        updateLogging();
        if(gp_.leftBumperWasPressed() && inputs_.state.equals(RobotState.Default)){
            inputs_.state = RobotState.GoingToPlaceTop;
            resetTimer(2.0);
        }
        if(gp_.rightBumperWasPressed() && inputs_.state.equals(RobotState.Default)){
            inputs_.state = RobotState.GoingToPlaceBottomForward;
            resetTimer(2.0);
        }
        if(gp_.squareWasPressed() && inputs_.state.equals(RobotState.Default)){
            inputs_.state = RobotState.EndgamePark;
            resetTimer(2.5);
        }
    }

}
