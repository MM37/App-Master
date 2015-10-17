package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by rkhaj on 10/16/2015.
 */
public class AutonomousRepairZone extends LinearOpMode {

    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;
    ColorSensor colorSensor;
    boolean isRed = true;

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
        lfMotor.setPower(power);
        lbMotor.setPower(power);
        rbMotor.setPower(-power);
        rfMotor.setPower(-power);
        sleep(time * 1000);
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
    @Override
    public void runOpMode() throws InterruptedException{

        lfMotor = hardwareMap.dcMotor.get("lfMotor");
        lbMotor = hardwareMap.dcMotor.get("lbMotor");
        rfMotor = hardwareMap.dcMotor.get("rfMotor");
        rbMotor = hardwareMap.dcMotor.get("rbMotor");

        colorSensor = hardwareMap.colorSensor.get("colorSensor");

        waitOneFullHardwareCycle();
        waitForStart();

        if (isRed=true) {
            moveForward(0.75, 1000);
            turnLeft(0.75, 1000);
            moveForward(0.75, 1000);
            turnLeft(0.75, 1000);
            moveForward(0.75, 1000);

            if (colorSensor.red() < 100) {
                turnRight(0.75, 500);
                moveForward(0.25, 1000);
                turnLeft(0.75, 500);
            }
            moveForward(0.25, 500);
            sleep(250);
            moveForward(-0.25, 500);
        } else {
            moveForward(0.75, 1000);
            turnRight(0.75, 1000);
            moveForward(0.75, 1000);
            turnRight(0.75, 1000);
            moveForward(0.75, 1000);

            if (colorSensor.blue() < 100) {
                turnLeft(0.75, 500);
                moveForward(0.25, 1000);
                turnRight(0.75, 500);
            }
            moveForward(0.25, 500);
            sleep(250);
            moveForward(-0.25, 500);
        }
    }
}