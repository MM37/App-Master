package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sahaj on 12/13/2015.
 */
public class AutonQualifier extends LinearOpMode {
    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;
    DcMotor bucketArmMotor;
    DcMotor pulleyMotor1;

    Servo lockServos;
    public static final double LOCK_SERVOS_CLOSED_POSITION = 0.99;
    public static final double LOCK_SERVOS_OPEN_POSITION = 0.40;

    public void moveForward(double power, long time) throws InterruptedException{
        lfMotor.setPower(power);
        lbMotor.setPower(power);
        rbMotor.setPower(power);
        rfMotor.setPower(power);
        sleep(time);
        lfMotor.setPower(0);
        lbMotor.setPower(0);
        rbMotor.setPower(0);
        rfMotor.setPower(0);
        sleep(500);
    }

    public void turnRight(double power, long time) throws InterruptedException{
        lfMotor.setPower(-power);
        lbMotor.setPower(-power);
        rbMotor.setPower(power);
        rfMotor.setPower(power);
        sleep(time);
        lfMotor.setPower(0);
        lbMotor.setPower(0);
        rbMotor.setPower(0);
        rfMotor.setPower(0);
        sleep(500);
    }

    public void turnLeft(double power, long time) throws InterruptedException{
        lfMotor.setPower(power);
        lbMotor.setPower(power);
        rbMotor.setPower(-power);
        rfMotor.setPower(-power);
        sleep(time);
        lfMotor.setPower(0);
        lbMotor.setPower(0);
        rbMotor.setPower(0);
        rfMotor.setPower(0);
        sleep(500);
    }

    @Override
    public void runOpMode() throws InterruptedException{
        telemetry.addData("FRONT TOUCHES WALL!", "");

        Boolean red = false;

        lfMotor = hardwareMap.dcMotor.get("lfMotor");
        lbMotor = hardwareMap.dcMotor.get("lbMotor");
        rfMotor = hardwareMap.dcMotor.get("rfMotor");
        rbMotor = hardwareMap.dcMotor.get("rbMotor");
        bucketArmMotor = hardwareMap.dcMotor.get("armMotor");
        pulleyMotor1 = hardwareMap.dcMotor.get("pulley1");
        lockServos = hardwareMap.servo.get("lockServos");

        rfMotor.setDirection(DcMotor.Direction.REVERSE);
        rbMotor.setDirection(DcMotor.Direction.REVERSE);

        if (gamepad1.x)
            red = false;
        else if (gamepad1.b)
            red = true;

        telemetry.addData("Red?: ", red);

        waitOneFullHardwareCycle();
        waitForStart();

        sleep(10);

        moveForward(0.70, 1800);

        if(red)
            turnLeft(1.0, 1900);
        else
            turnRight(1.0,1900);

        sleep(100);
        bucketArmMotor.setPower(-0.60);
        sleep(200);
        bucketArmMotor.setPower(0.0);

        //cannot use moveForward because locking must happen during movement
        lfMotor.setPower(0.90);
        lbMotor.setPower(0.90);
        rbMotor.setPower(0.90);
        rfMotor.setPower(0.90);
        sleep(2000);
        lockServos.setPosition(LOCK_SERVOS_OPEN_POSITION);
        sleep(300);

        //moveForward used to stop motors
        moveForward(0.01, 100);

        telemetry.addData("DONE", "");

    }

}
