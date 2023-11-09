package org.firstinspires.ftc.teamcode.codebreakers.config;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.drivetrain.MecanumDriveTrainConfig;

/**
 *
 */
public class MecanumCodeBotConfig extends MecanumDriveTrainConfig {

    /**
     *
     */
    public MecanumCodeBotConfig (IsaacBot robot) {

        this.robot = robot;

        this.leftFrontDeviceName = "leftFrontMotor"; // port 0
        this.rightFrontDeviceName = "rightFrontMotor"; // port 1
        this.rightRearDeviceName = "rightRearMotor"; // port 2
        this.leftRearDeviceName = "leftRearMotor"; // port 3

        this.imuName = "imu";

        this.debug = false;

        this.accelerationIncrement = 0.01;

        this.maxPower = 0.6;

        this.yawOffset = -90;

        this.leftFrontMotorDirection = DcMotor.Direction.REVERSE;
        this.rightFrontMotorDirection = DcMotor.Direction.FORWARD;
        this.rightRearMotorDirection = DcMotor.Direction.FORWARD;
        this.leftRearMotorDirection = DcMotor.Direction.REVERSE;

        this.incrementalDeceleration = false;

        this.invertRightX = false;

        this.invertLeftX = true;

    }

}
