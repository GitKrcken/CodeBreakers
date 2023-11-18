package org.firstinspires.ftc.teamcode.codebreakers.base;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.library.component.command.ICommand;
import org.firstinspires.ftc.library.component.command.OneTimeSynchronousCommand;
import org.firstinspires.ftc.library.component.event.command_callback.CommandCallbackAdapter;
import org.firstinspires.ftc.library.component.event.command_callback.CommandSuccessEvent;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.library.utility.Units;
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
     * Constructor
     *
     */
    public AutoCodeBot () {
       super();

       this.driveTrainConfig = new SimpleDriveTrainCodeBotConfig(this);
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
}
