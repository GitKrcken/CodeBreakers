package org.firstinspires.ftc.teamcode.codebreakers.config;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrainConfig;
import org.firstinspires.ftc.library.utility.Units;

/**
 *
 */
public class SimpleDriveTrainCodeBotConfig extends SimpleDriveTrainConfig {

    public SimpleDriveTrainCodeBotConfig (IsaacBot robot) {

        this.robot = robot;

        this.leftFrontDeviceName = "leftFrontMotor"; // port 0
        this.rightFrontDeviceName = "rightFrontMotor"; // port 1
        this.rightRearDeviceName = "rightRearMotor"; // port 2
        this.leftRearDeviceName = "leftRearMotor"; // port 3

        this.imuName = "imu";

        this.debug = false;

        this.motorTicsPerRev = 537.7;
        this.wheelDiameterCm = 9.6;
        this.rampUpDistanceCm = 50;
        this.rampDownDistanceCm = 50;
    }






}
