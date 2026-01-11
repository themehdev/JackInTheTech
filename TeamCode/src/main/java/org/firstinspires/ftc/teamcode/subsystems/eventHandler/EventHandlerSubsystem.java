package org.firstinspires.ftc.teamcode.subsystems.eventHandler;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.RobotState;

public final class EventHandlerSubsystem{

    private static EventHandlerIO io_;

    private static EventHandlerIO.EventHandlerIOInputs inputs_;

    private static Gamepad gp_;

    public EventHandlerSubsystem(EventHandlerIO io, Gamepad gp){
        io_ = io;
        gp_ = gp;
        inputs_ = new EventHandlerIO.EventHandlerIOInputs();
    }

    public static void setup(EventHandlerIO io, Gamepad gp){
        io_ = io;
        gp_ = gp;
        inputs_ = new EventHandlerIO.EventHandlerIOInputs();
    }

    public static RobotState getState(){
        return inputs_.state;
    }

    public static void setState(RobotState state){
        io_.setState(state);
    }

    public static void resetTimer(double seconds){
        io_.resetTimer(seconds);
    }

    public static boolean timerIsDone(){
        return inputs_.isDone;
    }

    public static void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("EventHandler", inputs_);
    }

    public static void periodicTeleOp(){
        updateLogging();
        if(gp_.leftBumperWasPressed() && inputs_.state.equals(RobotState.Default)){
            setState(RobotState.GoingToPlaceTop);
            resetTimer(1.0);
        }
        if(gp_.rightBumperWasPressed() && inputs_.state.equals(RobotState.Default)){
            setState(RobotState.GoingToPlaceBottomForward);
            resetTimer(1.0);
        }
        if(gp_.squareWasPressed() && inputs_.state.equals(RobotState.Default)){
            setState(RobotState.EndgamePark);
            resetTimer(2.0);
        }
    }

}
