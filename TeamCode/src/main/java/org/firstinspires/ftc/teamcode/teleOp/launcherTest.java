package org.firstinspires.ftc.teamcode.teleOp;
import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

@Configurable
@TeleOp
public class launcherTest extends LinearOpMode {
    private DcMotorEx motor;

    private double ticksPerSec;
    private double motorRpm;
    private double wheelRpm;
    private double motorPower;
    private double error;
    private double lastError;

    private double integralSum = 0;
    private ElapsedTime timer = new ElapsedTime();

    private double targetRpm;

    public static double targetWheelRpm = 0;
    public static double p = 0.001;
    public static double i = 0;
    public static double d = 0;
    public static double f = 0.00016;
    public static double maxIntegral = 1;

    private TelemetryManager panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();
        timer.reset();
        if(isStopRequested()) return;
        while (opModeIsActive()){
            ticksPerSec = motor.getVelocity();
            motorRpm = ticksPerSec / 28.0 * 60.0;
            wheelRpm = motorRpm / 1.4;

            targetRpm = targetWheelRpm * 1.4;

            if (gamepad1.dpadDownWasPressed()) {
                targetRpm -= 100;
            }
            else if (gamepad1.dpadUpWasPressed()) {
                targetRpm += 100;
            }

            motorPower = clamp(PIDF(p, i, d, f), -1, 1);
            motor.setPower(motorPower);

            telemetry.addData("wheel RPM", wheelRpm);
            telemetry.addData("motor rpm", motorRpm);
            telemetry.addData("targetRpm", targetRpm);
            telemetry.addData("motorPower", motorPower);
            telemetry.addData("f", f);
            telemetry.update();

            panelsTelemetry.addData("motor rpm", motorRpm);
            panelsTelemetry.addData("targetRpm", targetRpm);
            panelsTelemetry.addData("motorPower", motorPower);
            panelsTelemetry.addData("f", f);
            panelsTelemetry.update(telemetry);
        }
    }

    public double PIDF(double p, double i, double d, double f) {
        error = targetRpm - motorRpm;

        double dt = timer.seconds();
        timer.reset();
        integralSum += error * dt;
        integralSum = Math.max(-maxIntegral / i, Math.min(maxIntegral / i, integralSum));
        double iTerm = i * integralSum;

        double pTerm = p * error;
        double dTerm = d * (error - lastError) / dt;
        double fTerm = f * targetRpm;

        lastError = error;

        return pTerm + iTerm + dTerm + fTerm;
    }

    public double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}