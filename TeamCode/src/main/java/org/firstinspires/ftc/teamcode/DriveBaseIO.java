package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;

import com.blazedeveloper.chrono.structure.LogTable;
import com.blazedeveloper.chrono.structure.LoggableInputs;

public interface DriveBaseIO {

    public static class DriveBaseIOInputs implements LoggableInputs {
        double frPos = 0.0;
        double flPos = 0.0;
        double brPos = 0.0;
        double blPos = 0.0;

//        double frVel = 0.0;
//        double flVel = 0.0;
//        double brVel = 0.0;
//        double blVel = 0.0;

        double frPow = 0.0;
        double flPow = 0.0;
        double brPow = 0.0;
        double blPow = 0.0;

        double imuYawRad = 0.0;

        @Override
        public void toLog(@NonNull LogTable logTable) {
            logTable.put("fr/Pos", frPos);
            logTable.put("fr/Pow", frPow);

            logTable.put("fl/Pos", flPos);
            logTable.put("fl/Pow", flPow);

            logTable.put("br/Pos", brPos);
            logTable.put("br/Pow", brPow);

            logTable.put("bl/Pos", blPos);
            logTable.put("bl/Pow", blPow);

            logTable.put("imuYawRad", imuYawRad);
        }

        @Override
        public void fromLog(@NonNull LogTable logTable) {
            frPos = logTable.get("fr/Pos", frPos);
            frPow = logTable.get("fr/Pow", frPow);

            flPos = logTable.get("fl/Pos", flPos);
            flPow = logTable.get("fl/Pow", flPow);

            brPos = logTable.get("br/Pos", brPos);
            brPow = logTable.get("br/Pow", brPow);

            blPos = logTable.get("bl/Pos", blPos);
            blPow = logTable.get("bl/Pow", blPow);

            imuYawRad = logTable.get("imuYawRad", imuYawRad);
        }
    }

    public default void updateInputs (DriveBaseIOInputs inputs){}

    public default void resetYaw(){}

    public default void setDBPowers(double frPow, double flPow, double blPow, double brPow){}
    public default void setDBPowers(double leftPow, double rightPow){}
    public default void setDBPowers(double pow){}

    public default void stop(){}
}
