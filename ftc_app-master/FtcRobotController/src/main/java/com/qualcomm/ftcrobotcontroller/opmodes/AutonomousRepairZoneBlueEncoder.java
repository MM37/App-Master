package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by rkhaj on 10/31/2015.
 */
public class AutonomousRepairZoneBlueEncoder extends LinearOpMode {

    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;
    Servo climber;
    //ColorSensor colorSensor;

    public static final int ARM_DOWN_POSITION = 0;
    public static final int ARM_UP_POSITION = 0;
    public static final double ZIP_UP_POSITION = 0;
    public static final double ZIP_DOWN_POSITION = 0;
    public static final double CLIMBER_UP_POSITION = 0;
    public static final double CLIMBER_DOWN_POSITION = 0;

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
    public void runOpMode() throws InterruptedException{

        lfMotor = hardwareMap.dcMotor.get("lfMotor");
        lbMotor = hardwareMap.dcMotor.get("lbMotor");
        rfMotor = hardwareMap.dcMotor.get("rfMotor");
        rbMotor = hardwareMap.dcMotor.get("rbMotor");

        lfMotor.setDirection(DcMotor.Direction.REVERSE);
        rbMotor.setDirection(DcMotor.Direction.REVERSE);

        //colorSensor = hardwareMap.colorSensor.get("colorSensor");

        climber = hardwareMap.servo.get("climber");

        lfMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        lbMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rfMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rbMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        waitOneFullHardwareCycle();
        waitForStart();

        moveForwardEncoder(0.75, 60);
        turnRight(0.75, 1000);
        moveForwardEncoder(0.75, 50);
        turnRight(0.75, 1000);
        moveForwardEncoder(0.75, 12);
        /*if (colorSensor.blue() < 100) {
            turnLeft(0.75, 500);
            moveForward(0.5, 5);
            turnRight(0.75, 500);
        }
        moveForwardEncoder(0.25, 1);
        sleep(250);
        moveForwardEncoder(-0.25, 1);
        climber.setPosition(CLIMBER_DOWN_POSITION);
        sleep(1000);
        climber.setPosition(CLIMBER_UP_POSITION);*/
    }
}
