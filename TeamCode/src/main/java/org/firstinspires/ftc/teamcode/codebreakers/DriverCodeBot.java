package org.firstinspires.ftc.teamcode.codebreakers;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrain;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrainConfig;
import org.firstinspires.ftc.teamcode.codebreakers.base.CodeBot;
import org.firstinspires.ftc.teamcode.codebreakers.config.MecanumCodeBotConfig;

@TeleOp(name="DriverCodeBot", group="Robot")
//@Disabled
public class DriverCodeBot extends CodeBot {

    /**
     */
    protected MecanumDriveTrainConfig driveTrainConfig;

    /**
     */
    protected MecanumDriveTrain driveTrain;

    public DriverCodeBot () {
        super();

        this.driveTrainConfig = new MecanumCodeBotConfig(this);
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.driveTrain = new MecanumDriveTrain(this.driveTrainConfig);

    }


    /**
     * Happens before the play button
     */
    public void go () {
        super.go();

        this.driveTrain.init();
    }

    /**
     * Happens after the play button
     */
    public void run () {
        super.run();

        driveTrain.run();
        driveTrain.run();
        this.pixelCatcher.run();
        driveTrain.run();
        driveTrain.run();
    }

}
