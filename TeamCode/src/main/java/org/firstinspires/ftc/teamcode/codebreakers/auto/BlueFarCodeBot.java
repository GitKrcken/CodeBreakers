package org.firstinspires.ftc.teamcode.codebreakers.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
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
            public void runOnce(ICommand outerCommand) {
                //BlueFarCodeBot.this.driveTrain.wait(10000); // give other robot time, maybe not needed when doing purple pixel autonomous
                BlueFarCodeBot.this.driveTrain.forward(0.2, 0.5, 53, Units.Centimeters);
                BlueFarCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                BlueFarCodeBot.this.ping(new PingHandler() {
                    @Override
                    public void onPing(PingEvent event) {

                        BlueFarCodeBot.this.telemetry.addData("Ping Distance: ", "%2f", event.getDistance());
                        BlueFarCodeBot.this.telemetry.update();

                        command.markAsCompleted();

                        if (event.getDistance() < 35){
                            BlueFarCodeBot.this.placePurplePixelForwards();
                        }
                    }
                });
            }
        });

    }

    protected void placePurplePixelForwards()
    {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                BlueFarCodeBot.this.driveTrain.forward(0.2, 0.2, 21, Units.Centimeters);
                BlueFarCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                BlueFarCodeBot.this.pixelCatcher.openLeftArm();
                BlueFarCodeBot.this.driveTrain.wait(1000)
                        .back(0.2, 0.2, 21, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                                //AutoCodeBot.this.finish();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                BlueFarCodeBot.this.pixelCatcher.closeLeftArm();
                BlueFarCodeBot.this.driveTrain.wait(0)
                        .back(0.2, 0.5, 43, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                                //AutoCodeBot.this.finish();
                            }
                        });
            }
        });
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                BlueFarCodeBot.this.driveTrain.gyroTurnLeft(0.1, 0.2, 85, Units.Centimeters);
                BlueFarCodeBot.this.driveTrain.forward(0.1, 0.5, 225, Units.Centimeters);
                BlueFarCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        BlueFarCodeBot.this.dropPixels();
                    }
                });
            }
        });

//        this.addCommand(new OneTimeSynchronousCommand() {
//            public void runOnce(ICommand command) {
//                BlueFarCodeBot.this.driveTrain.back(0.2, 0.2, 21, Units.Centimeters);
//                BlueFarCodeBot.this.driveTrain.back(0.2, 0.5, 43, Units.Centimeters);
//                BlueFarCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
//                    public void onSuccess(CommandSuccessEvent successEvent) {
//                        this.command.markAsCompleted();
//
//                        telemetry.addLine("HERE");
//                        telemetry.update();
//                    }
//                });
//            }
//        });

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
