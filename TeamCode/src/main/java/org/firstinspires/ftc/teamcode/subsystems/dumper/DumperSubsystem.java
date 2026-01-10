package org.firstinspires.ftc.teamcode.subsystems.dumper;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotState;
import org.firstinspires.ftc.teamcode.subsystems.eventHandler.EventHandlerSubsystem;

public class DumperSubsystem {

    private Gamepad gp_;
    private DumperIO io_;

    private DumperIO.DumperIOInputs inputs_;

    public DumperSubsystem(DumperIO io, Gamepad gp){
        io_ = io;
        gp_ = gp;
        inputs_ = new DumperIO.DumperIOInputs();

    }

    public void setPower(double pow){
        io_.setPow(pow);
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);
        Logger.processInputs("dumper", inputs_);
    }

    public void periodicTeleOp(){
        updateLogging();
        switch (EventHandlerSubsystem.getState()) {
            case Default:
                io_.setPow(gp_.right_trigger - gp_.left_trigger);
                break;
            case PlacingTop:
                io_.setPow(1.0);
                if(EventHandlerSubsystem.timerIsDone()){
                    EventHandlerSubsystem.setState(RobotState.BackingUp);
                    io_.setPow(0.0);
                }
            case PlacingBottom:
                io_.setPow(-1.0);
                if(EventHandlerSubsystem.timerIsDone()){
                    EventHandlerSubsystem.setState(RobotState.BackingUp);
                    io_.setPow(0.0);
                }
            default:
                io_.setPow(0.0);
        }
    }
}
