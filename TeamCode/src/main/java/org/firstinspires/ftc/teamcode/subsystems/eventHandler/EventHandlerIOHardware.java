package org.firstinspires.ftc.teamcode.subsystems.eventHandler;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RobotState;

public class EventHandlerIOHardware implements EventHandlerIO{

    private RobotState state_;

    private ElapsedTime timer_;
    private double timerLength_;

    public EventHandlerIOHardware(){
        state_ = RobotState.Default;
        timer_ = new ElapsedTime();
        timerLength_ = 0.0;
    }

    public void resetTimer(double seconds){
        timer_.reset();
        timerLength_ = seconds;
    }

    public void setState(RobotState state){
        state_ = state;
    }

    public void updateInputs(EventHandlerIOInputs inputs){
        inputs.state = state_;
        inputs.timerLength = timerLength_;
        inputs.isDone = timer_.seconds() >= timerLength_;
        inputs.timer = inputs.isDone ? timerLength_ - timer_.seconds() : 0.0;

    }

}
