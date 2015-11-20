package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

/**
 * Created by rkhaj on 11/14/2015.
 */
public class EncoderTestRohan extends LinearOpMode {

    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;

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

        lbMotor.setDirection(DcMotor.Direction.REVERSE);
        rfMotor.setDirection(DcMotor.Direction.REVERSE);

        lfMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        lbMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rfMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rbMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        telemetry.addData("starting position: ", rfMotor.getCurrentPosition());

        waitForStart();
        waitOneFullHardwareCycle();

        //moveForwardEncoder(0.75, 10);

        lfMotor.setTargetPosition(20);
        lbMotor.setTargetPosition(20);
        rfMotor.setTargetPosition(20);
        rbMotor.setTargetPosition(20);
        lfMotor.setPower(0.75);
        lbMotor.setPower(0.75);
        rfMotor.setPower(0.75);
        rbMotor.setPower(0.75);
        while (rfMotor.getCurrentPosition() < rfMotor.getTargetPosition()) {
            telemetry.addData("current position: ", rfMotor.getCurrentPosition());
            telemetry.addData("target position: ", rfMotor.getTargetPosition());
        }
        lfMotor.setPower(0);
        lbMotor.setPower(0);
        rfMotor.setPower(0);
        rbMotor.setPower(0);

        telemetry.addData("ending position: ", rfMotor.getCurrentPosition());
    }
}
