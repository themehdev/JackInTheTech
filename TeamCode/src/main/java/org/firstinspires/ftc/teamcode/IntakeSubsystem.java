package org.firstinspires.ftc.teamcode;

import com.blazedeveloper.chrono.Logger;
import com.qualcomm.robotcore.hardware.Gamepad;

public class IntakeSubsystem {

    private IntakeIO io_;

    private IntakeIO.IntakeIOInputs inputs_;

    private Gamepad gp_;

    public IntakeSubsystem(IntakeIO io, Gamepad gp){
        io_ = io;

        gp_ = gp;

        inputs_ = new IntakeIO.IntakeIOInputs();
    }

    public IntakeSubsystem(IntakeIO io){
        this(io, null);
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);

        Logger.processInputs("intake", inputs_);
    }

    public void periodicTeleOp(){
        updateLogging();

        io_.setPow(gp_.right_bumper ? 0.75 : (gp_.square ? -0.75 : 0));
    }
}
