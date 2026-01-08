package org.firstinspires.ftc.teamcode.subsystems.dumper;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

public interface DumperIO {
    public class DumperIOInputs implements LoggableInputs {
        double pos = 0.0;
        double vel = 0.0;
        double pow = 0.0;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("dumper/pos", pos);
            logTable.put("dumper/pow", pow);
        }

        @Override
        public void fromLog(@NonNull LogTable logTable) {
            pos = logTable.get("dumper/pos", pos);
            pow = logTable.get("dumper/pow", pow);
        }
    }

    public default void updateInputs(DumperIOInputs inputs){}

    public default void setPow(double pow){}
}
