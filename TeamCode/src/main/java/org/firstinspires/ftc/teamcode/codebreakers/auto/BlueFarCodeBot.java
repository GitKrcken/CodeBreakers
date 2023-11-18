package org.firstinspires.ftc.teamcode.codebreakers.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
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

        telemetry.addLine("Blue Far Auto Initialized...");
        telemetry.addLine("READY!");
        telemetry.update();
    }

    /**
     *
     */
    public void go () {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                BlueFarCodeBot.this.driveTrain.wait(10000);
                BlueFarCodeBot.this.driveTrain.forward(0.1, 0.2, 8, Units.Centimeters);
                BlueFarCodeBot.this.driveTrain.gyroTurnLeft(0.1, 0.5, 90);
                BlueFarCodeBot.this.driveTrain.forward(0.1, 0.5, 227, Units.Centimeters);
                BlueFarCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        BlueFarCodeBot.this.dropPixels();
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
        this.driveTrain.gyroTurnRight(0.1, 0.4, 90);
        this.driveTrain.back(0.1, 0.2, 8, Units.Centimeters);
    }
}
