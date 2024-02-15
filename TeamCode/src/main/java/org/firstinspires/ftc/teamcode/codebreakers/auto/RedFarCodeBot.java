package org.firstinspires.ftc.teamcode.codebreakers.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.teamcode.codebreakers.base.AutoCodeBot;

/**
 *
 */
@Autonomous(name="RedFarCodeBot", group="Red")
//@Disabled
public class RedFarCodeBot extends AutoCodeBot {

    /**
     * Constructor
     */
    public RedFarCodeBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        this.trussDirection = Direction.RIGHT;
        this.backdropDirection = Direction.RIGHT;
        this.distanceToBackstage = 240;

        telemetry.addLine("Red Far AutoBot Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () { super.go(); }

    /**
     *
     */
    public void run () { super.run(); }
}
