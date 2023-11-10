package org.firstinspires.ftc.teamcode.codebreakers.config;

import org.firstinspires.ftc.library.Control;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;

public class PixelCatcherCodeBotConfig extends PixelCatcherConfig {
    public PixelCatcherCodeBotConfig(){
        this.leftArmServoName = "leftPixelServo";

        this.leftArmServoOpenedPos = 0.542;

        this.leftArmServoClosedPos = 0.176;

        this.leftArmServoInitPos = 0.198;

        this.leftArmIntiPos = PixelCatcher.ArmPosition.CLOSED;

        this.leftArmToggle = Control.Gp1_LeftTrigger_Down;

        this.rightArmServoName = "rightPixelServo";

        this.rightArmServoOpenedPos = 0.430;

        this.rightArmServoClosedPos = 0.763;

        this.rightArmServoInitPos = 0.711;

        this.rightArmIntiPos = PixelCatcher.ArmPosition.CLOSED;

        this.rightArmToggle = Control.Gp1_RightTrigger_Down;
    }
}

