package org.firstinspires.ftc.teamcode.codebreakers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

        this.driveTrain.forward(0.2, 0.3, 10, Units.Centimeters);
    }

    /**
     * Happens after the play button
     */
    public void run () {
        super.run();

        this.driveTrain.run();
    }

}
