package com.example.model;

import com.fazecast.jSerialComm.SerialPort;
import com.google.inject.Inject;

public class PointerDeviceConnector implements IPointerDeviceConnector {
    SerialPort comPort;

    @Inject
    PointerDeviceConnector() {
        System.out.println("Tworzę obiekt klasy PointerDeviceConnector");
        comPort = SerialPort.getCommPort("COM6"); // Podmień na właściwą nazwę portu
        comPort.openPort();
        comPort.setBaudRate(9600);
    }

    @Override
    public void movePointer(int x, int y) {
        System.out.println("Ruch kursora na pozycję: " + x + " " + y);
        byte[] data = ("mov " + x + " " + y + "\n").getBytes();
        comPort.writeBytes(data, data.length);
    }
}
