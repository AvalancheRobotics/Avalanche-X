package org.usfirst.ftc.avalancherobotics.v2.modules;

/**
 * Created by austinzhang on 6/3/16.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;

import org.swerverobotics.library.SynchronousOpMode;
import org.swerverobotics.library.interfaces.TeleOp;

/**
 * DESCRIPTION OF CLASS
 * the purpose of this class is to find the correct values that we need to program autonomous
 * this class runs as a teleop method, you can only drive straight and turn.
 * you can check distance you travel by hitting a button, it sends the distances back to the phone through telemetry.
 */

/**
 * CONTROLS - ONLY USES DRIVER (GAMEPAD1) CONTROLLER
 * Right Trigger - Move Forwards
 * Left Trigger - Move Backwards
 * Right Bumper - Turn Right
 * Left Bumper - Turn Right
 * A - Revert Last Move
 */

//Format Type of movement forward backward left right (f, b, l, r), number of ticks followed by t (2000t)

@TeleOp(name = "BasicDrive")
public class BasicDrive extends SynchronousOpMode {


    DcMotor motorLeftFore;
    DcMotor motorRightFore;
    DcMotor motorLeftAft;
    DcMotor motorRightAft;
    DcMotor motorHarvest;
    DriveTrainController driveTrain;


    //Initialize and Map All Hardware
    private void hardwareMapping() throws InterruptedException {
        motorLeftAft = hardwareMap.dcMotor.get("LeftAft");
        motorLeftFore = hardwareMap.dcMotor.get("LeftFore");
        motorRightAft = hardwareMap.dcMotor.get("RightAft");
        motorRightFore = hardwareMap.dcMotor.get("RightFore");
        motorHarvest = hardwareMap.dcMotor.get("Harvest");
        motorHarvest.setMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);


        driveTrain = new DriveTrainController(motorLeftAft, motorRightAft, motorLeftFore, motorRightFore);


        // Reset encoders
        driveTrain.resetEncoders();

    }

    @Override
    public void main() throws InterruptedException {

        //   try {
        //       outputStreamWriter = new OutputStreamWriter(MyApplication.getContext().openFileOutput("auto.txt", Context.MODE_PRIVATE));
        //   }
        //   catch (IOException e) {
        //       Log.e("Exception", "File write failed: " + e.toString());
        //   }

        hardwareMapping();

        //telemetry.addData( "Finished mapping", new MyApplication().getApplicationContext());
        telemetry.update();

        waitForStart();


        // Go go gadget robot!
        while (opModeIsActive()) {

            if (updateGamepads()) {

                if (ScaleInput.scale(gamepad1.left_trigger + gamepad1.right_trigger) > 0) {
                    if (gamepad1.left_trigger > gamepad1.right_trigger) {
                        driveTrain.manualDrive(-gamepad1.left_trigger, -gamepad1.left_trigger);
                    } else {
                        driveTrain.manualDrive(gamepad1.right_trigger, gamepad1.right_trigger);
                    }
                }


                //turn right
                if (gamepad1.right_bumper) {
                    driveTrain.setLeftDrivePower(.2);
                    driveTrain.setRightDrivePower(-.2);
                }

                //turn left
                if (gamepad1.left_bumper) {
                    driveTrain.setLeftDrivePower(-.2);
                    driveTrain.setRightDrivePower(.2);
                }

                if (!gamepad1.left_bumper && !gamepad1.right_bumper && !(ScaleInput.scale(gamepad1.left_trigger + gamepad1.right_trigger) > 0)) {
                    driveTrain.setLeftDrivePower(0);
                    driveTrain.setRightDrivePower(0);
                }

                if (gamepad1.a) {
                    if (motorHarvest.getPower() != -.6) {
                        motorHarvest.setPower(-.6);
                    }
                    else {
                        motorHarvest.setPower(0);
                    }
                }

                if (gamepad1.y) {
                    if (motorHarvest.getPower() != .7) {
                        motorHarvest.setPower(.7);
                    }
                    else {
                        motorHarvest.setPower(0);
                    }
                }

                idle();

            }
        }
    }
}

