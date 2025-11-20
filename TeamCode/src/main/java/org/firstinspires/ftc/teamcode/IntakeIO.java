package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

public interface IntakeIO {

    public class IntakeIOInputs implements LoggableInputs {
        double intakePow = 0.0;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("intake/pow", intakePow);
        }

        @Override
        public void fromLog(@NonNull LogTable logTable) {
            intakePow = logTable.get("intake/pow", intakePow);
        }
    }

    public default void updateInputs (IntakeIOInputs inputs){}

    public default void setPow(double pow){}

    public default void stop(){}
}
