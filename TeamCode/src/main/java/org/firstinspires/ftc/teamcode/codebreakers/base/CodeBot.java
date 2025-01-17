package org.firstinspires.ftc.teamcode.codebreakers.base;

import org.firstinspires.ftc.library.IsaacBot;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcher;
import org.firstinspires.ftc.library.pixelcatcher.PixelCatcherConfig;
import org.firstinspires.ftc.teamcode.codebreakers.config.PixelCatcherCodeBotConfig;

/**
 *
 */
public class CodeBot extends IsaacBot {

    protected PixelCatcherConfig pixelCatcherConfig;

    protected PixelCatcher pixelCatcher;

    /**
     *
     */
    public CodeBot () {
        super();

        this.pixelCatcherConfig = new PixelCatcherCodeBotConfig();
        this.pixelCatcherConfig.robot = this;
    }

    /**
     *
     */
    public void initBot () {
        super.initBot();

        this.pixelCatcher = new PixelCatcher(this.pixelCatcherConfig);
        this.pixelCatcher.init();

        // this is where you can put "normal" bot code that
        // would happen in the constructor
    }

    /**
     * Happens before the play button
     */
    public void go () {
        super.go();

        // this is where you can put "normal" bot code that is
        // BEFORE 'waitForStart'
    }

    /**
     * Happens after the play button
     */
    public void run () {
        super.run();

        // this is where you can put "normal" bot code that is
        // AFTER 'waitForStart'
    }

}
