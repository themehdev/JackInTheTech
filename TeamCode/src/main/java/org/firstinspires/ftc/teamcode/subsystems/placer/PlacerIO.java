package org.firstinspires.ftc.teamcode.subsystems.placer;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

public interface PlacerIO {

    public class PlacerIOInputs implements LoggableInputs {

        double armPow = 0.0;
        double armTarget = 0.0;


        double pinchPow = 0.0;
        double pinchTarget = 0.0;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("arm/pow", armPow);
            logTable.put("arm/target", armTarget);

            logTable.put("pincher/pow", pinchPow);
            logTable.put("pincher/target", pinchTarget);
        }

        public void fromLog(@NonNull LogTable logTable) {
            armPow = logTable.get("arm/pow", armPow);
            armTarget = logTable.get("arm/targetVel", armTarget);

            pinchPow = logTable.get("pincher/pow", pinchPow);
            pinchTarget = logTable.get("pincher/targetVel", pinchTarget);
        }
    }

    public default void updateInputs(PlacerIOInputs inputs){}

    public default void setPincherTargetPow(double vel){}

    public default void setArmTargetPow(double vel){}
}
