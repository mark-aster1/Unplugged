package org.firstinspires.ftc.teamcode.teleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class encoderTest extends LinearOpMode {
    private DcMotorEx motor;

    @Override
    public void runOpMode() throws InterruptedException {
        motor = hardwareMap.get(DcMotorEx.class, "motor");

        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()){
            double ticksPerSec = motor.getVelocity();
            double motorRpm = ticksPerSec / 28.0 * 60.0;
            double wheelRpm = motorRpm / 12.0;

            motor.setPower(1);

            telemetry.addData("wheel RPM", wheelRpm);
            telemetry.addData("motor rpm", motorRpm);
            telemetry.update();
        }
    }
}