package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by rkhaj on 10/9/2015.
 * This is a basic teleop that we are going to use for testing purposes.
 */
public class PreTeleop extends OpMode {

    /*
    Declares motor variables
    first letter: left/right
    second letter: front/back
    */
    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;

    public PreTeleop() {

    }

    @Override
    public void init() {

        /*
        creates link between variables and hardware destinations
        */
        lfMotor = hardwareMap.dcMotor.get("lfMotor");
        lbMotor = hardwareMap.dcMotor.get("lbMotor");
        rfMotor = hardwareMap.dcMotor.get("rfMotor");
        rbMotor = hardwareMap.dcMotor.get("rbMotor");
    }

    @Override
    public void loop() {

        /*
        Creates variables for gamepad input and assigns values to them
        "Ver"=vertical

        Creates variable for mode
        */
        float leftVer = -gamepad1.left_stick_y;
        float rightVer = gamepad1.right_stick_y;

        String mode = "Regular";

        /*
        Scales gamepad values depending on buttons pressed
        This allows the driver to have more precision

        Also updates the mode display
        */
        if (gamepad1.left_trigger == 1){
            leftVer *= 0.75;
            rightVer *= 0.75;
            mode = "slow";
        } else if (gamepad1.left_trigger == 1 && gamepad1.right_trigger == 1) {
            leftVer *= 0.5;
            rightVer *= 0.5;
            mode = "slower";
        }

        /*
        Updates motor power only if input value is greater than 0.08
        This prevents motors from breaking
        */
        if (leftVer>0.08) {
            lfMotor.setPower(leftVer);
            lbMotor.setPower(leftVer);
        }
        if (rightVer>0.08) {
            rfMotor.setPower(rightVer);
            rbMotor.setPower(rightVer);
        }

        /*
        Display:
        the mode we're in (regular, slow, or slower
        left joystick output
        right joystick output
        left trigger output
        right trigger output
         */
        telemetry.addData("Mode", mode);
        telemetry.addData("Left Vertical Ouput", leftVer);
        telemetry.addData("Right Vertical Output", rightVer);
        telemetry.addData("Left Trigger", gamepad1.left_trigger);
        telemetry.addData("Right Trigger", gamepad1.right_trigger);
    }

    @Override
    public void stop() {

    }
}
