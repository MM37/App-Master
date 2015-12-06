package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;
import static java.lang.Math.abs;

/**
 * Created by rkhaj on 12/5/2015.
 * This is the teleop for the new robot (made beginning of Demeber.
 */
public class TeleOp2 extends OpMode {

    /*
    Declares motor variables and creates link to hardware destinations
    first letter: left/right
    second letter: front/back
    */
    DcMotor lfMotor = hardwareMap.dcMotor.get("lfMotor");
    DcMotor lbMotor = hardwareMap.dcMotor.get("lbMotor");
    DcMotor rfMotor = hardwareMap.dcMotor.get("rfMotor");
    DcMotor rbMotor = hardwareMap.dcMotor.get("rbMotor");

    public TeleOp2() {

    }

    @Override
    public void init() {

    }

    @Override
    public void loop() {

        /*
        Creates variables for gamepad input
        "Ver"=vertical
        Creates variable for speed mode
        Creates variable for arm encoder control
        */
        float leftLeftVer;
        float leftRightVer;

        /*
        Updates the power variable from the sticks only if its absolute value is greater than 0.08
        This prevents values from being passed unless someone is actively moving a stick
        */
        if (abs(gamepad1.left_stick_y)>0.08) {
            leftLeftVer = -gamepad1.left_stick_y;
        } else {
            leftLeftVer = 0;
        }

        if (abs(gamepad1.right_stick_y)>0.08) {
            leftRightVer = -gamepad1.right_stick_y;
        } else {
            leftRightVer = 0;
        }

        /*
        Scales drivetrain gamepad values depending on buttons pressed
        This allows the driver to have more precision
        Also updates the mode display
        */
        if (gamepad1.left_bumper && gamepad1.right_bumper){
            leftLeftVer *= 0.2;
            leftRightVer *= 0.2;
        } else if (gamepad1.left_bumper) {
            leftLeftVer *= 0.5;
            leftRightVer *= 0.5;
        }

        /*
        Updates drive motor power
        */
        lfMotor.setPower(leftLeftVer);
        lbMotor.setPower(leftLeftVer);
        rfMotor.setPower(leftRightVer);
        rbMotor.setPower(leftRightVer);
    }

    @Override
    public void stop() {

    }
}
