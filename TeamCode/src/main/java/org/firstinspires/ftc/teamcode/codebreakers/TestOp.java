package org.firstinspires.ftc.teamcode.codebreakers;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

/**
 *
 */
//@Autonomous(name="Autonomous Test Op", group="Robot")
@TeleOp(name="Autonomous Test Op", group="Robot")
@Disabled
public class TestOp extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        // initialization that happens before start "play" button

        // wait for the play button to be pressed to continue
        this.waitForStart();

        // code that runs after play button

        while (this.opModeIsActive()) {

            // keep the robot alive

        }
    }
}
