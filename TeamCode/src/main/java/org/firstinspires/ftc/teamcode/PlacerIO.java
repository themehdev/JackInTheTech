package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

public interface PlacerIO {

    public class PlacerIOInputs implements LoggableInputs {

        double armPos = 0.0;
        double armTarget = 0.0;
        double armPow = 0.0;

        double pincherPos = 0.0;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("arm/pos", armPos);
            logTable.put("arm/target", armTarget);
            logTable.put("arm/pow", armPow);

            logTable.put("pincher/pos", pincherPos);
        }

        public void fromLog(@NonNull LogTable logTable) {
            armPos = logTable.get("arm/pos", armPos);
            armTarget = logTable.get("arm/target", armTarget);
            armPow = logTable.get("arm/pow", armPow);

            pincherPos = logTable.get("pincher/pos", pincherPos);
        }
    }

    public default void updateInputs(PlacerIOInputs inputs){}

    public default void setPincherTargetPos(double pos){}

    public default void setArmTargetPos(double pos){}
}
