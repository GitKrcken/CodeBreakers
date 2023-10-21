package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.library.IsaacBot;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.teamcode.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.teamcode.library.utility.Units;

/**
 * 
 */
@Autonomous(name="Robot: SelfDrive", group="Robot")
@Disabled
public class SelfDrive extends IsaacBot
{
    /**
     */
    private final SimpleDriveTrain driveTrain;

    //private MecanumDriveTrain driveTrain;

    /**
     */
    public SelfDrive ()
    {
        super();

        SimpleDriveTrainConfig driveTrainConfig = new SimpleDriveTrainConfig();

        driveTrainConfig.robot = this;
        driveTrainConfig.leftFrontDeviceName  = "leftFrontDrive";
        driveTrainConfig.rightFrontDeviceName = "rightFrontDrive";
        driveTrainConfig.rightRearDeviceName  = "rightRearDrive";
        driveTrainConfig.leftRearDeviceName   = "leftRearDrive";
        driveTrainConfig.imuName = "imu";

        driveTrainConfig.motorTicsPerRev = 537.7;
        driveTrainConfig.wheelDiameterCm = 9.6;
        driveTrainConfig.rampUpDistanceCm = 50;
        driveTrainConfig.rampDownDistanceCm = 50;

        // driveTrainConfig.wheelBaseCm = 29;
        // driveTrainConfig.turningRadiusCm = 29;

        this.driveTrain = new SimpleDriveTrain(driveTrainConfig);
    }

    @Override
    public void runOpMode() throws InterruptedException
    {
        this.driveTrain.init();

        telemetry.addLine("Drive train initialized. Ready.");
        telemetry.update();

        waitForStart();

        this.driveTrain.forward(0.1, 0.8, 1, Units.Meters);

        sleep(250);

        this.driveTrain.gyroTurnLeft(0.05, 0.2, 90);

        sleep(250);

        this.driveTrain.forward(0.1, 0.8, 1, Units.Meters);
    }
}