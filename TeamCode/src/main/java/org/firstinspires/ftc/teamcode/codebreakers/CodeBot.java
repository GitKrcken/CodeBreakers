package org.firstinspires.ftc.teamcode.codebreakers;

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
    }

    /**
     * Happens before the play button
     */
    public void go () {
        super.go();

        this.pixelCatcher.init();
    }

    /**
     * Happens after the play button
     */
    public void run () {
        super.run();

        this.pixelCatcher.run();
    }

}
