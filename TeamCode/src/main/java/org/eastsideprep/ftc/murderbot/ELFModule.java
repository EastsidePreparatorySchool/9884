package org.eastsideprep.ftc.murderbot;

import com.qualcomm.robotcore.hardware.I2cAddr;
import com.qualcomm.robotcore.hardware.I2cDeviceSynch;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchDevice;
import com.qualcomm.robotcore.hardware.configuration.I2cSensor;
import com.qualcomm.robotcore.util.TypeConversion;

import java.nio.ByteOrder;

import static android.os.SystemClock.sleep;


/**
 * Created by gmein on 2/13/2018.
 */
@I2cSensor(name = "EPS Laser Tag Module", description = "Laser cannon turret and hit detector", xmlTag = "ELF01")
public class ELFModule extends I2cDeviceSynchDevice<I2cDeviceSynch> {
    final int ELF_I2C_ADDRESS = 0x47;

    private enum Command {
        REPORT_HITS(1),
        FIRE_LASER(2),
        SET_LASER_HEADING(3),
        CALIBRATE_LEVEL(4),
        GET_RESULT(5);

        public int code;

        Command(int code) {
            this.code = code;
        }
    }


    @Override
    public Manufacturer getManufacturer() {

        return Manufacturer.Other;
    }

    @Override
    protected synchronized boolean doInitialize() {
        return true;
    }

    @Override
    public String getDeviceName() {

        return "EPS Laser Tag Module";
    }

    public ELFModule(I2cDeviceSynch deviceClient) {
        super(deviceClient, true);

        this.deviceClient.setI2cAddress(I2cAddr.create7bit(ELF_I2C_ADDRESS));

        super.registerArmingStateCallback(false);
        this.deviceClient.engage();
    }

    public void fire(int ms) {
        ArduinoCommand(Command.FIRE_LASER, ms);
    }


    private int ArduinoCommand(Command command, int parameter) {
        byte[] bReadBuffer;

        this.deviceClient.write(command.code, TypeConversion.intToByteArray(parameter));
        sleep(20);
        bReadBuffer = this.deviceClient.read(Command.GET_RESULT.code, 4);

        return TypeConversion.byteArrayToInt(bReadBuffer, ByteOrder.LITTLE_ENDIAN);
    }
}
