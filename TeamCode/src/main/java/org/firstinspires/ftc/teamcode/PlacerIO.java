package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

public interface PlacerIO {

    public class PlacerIOInputs implements LoggableInputs {

        double armPow = 0.0;
        double armTarget = 0.0;


        double pinchPos = 0.0;
        double pinchTarget = 0.0;

        PlacerSubsystem.PlacingState state = PlacerSubsystem.PlacingState.Stowed;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("arm/pos", armPow);
            logTable.put("arm/target", armTarget);

            logTable.put("pincher/pos", pinchPos);
            logTable.put("pincher/target", pinchTarget);
        }

        public void fromLog(@NonNull LogTable logTable) {
            armPow = logTable.get("arm/pos", armPow);
            armTarget = logTable.get("arm/target", armTarget);

            pinchPos = logTable.get("pincher/pos", pinchPos);
            pinchTarget = logTable.get("pincher/target", pinchTarget);
        }
    }

    public default void updateInputs(PlacerIOInputs inputs){}

    public default void setPincherTargetPos(double pos){}

    public default void setArmTargetVel(double vel){}
}
