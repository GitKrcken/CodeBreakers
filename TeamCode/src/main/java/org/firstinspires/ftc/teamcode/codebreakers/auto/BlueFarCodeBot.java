package org.firstinspires.ftc.teamcode.codebreakers.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.teamcode.codebreakers.base.AutoCodeBot;

/**
 *
 */
@Autonomous(name="BlueFarCodeBot", group="Blue")
//@Disabled
public class BlueFarCodeBot extends AutoCodeBot {

    /**
     * Constructor
     */
    public BlueFarCodeBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        this.trussDirection = Direction.LEFT;
        this.backdropDirection = Direction.LEFT;
        this.distanceToBackstage = 240;

        telemetry.addLine("Blue Far AutoBot Initialized...");
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
