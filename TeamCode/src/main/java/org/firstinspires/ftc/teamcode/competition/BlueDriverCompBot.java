package org.firstinspires.ftc.teamcode.competition;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.competition.base.CompDriverBot;

@TeleOp(name="BlueDriverCompBot", group="Competition OpMode")
//@Disabled
public class BlueDriverCompBot extends CompDriverBot {

    /**
     * Constructor
     *
     */
    public BlueDriverCompBot () {
        super();
    }

    /**
     *
     */
    public void initBot () {
        this.armConfig.debug = true;

        super.initBot();

        this.telemetry.addLine("Blue Driver Comp Bot Initialized...");
        this.telemetry.addLine("READY!");
        this.telemetry.update();
    }

    /**
     *
     */
    public void run () {
        super.run();
    }
}
