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

package org.eastsideprep.ftc.murderbot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Murderbot: Teleop Simple", group = "Murderbot")

public class MBSimple extends LinearOpMode {

    /* Declare OpMode members. */
    MBHardware robot = new MBHardware();

    @Override
    public void runOpMode() {
        double drive = 0;
        double strafe = 0;
        double rotate = 0;
        double total = 0;


        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Say", "Now over wireless");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            drive = -1.0 * gamepad1.right_stick_y;
            strafe = gamepad1.right_stick_x;
            rotate = gamepad1.left_stick_x;
            total = Math.max(Math.abs(drive) + Math.abs(strafe) + Math.abs(rotate), 1.0);

            robot.leftFrontMotor.setPower((drive + strafe + rotate) / total);
            robot.leftBackMotor.setPower((drive - strafe + rotate) / total);
            robot.rightFrontMotor.setPower((drive - strafe - rotate) / total);
            robot.rightBackMotor.setPower((drive + strafe - rotate) / total);

            if (gamepad1.right_trigger > 0.1) {
                if (gamepad1.right_trigger > 0.8) {
                    // burst
                    robot.d0.setState(true);
                    sleep(300);
                    robot.d0.setState(false);
                } else {
                    // single shot
                    robot.d0.setState(true);
                    sleep(100);
                    robot.d0.setState(false);
                }
            }

            // Send telemetry message to signify robot running;
            telemetry.addLine()
                    .addData("drive", "%.2f", drive)
                    .addData("strafe", "%.2f", strafe)
                    .addData("rotate", "%.2f", rotate);
            telemetry.addLine()
                    .addData("heading", "%d", robot.gyro.getHeading());
            telemetry.addLine()
                    .addData("hit", "%.2f", robot.a0.getVoltage());

            telemetry.update();

            // Pause for 40 mS each cycle = update 25 times a second.
            sleep(40);
        }
    }
}
