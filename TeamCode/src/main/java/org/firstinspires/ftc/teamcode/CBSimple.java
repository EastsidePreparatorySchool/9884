/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Cheerbot: Teleop Simple", group = "Cheerbot")

public class CBSimple extends LinearOpMode {

    /* Declare OpMode members. */
    HardwareCheerbot robot = new HardwareCheerbot();

    @Override
    public void runOpMode() {
        double left;
        double right;
        double up;
        double down;
        double position;

        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Hello Sophia");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            left = gamepad1.left_stick_y;
            right = gamepad1.right_stick_y;

            robot.leftFrontMotor.setPower(left);
            robot.rightFrontMotor.setPower(right);
            robot.leftBackMotor.setPower(left);
            robot.rightBackMotor.setPower(right);


//            //TODO: Servos
//            // Use gamepad Y & A raise and lower the arm
//            if (gamepad1.a) {
//                whackerPosition = 0;
//            }
//            else if (gamepad1.y) {
//                whackerPosition = 0.4;
//            }
//            if(gamepad1.x){
//                leftArmPosition -= 0.1;
//                rightArmPosition +=0.1;
//            } else if (gamepad1.b){
//                leftArmPosition += 0.1;
//                rightArmPosition -=0.1;
//            }
//            if(gamepad1.left_bumper){
//                position = robot.lifter.getCurrentPosition();
//            }
//
////
////            // Use gamepad X & B to open and close the claw
////            if (gamepad91.x)
////                clawPosition += CLAW_SPEED;
////            else if (gamepad1.b)
////                clawPosition -= CLAW_SPEED;
////
////            // Move both servos to new position.
//            // at 0 right is all the way in and left is all the way out
//            whackerPosition  = Range.clip(whackerPosition, 0.0, 0.6);
//            rightArmPosition = Range.clip(rightArmPosition, 0.3, 0.8);
//            leftArmPosition = Range.clip(leftArmPosition, 0.3,0.8);
//            robot.whacker.setPosition(whackerPosition);
//            robot.leftArm.setPosition(leftArmPosition);
//            robot.rightArm.setPosition(rightArmPosition);
////            robot.arm.setPosition(armPosition);
////            clawPosition = Range.clip(clawPosition, robot.CLAW_MIN_RANGE, robot.CLAW_MAX_RANGE);
////            robot.claw.setPosition(clawPosition);
//


            // Send telemetry message to signify robot running;
//            telemetry.addData("position","%.2f", position);
//            telemetry.addData("left",  "%.2f", left);
//            telemetry.addData("right", "%.2f", right);
//            telemetry.update();

            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);

        }
    }
}
