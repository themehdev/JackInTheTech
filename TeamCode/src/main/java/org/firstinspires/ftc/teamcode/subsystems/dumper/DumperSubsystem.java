package org.firstinspires.ftc.teamcode.subsystems.dumper;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DumperSubsystem {

    private Gamepad gp_;
    private DumperIO io_;

    private DumperIO.DumperIOInputs inputs_;

    public DumperSubsystem(DumperIO io, Gamepad gp){
        io_ = io;
        gp_ = gp;
        inputs_ = new DumperIO.DumperIOInputs();
    }

    public void updateLogging(){
        io_.updateInputs(inputs_);
    }

    public void periodicTeleOp(){
        updateLogging();
        io_.setPow(gp_.right_trigger - gp_.left_trigger);
    }
}
