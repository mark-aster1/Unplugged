package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class servoTest extends LinearOpMode {
    //variables
    DcMotor drive;
    DcMotor turn;
    Servo rotate;
    Servo brat1;
    Servo brat2;
    private double motorPower;
    private double turnPower;

    @Override
    public void runOpMode() throws InterruptedException {
        //init
        drive = hardwareMap.get(DcMotor.class, "drive");
        turn = hardwareMap.get(DcMotor.class, "turn");

        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()){
            //start
            motorPower = -gamepad1.left_stick_y;
            drive.setPower(motorPower);

            turnPower = -gamepad1.right_stick_x;

            turn.setPower(turnPower);
        }
    }
}