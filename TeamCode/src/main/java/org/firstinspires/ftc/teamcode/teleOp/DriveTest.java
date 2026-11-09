package org.firstinspires.ftc.teamcode.teleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class DriveTest extends LinearOpMode {
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor rearRight;
    private DcMotor rearLeft;

    private double x;
    private double y;
    private double rx;

    private double frontLeftPower;
    private double frontRightPower;
    private double rearLeftPower;
    private double rearRightPower;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        rearRight= hardwareMap.get(DcMotor.class, "rearRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()) {
            x = gamepad1.left_stick_x;
            y = -gamepad1.left_stick_y;
            rx = gamepad1.right_stick_x;

            frontLeftPower = y+x+rx;
            frontRightPower = y-x-rx;
            rearRightPower = y+x-rx;
            rearLeftPower = y-x+rx;

            frontLeft.setPower(frontLeftPower);
            frontRight.setPower(frontRightPower);
            rearRight.setPower(rearRightPower);
            rearLeft.setPower(rearLeftPower);

            telemetry.addData("frontLeft", frontLeftPower);
            telemetry.addData("frontRight", frontRightPower);
            telemetry.addData("rearRight", rearRightPower);
            telemetry.addData("rearLeft", rearLeftPower);
            telemetry.update();
        }
    }
}