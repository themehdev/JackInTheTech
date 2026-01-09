package org.firstinspires.ftc.teamcode.subsystems.eventHandler;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

import org.firstinspires.ftc.teamcode.RobotState;

public interface EventHandlerIO {
    public class EventHandlerIOInputs implements LoggableInputs{
        RobotState state = RobotState.Default;
        double timer = 0.0;
        double timerLength = 0.0;
        boolean isDone = true;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("state", state.name());

            logTable.put("timerLength", timerLength);
            logTable.put("timer", timer);
            logTable.put("isDone", isDone);
        }

        @Override
        public void fromLog(@NonNull LogTable logTable) {
            timerLength = logTable.get("timerLength", timerLength);
            timer = logTable.get("timer", timer);
            isDone = logTable.get("isDone", isDone);
        }
    }

    public default void setState(RobotState state){}

    public default void resetTimer(double seconds){}

    public default void updateInputs(EventHandlerIOInputs inputs){}
}
