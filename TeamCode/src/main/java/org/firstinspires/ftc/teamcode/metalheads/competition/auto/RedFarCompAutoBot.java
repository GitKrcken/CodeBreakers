package org.firstinspires.ftc.teamcode.metalheads.competition.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;
import org.firstinspires.ftc.library.utility.Units;

/**
 *
 */
@Autonomous(name="RedFarCompAutoBot", group="Competition")
@Disabled
public class RedFarCompAutoBot extends CompAutoBot {

    /**
     * Constructor
     */
    public RedFarCompAutoBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Red Far Auto Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
        this.driveTrain.gyroTurnRight(0.1, 0.5, 90);
        this.driveTrain.forward(0.1, 0.5, 243, Units.Centimeters);
    }

}
