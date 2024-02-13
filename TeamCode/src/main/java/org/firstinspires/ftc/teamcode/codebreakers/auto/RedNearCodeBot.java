package org.firstinspires.ftc.teamcode.codebreakers.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.teamcode.codebreakers.base.AutoCodeBot;

/**
 *
 */
@Autonomous(name="RedNearCodeBot", group="Red")
//@Disabled
public class RedNearCodeBot extends AutoCodeBot {

    /**
     * Constructor
     */
    public RedNearCodeBot() {
        super();
    }

    /**
     *
     */
    public void initBot () {

        super.initBot();

        telemetry.addLine("Red Near Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                RedNearCodeBot.this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
                RedNearCodeBot.this.driveTrain.gyroTurnRight(0.1, 0.5, 90);
                RedNearCodeBot.this.driveTrain.forward(0.1, 0.5, 110, Units.Centimeters);
                RedNearCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        RedNearCodeBot.this.dropPixels();
                    }
                });
            }
        });
    }

    /**
     *
     */
    public void run () { super.run(); }

    protected void finish () {
        this.driveTrain.gyroTurnLeft(0.1, 0.4, 90, Units.Centimeters);
        this.driveTrain.back(0.1, 0.2, 8, Units.Centimeters);
    }

}
