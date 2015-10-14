package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import static java.lang.Math.abs;

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
        Creates variables for gamepad input
        "Ver"=vertical
        Creates variable for mode
        */
        float leftVer;
        float rightVer;
        String mode;

        /*
        Updates the power variable only if its absolute value is greater than 0.08
        This prevents the motors from burning out
        */
        if (abs(gamepad1.left_stick_y)>0.08) {
            leftVer = -gamepad1.left_stick_y;
        } else {
            leftVer = 0;
        }
        if (abs(gamepad1.right_stick_y)>0.08) {
            rightVer = gamepad1.right_stick_y;
        } else {
            rightVer = 0;
        }

        /*
        Scales gamepad values depending on buttons pressed
        This allows the driver to have more precision
        Also updates the mode display
        */
        if (gamepad1.left_bumper && gamepad1.right_bumper){
            leftVer *= 0.2;
            rightVer *= 0.2;
            mode = "slower";
        } else if (gamepad1.left_bumper) {
            leftVer *= 0.5;
            rightVer *= 0.5;
            mode = "slow";
        } else {
            mode = "Regular";
        }

        /*
        Updates motor power
        */
        lfMotor.setPower(leftVer);
        lbMotor.setPower(leftVer);
        rfMotor.setPower(rightVer);
        rbMotor.setPower(rightVer);

        /*
        Display:
        the mode we're in (regular, slow, or slower)
        left joystick output
        right joystick output
        left trigger output
        right trigger output
         */
        telemetry.addData("Mode", mode);
        telemetry.addData("Left Vertical Ouput", lfMotor.getPower());
        telemetry.addData("Right Vertical Output", rfMotor.getPower());
        telemetry.addData("Left Bumper", gamepad1.left_bumper);
        telemetry.addData("Right Bumper", gamepad1.right_bumper);
    }

    @Override
    public void stop() {

    }
}
