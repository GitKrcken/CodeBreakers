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
@Autonomous(name="BlueNearCodeBot", group="Blue")
//@Disabled
public class BlueNearCodeBot extends AutoCodeBot {

    /**
     * Constructor
     */
    public BlueNearCodeBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        this.trussDirection = Direction.RIGHT;
        this.backdropDirection = Direction.LEFT;
        this.distanceToBackstage = 110;

        telemetry.addLine("Blue Near AutoBot Initialized...");
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
