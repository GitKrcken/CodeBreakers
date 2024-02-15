package org.firstinspires.ftc.teamcode.codebreakers.base;

import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.component.event.ping.PingEvent;
import org.firstinspires.ftc.library.component.event.ping.PingHandler;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.library.utility.Direction;
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.codebreakers.config.SimpleDriveTrainCodeBotConfig;

@Autonomous(name="AutoCodeBot", group="Robot")
//@Disabled
public class AutoCodeBot extends CodeBot {

    /**
     *
     */
    protected SimpleDriveTrainConfig driveTrainConfig;

    /**
     *
     */
    protected SimpleDriveTrain driveTrain;

    /**
     */
    private Rev2mDistanceSensor sonar;

    /**
     */
    protected Direction trussDirection;

    /**
     */
    protected Direction backdropDirection;

    /**
     */
    protected double distanceToBackstage;

    /**
     * Constructor
     *
     */
    public AutoCodeBot () {
       super();

       this.driveTrainConfig = new SimpleDriveTrainCodeBotConfig(this);
       this.driveTrainConfig.debug = false;
       this.setImuName(driveTrainConfig.imuName);
    }

    /**
     *
     */
    @Override
    public void initBot() {
        super.initBot();

        this.driveTrain = new SimpleDriveTrain(this.driveTrainConfig);
        this.driveTrain.init();

        DistanceSensor sonarDistanceSensor = hardwareMap.get(DistanceSensor.class, "sonarSensor");
        this.sonar = (Rev2mDistanceSensor) sonarDistanceSensor;
    }

    /**
     * Happens before the play button
     */
    public void go () {
        super.go();

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.driveTrain.forward(0.2, 0.5, 53, Units.Centimeters);
                AutoCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                AutoCodeBot.this.ping(new PingHandler() {
                    @Override
                    public void onPing(PingEvent event) {

                        AutoCodeBot.this.telemetry.addData("Ping Distance: ", "%2f", event.getDistance());
                        AutoCodeBot.this.telemetry.update();

                        command.markAsCompleted();

                        if (event.getDistance() < 35){
                            AutoCodeBot.this.placePurplePixelForwards();
                        }
                        else {
                            AutoCodeBot.this.scanOppositeTruss();
                        }
                    }
                });
            }
        });
    }

    protected void scanOppositeTruss() {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.driveTrain.gyroTurn(AutoCodeBot.this.trussDirection.invert(), 0.2, 0.2, 45);
                AutoCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                AutoCodeBot.this.ping(new PingHandler() {
                    @Override
                    public void onPing(PingEvent event) {

                        AutoCodeBot.this.telemetry.addData("Ping Distance: ", "%2f", event.getDistance());
                        AutoCodeBot.this.telemetry.update();

                        command.markAsCompleted();

                        if (event.getDistance() < 35){
                            AutoCodeBot.this.placePurplePixelOppositeTruss();
                        }
                        else {
                            AutoCodeBot.this.placePurplePixelTruss();
                        }
                    }
                });
            }
        });
    }


    /**
     * Happens after the play button
     */
    public void run () {
        super.run();

        this.driveTrain.run();
        this.pixelCatcher.run();
    }

    /**
     *
     */
    protected void dropYellowPixel() {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                AutoCodeBot.this.pixelCatcher.openArm(AutoCodeBot.this.trussDirection);

                AutoCodeBot.this.driveTrain.wait(1000)
                        .back(0.2, 0.2, 16, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                AutoCodeBot.this.pixelCatcher.closeArm(AutoCodeBot.this.trussDirection);
                                command.markAsCompleted();
                                AutoCodeBot.this.finish();
                            }
                        });
            }
        });

    }

    /**
     *
     */
    protected void placePurplePixelForwards()
    {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.driveTrain.forward(0.2, 0.2, 21, Units.Centimeters);
                AutoCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.pixelCatcher.openArm(AutoCodeBot.this.trussDirection.invert());
                AutoCodeBot.this.driveTrain.wait(1000)
                        .back(0.2, 0.2, 21, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.pixelCatcher.closeArm(AutoCodeBot.this.trussDirection.invert());
                AutoCodeBot.this.driveTrain.wait(0)
                        .back(0.2, 0.5, 36, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                                AutoCodeBot.this.goToBackstage(85);
                            }
                        });
            }
        });
    }

    protected void goToBackstage(int degrees) {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {

                AutoCodeBot.this.driveTrain.gyroTurn(AutoCodeBot.this.backdropDirection,0.2, 0.2, degrees);
                AutoCodeBot.this.driveTrain.forward(0.1, 0.5, AutoCodeBot.this.distanceToBackstage, Units.Centimeters);
                AutoCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        AutoCodeBot.this.dropYellowPixel();
                    }
                });
            }
        });
    }

    /**
     *
     */
    protected void finish () {
        this.driveTrain.back(0.2, 0.2, 8, Units.Centimeters);
        this.driveTrain.gyroTurn(this.backdropDirection.invert(),0.2, 0.2, 90);
        this.driveTrain.back(0.2, 0.2, 8, Units.Centimeters);
    }

    /**
     *
     * @param handler
     */
    protected void ping (PingHandler handler) {
        handler.onPing(new PingEvent(0, AutoCodeBot.this.sonar.getDistance(DistanceUnit.CM), DistanceUnit.CM));
    }

    /**
     *
     */
    protected void placePurplePixelOppositeTruss()
    {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.driveTrain.gyroTurn(AutoCodeBot.this.trussDirection, 0.2, 0.3, 10);
                AutoCodeBot.this.driveTrain.forward(0.2, 0.2, 17, Units.Centimeters);
                AutoCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {

                AutoCodeBot.this.pixelCatcher.openArm(AutoCodeBot.this.trussDirection.invert());
                AutoCodeBot.this.driveTrain.wait(1000)
                        .back(0.2, 0.2, 17, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.pixelCatcher.closeArm(AutoCodeBot.this.trussDirection.invert());
                AutoCodeBot.this.driveTrain.gyroTurn(AutoCodeBot.this.trussDirection, 0.2, 0.2, 35);
                AutoCodeBot.this.driveTrain.wait(0)
                        .back(0.2, 0.5, 36, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                                AutoCodeBot.this.goToBackstage(85);
                            }
                        });
            }
        });
    }

    /**
     *
     */
    protected void placePurplePixelTruss()
    {
        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.driveTrain.gyroTurn(AutoCodeBot.this.trussDirection, 0.2, 0.2, 95);
                AutoCodeBot.this.driveTrain.forward(0.2, 0.2, 15, Units.Centimeters);
                AutoCodeBot.this.driveTrain.wait(0, new CommandCallbackAdapter(this){
                    public void onSuccess(CommandSuccessEvent successEvent) {
                        this.command.markAsCompleted();
                        outerCommand.markAsCompleted();
                    }
                });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.pixelCatcher.openArm(AutoCodeBot.this.trussDirection.invert());
                AutoCodeBot.this.driveTrain.wait(1000)
                        .back(0.2, 0.2, 15, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                            }
                        });
            }
        });

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand outerCommand) {
                AutoCodeBot.this.pixelCatcher.closeArm(AutoCodeBot.this.trussDirection.invert());
                AutoCodeBot.this.driveTrain.gyroTurn(AutoCodeBot.this.trussDirection.invert(), 0.2, 0.3, 40);
                AutoCodeBot.this.driveTrain.wait(0)
                        .back(0.2, 0.5, 38, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                command.markAsCompleted();
                                AutoCodeBot.this.goToBackstage(85);
                            }
                        });
            }
        });
    }
}
