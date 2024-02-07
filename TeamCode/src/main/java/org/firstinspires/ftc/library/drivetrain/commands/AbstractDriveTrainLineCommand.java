package org.firstinspires.ftc.library.drivetrain.commands;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.library.component.command.AbstractSynchronousCommand;
import org.firstinspires.ftc.library.drivetrain.SimpleDriveTrain;
import org.firstinspires.ftc.library.utility.Converter;
import org.firstinspires.ftc.library.utility.Units;

/**
 *
 */
public abstract class AbstractDriveTrainLineCommand extends AbstractSynchronousCommand {

    private double startPower;
    private double maxPower;
    private double distance;
    private Units units;

    protected SimpleDriveTrain driveTrain;

    private int tics;
    private int rampUpTics;
    private int rampUpTicsEnd;
    private int rampDownTics;
    private int rampDownTicsStart;

    private double powerBand;

    /**
     * Constructor
     */
    public AbstractDriveTrainLineCommand(SimpleDriveTrain driveTrain, double startPower, double maxPower, double distance, Units units) {
        super();

        this.driveTrain = driveTrain;
        this.startPower = startPower;
        this.maxPower = maxPower;
        this.distance = distance;
        this.units = units;
    }

    /**
     *
     */
    public void init () {
        if (units == null) {
            units = this.driveTrain.getConfig().defaultUnits;
        }

        this.distance = Converter.convertToCm(distance, units);
        this.tics = this.driveTrain.convertCmToTics(distance);

        this.driveTrain.resetMotorGroup();
        this.driveTrain.getMotorGroup().setTargetPosition(tics);
        this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_TO_POSITION);

        this.driveTrain.getMotorGroup().setPower(startPower);

        this.rampUpTics = this.driveTrain.convertCmToTics(this.driveTrain.getConfig().rampUpDistanceCm);
        this.rampDownTics = this.driveTrain.convertCmToTics(this.driveTrain.getConfig().rampDownDistanceCm);

        if ((this.rampUpTics + this.rampDownTics) > this.tics)
        {
            this.rampUpTics = tics / 2;
            this.rampDownTics = tics / 2;
        }

        this.rampUpTicsEnd = rampUpTics;
        this.rampDownTicsStart = tics - rampDownTics;

        this.powerBand = maxPower - startPower;

        this.setInitialized(true);
    }


    @Override
    public void run() {
        if (this.driveTrain.getMotorGroup().isBusy())
        {
            int currentPosition = this.driveTrain.getLeftFrontMotor().getCurrentPosition();

            if (this.driveTrain.getConfig().debug = true) {
                this.driveTrain.getRobot().telemetry.addData("Running to",  " %7d", tics);

                this.driveTrain.getRobot().telemetry.addData("Left Front Currently at",  " at %7d", this.driveTrain.getLeftFrontMotor().getCurrentPosition());
                this.driveTrain.getRobot().telemetry.addData("Left Front  Motor Power: ", " %f", this.driveTrain.getLeftFrontMotor().getPower());
                this.driveTrain.getRobot().telemetry.addLine("Left Front  Direction: " + this.driveTrain.getLeftFrontMotor().getDirection());
                this.driveTrain.getRobot().telemetry.addLine("Left Front Run Using Encoder: " + this.driveTrain.getLeftFrontMotor().getMode());

                this.driveTrain.getRobot().telemetry.addLine();

                this.driveTrain.getRobot().telemetry.addData("Right Front Currently at",  " at %7d", this.driveTrain.getRightFrontMotor().getCurrentPosition());
                this.driveTrain.getRobot().telemetry.addData("Right Front  Motor Power: ", " %f", this.driveTrain.getRightFrontMotor().getPower());
                this.driveTrain.getRobot().telemetry.addLine("Right Front  Direction: " + this.driveTrain.getRightFrontMotor().getDirection());
                this.driveTrain.getRobot().telemetry.addLine("Right Front Run Using Encoder: " + this.driveTrain.getRightFrontMotor().getMode());

                this.driveTrain.getRobot().telemetry.addLine();

                this.driveTrain.getRobot().telemetry.addData("Right Rear Currently at",  " at %7d", this.driveTrain.getRightRearMotor().getCurrentPosition());
                this.driveTrain.getRobot().telemetry.addData("Right Rear  Motor Power: ", " %f", this.driveTrain.getRightRearMotor().getPower());
                this.driveTrain.getRobot().telemetry.addLine("Right Rear  Direction: " + this.driveTrain.getRightRearMotor().getDirection());
                this.driveTrain.getRobot().telemetry.addLine("Right RearRun Using Encoder: " + this.driveTrain.getRightRearMotor().getMode());
                this.driveTrain.getRobot().telemetry.addData("Right Rear Target: ", "%7d", this.driveTrain.getRightRearMotor().getTargetPosition());

                this.driveTrain.getRobot().telemetry.addLine();

                this.driveTrain.getRobot().telemetry.addData("Left Rear Currently at",  " at %7d", this.driveTrain.getLeftRearMotor().getCurrentPosition());
                this.driveTrain.getRobot().telemetry.addData("Left Rear  Motor Power: ", " %f", this.driveTrain.getLeftRearMotor().getPower());
                this.driveTrain.getRobot().telemetry.addLine("Left Rear  Direction: " + this.driveTrain.getLeftRearMotor().getDirection());
                this.driveTrain.getRobot().telemetry.addLine("Left Rear Run Using Encoder: " + this.driveTrain.getLeftRearMotor().getMode());
                this.driveTrain.getRobot().telemetry.addData("Left Rear Target: ", "%7d", this.driveTrain.getLeftRearMotor().getTargetPosition());

                this.driveTrain.getRobot().telemetry.addLine();

                this.driveTrain.getRobot().telemetry.update();
            }

            if (currentPosition <= rampUpTicsEnd)
            {
                double newPower = startPower + (((double)currentPosition / (double)rampUpTics) * powerBand);
                this.driveTrain.getMotorGroup().setPower(newPower);
            }
            else if (currentPosition > rampDownTicsStart)
            {
                double newPower = maxPower - (((double)(currentPosition - rampDownTicsStart) / (double)rampDownTics) * powerBand);
                this.driveTrain.getMotorGroup().setPower(newPower);
            }
        }
        else {
            this.markAsCompleted();

            this.driveTrain.getMotorGroup().setPower(0);
            this.driveTrain.getMotorGroup().enableAll();
            this.driveTrain.getMotorGroup().setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

    }
}
