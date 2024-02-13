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

        telemetry.addLine("Red Far Auto Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                RedFarCodeBot.this.driveTrain.wait(10000);
                RedFarCodeBot.this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
                RedFarCodeBot.this.driveTrain.gyroTurnRight(0.1, 0.5, 90);
                RedFarCodeBot.this.driveTrain.forward(0.1, 0.5, 227, Units.Centimeters);
                RedFarCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        RedFarCodeBot.this.dropPixels();
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
