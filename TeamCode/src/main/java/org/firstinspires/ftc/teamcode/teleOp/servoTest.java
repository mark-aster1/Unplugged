package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class servoTest extends LinearOpMode {

    private Servo left;
    private Servo right;
    private double position = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        left = hardwareMap.get(Servo.class, "left");
        right = hardwareMap.get(Servo.class, "right");

        left.setDirection(Servo.Direction.REVERSE);

        left.setPosition(position);
        right.setPosition(position);

        waitForStart();

        while (opModeIsActive())
        {
            if(gamepad1.dpadUpWasPressed()) {
                position+=0.01;
                left.setPosition(position);
                right.setPosition(position);
            }
            else if(gamepad1.dpadDownWasPressed()) {
                position-=0.01;
                left.setPosition(position);
                right.setPosition(position);
            }

            telemetry.addData("Servo Position", position);
            telemetry.update();
        }
    }
}