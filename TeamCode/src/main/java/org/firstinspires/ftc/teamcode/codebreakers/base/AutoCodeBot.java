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
import org.firstinspires.ftc.library.utility.Units;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.codebreakers.config.SimpleDriveTrainCodeBotConfig;
import org.firstinspires.ftc.teamcode.metalheads.competition.base.CompAutoBot;

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
    }

    /**
     * Happens after the play button
     */
    public void run () {
        super.run();

        this.driveTrain.run();
        this.pixelCatcher.run();
    }

    protected void dropPixels () {

        this.addCommand(new OneTimeSynchronousCommand() {
            public void runOnce(ICommand command) {
                AutoCodeBot.this.pixelCatcher.openLeftArm();
                AutoCodeBot.this.pixelCatcher.openRightArm();

                AutoCodeBot.this.driveTrain.wait(1000)
                        .back(0.2, 0.2, 16, Units.Centimeters)
                        .wait(0, new CommandCallbackAdapter(this) {
                            public void onSuccess(CommandSuccessEvent successEvent) {
                                AutoCodeBot.this.pixelCatcher.closeLeftArm();
                                AutoCodeBot.this.pixelCatcher.closeRightArm();
                                command.markAsCompleted();
                                AutoCodeBot.this.finish();
                            }
                        });
            }
        });

    }

    protected void finish () {

    }

    protected void ping (PingHandler handler) {
        handler.onPing(new PingEvent(0, AutoCodeBot.this.sonar.getDistance(DistanceUnit.CM), DistanceUnit.CM));
    }
}
