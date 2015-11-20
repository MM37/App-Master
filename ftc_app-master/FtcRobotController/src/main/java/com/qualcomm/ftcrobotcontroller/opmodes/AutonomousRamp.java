package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by rkhaj on 11/14/2015.
 */
public class AutonomousRamp extends LinearOpMode{

    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;

    public void moveForward(double power, long time) throws InterruptedException{
        lfMotor.setPower(power);
        lbMotor.setPower(power);
        rbMotor.setPower(power);
        rfMotor.setPower(power);
        sleep(time * 1000);
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
        lfMotor.setPower(-power);
        lbMotor.setPower(-power);
        rbMotor.setPower(power);
        rfMotor.setPower(power);
        sleep(time * 1000);
        lfMotor.setPower(0);
        lbMotor.setPower(0);
        rbMotor.setPower(0);
        rfMotor.setPower(0);
        sleep(500);
    }

    public void moveForwardEncoder(double power, double inches) {
        double ticks = inches * 1220 / (4 * Math.PI);
        byte direction=1;
        if (power<0) {
            direction = -1;
        }
        lfMotor.setTargetPosition(lfMotor.getCurrentPosition() + direction * (int) ticks);
        lbMotor.setTargetPosition(lbMotor.getCurrentPosition() + direction * (int) ticks);
        rfMotor.setTargetPosition(rfMotor.getCurrentPosition() + direction * (int) ticks);
        rbMotor.setTargetPosition(rbMotor.getCurrentPosition() + direction * (int) ticks);
        lfMotor.setPower(power);
        lbMotor.setPower(power);
        rfMotor.setPower(power);
        rbMotor.setPower(power);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        lfMotor = hardwareMap.dcMotor.get("lfMotor");
        lbMotor = hardwareMap.dcMotor.get("lbMotor");
        rfMotor = hardwareMap.dcMotor.get("rfMotor");
        rbMotor = hardwareMap.dcMotor.get("rbMotor");

        rbMotor.setDirection(DcMotor.Direction.REVERSE);
        lfMotor.setDirection(DcMotor.Direction.REVERSE);

        waitOneFullHardwareCycle();
        waitForStart();

        moveForward(0.75, 2);
        turnRight(0.75, 1100);
        moveForward(0.75, 2);
    }
}