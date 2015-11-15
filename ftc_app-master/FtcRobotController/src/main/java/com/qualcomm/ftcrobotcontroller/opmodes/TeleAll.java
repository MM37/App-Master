package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Math.abs;


/**
 * Created by Sahaj on 11/1/2015.
 */
public class TeleAll extends OpMode {

    /*creates DcMotor objects*/
    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;
    DcMotor armMotor;

    /*creates Servo objects*/
    Servo  climber;

    /*sets values for arm position*/
    public static final int ARM_DOWN_POSITION = 50;
    public static final int ARM_UP_POSITION = 100;

    /*sets climber servo positions*/
    public static final double CLIMBER_UP_POSITION = 0.7;
    public static final double CLIMBER_DOWN_POSITION = 0.0;


    public TeleAll() {
    }

    @Override
    public void init(){
        /* Assigns DcMotor objects to physical motors
            using Hardware Mapping
         */
        lfMotor = hardwareMap.dcMotor.get("lfMotor");
        lbMotor = hardwareMap.dcMotor.get("lbMotor");
        rfMotor = hardwareMap.dcMotor.get("rfMotor");
        rbMotor = hardwareMap.dcMotor.get("rbMotor");
        armMotor = hardwareMap.dcMotor.get("armMotor");

        /* Assigns Servo objects to physical servos using
               Hardware Mapping
        */
        climber = hardwareMap.servo.get("climber");

        rbMotor.setDirection(DcMotor.Direction.REVERSE);
        lfMotor.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop(){
        /* variables to control motor power */
        float rPwr, lPwr, armPwr;

        Boolean lBump; //status of left bumper on gamepad 1
        Boolean rBump; //status of right bumper on gamepad 1

        /*sets threshold for gamepad input*/
        if (abs(gamepad1.left_stick_y)>0.08) {
            lPwr = gamepad1.left_stick_y;
        } else {
            lPwr = 0;
        }

        if (abs(gamepad1.right_stick_y)>0.08) {
            rPwr = gamepad1.right_stick_y;
        } else {
            rPwr = 0;
        }

        if (abs(gamepad2.right_stick_y)>0.08) {
            armPwr = (float)0.2*gamepad2.right_stick_y;
        } else {
            armPwr = 0;
        }

        if (gamepad1.left_bumper)
            lBump=true;
        else
            lBump=false;

        if (gamepad1.right_bumper)
            rBump=true;
        else
            rBump=false;

        /*assigns power based on lBump value */
        if(lBump && rBump){
            lbMotor.setPower(0.30*lPwr);
            lfMotor.setPower(0.30*lPwr);
            rfMotor.setPower(0.30 * rPwr);
            rbMotor.setPower(0.30 * rPwr);
        }
        else if(lBump){
            lbMotor.setPower(0.60*lPwr);
            lfMotor.setPower(0.60*lPwr);
            rfMotor.setPower(0.60*rPwr);
            rbMotor.setPower(0.60*rPwr);
        }
        else{
            lbMotor.setPower(lPwr);
            lfMotor.setPower(lPwr);
            rfMotor.setPower(rPwr);
            rbMotor.setPower(rPwr);
        }
        armMotor.setPower(armPwr);

        if(gamepad1.x)
            climber.setPosition(CLIMBER_UP_POSITION);
        else
            climber.setPosition(CLIMBER_DOWN_POSITION);


        telemetry.addData("rightDrive: ", rfMotor.getPower());
        telemetry.addData("leftDrive: ", lfMotor.getPower());
        telemetry.addData("arm: ", armMotor.getPower());
        telemetry.addData("climber: ", climber.getPosition());


    }

    @Override
    public void stop(){
    }
}


