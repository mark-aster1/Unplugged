package org.firstinspires.ftc.teamcode.teleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class andreiRobo extends LinearOpMode {
    private DcMotor motorA;
    private DcMotor motorB;

    private Servo servoTurn;
    private Servo servo1;
    private Servo servo2;

    private boolean grabbing = true;

    @Override
    public void runOpMode() throws InterruptedException {
        motorA = hardwareMap.get(DcMotor.class,"drive");
        motorB = hardwareMap.get(DcMotor.class,"turn");
        servoTurn = hardwareMap.get(Servo.class,"servoTurn");
        servo1 = hardwareMap.get(Servo.class,"servo1");
        servo2  = hardwareMap.get(Servo.class,"servo2");

        servoTurn.setPosition(0.5);
        servo1.setPosition(0.5);
        servo2.setPosition(0.5);

        waitForStart();
        if(isStopRequested()) return;
        while (opModeIsActive()){
            motorA.setPower(gamepad1.left_stick_y);
            motorB.setPower(-gamepad1.right_stick_x);

            if(gamepad1.xWasPressed()) grabbing = !grabbing;
            if(grabbing == false)
            {
                servoTurn.setPosition(0.1);
                servo1.setPosition(0.1);
                servo2.setPosition(0.1);
            }
            else
            {
                servoTurn.setPosition(0.5);
                servo1.setPosition(0.5);
                servo2.setPosition(0.5);
            }

        }
    }
}