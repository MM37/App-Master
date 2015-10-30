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
public class TeleOp extends OpMode {

    /*
    Declares motor variables
    first letter: left/right
    second letter: front/back
    */
    DcMotor lfMotor;
    DcMotor lbMotor;
    DcMotor rfMotor;
    DcMotor rbMotor;
    DcMotor armMotor;

    public TeleOp() {

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
        armMotor = hardwareMap.dcMotor.get("armMotor");
    }

    @Override
    public void loop() {

        /*
        Creates variables for gamepad input
        "Ver"=vertical
        Creates variable for mode
        */
        float leftLeftVer;
        float leftRightVer;
        float rightRightVer;
        String mode;

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
            leftRightVer = gamepad1.right_stick_y;
        } else {
            leftRightVer = 0;
        }
        if (abs(gamepad2.right_stick_y)>0.08) {
            rightRightVer = gamepad2.right_stick_y;
        } else {
            rightRightVer = 0;
        }

        /*
        Scales drivetrain gamepad values depending on buttons pressed
        This allows the driver to have more precision
        Also updates the mode display
        */
        if (gamepad1.left_bumper && gamepad1.right_bumper){
            leftLeftVer *= 0.2;
            leftRightVer *= 0.2;
            mode = "slower";
        } else if (gamepad1.left_bumper) {
            leftLeftVer *= 0.5;
            leftRightVer *= 0.5;
            mode = "slow";
        } else {
            mode = "Regular";
        }

        /*
        Updates motor power
        */
        lfMotor.setPower(leftLeftVer);
        lbMotor.setPower(leftLeftVer);
        rfMotor.setPower(leftRightVer);
        rbMotor.setPower(leftRightVer);
        if (gamepad2.y) {
            armMotor.setPower(0.4);
        } else if (gamepad2.a) {
            armMotor.setPower(0.4);
        } else {
            armMotor.setPower(rightRightVer * (float)0.6); //Scales values to 60% to give operator more control
        }

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
