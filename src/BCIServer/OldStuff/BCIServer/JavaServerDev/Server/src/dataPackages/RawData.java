package dataPackages;

import java.nio.ByteBuffer;

import osc.OSCMessage;

public class RawData {
    public final int errorCode;
    public final int counter;
    public final int gyroX;
    public final int gyroY;
    public final int timeStamp;
    public final int func_ID;
    public final int func_Value;
    public final int marker;
    public final int sync_Signal;
    public final double AF3;
    public final double F7;
    public final double F3;
    public final double FC5;
    public final double T7;
    public final double P7;
    public final double O1;
    public final double O2;
    public final double P8;
    public final double T8;
    public final double FC6;
    public final double F4;
    public final double F8;
    public final double AF4;
    private final byte[] message;

    public byte[] getMessage() {
        return message.clone();
    }

    public RawData(OSCMessage m){
        errorCode = 0;
        message = null;
        Object[] o = m.getArguments();
        counter = (int) o[0];
        gyroX = (int) o[1];
        gyroY = (int) o[2];
        timeStamp = (int) o [3];
        func_ID = (int) o[4];
        func_Value = (int) o[5];
        marker = (int) o[6];
        sync_Signal = (int) o[7];
        AF3 = (float) o[8];
        F7 = (float) o[9];
        F3 = (float) o[10];
        FC5 = (float) o[11];
        T7 = (float) o[12];
        P7 = (float) o[13];
        O1 = (float) o[14];
        O2 = (float) o[15];
        P8 = (float) o[16];
        T8 = (float) o[17];
        FC6 = (float) o[18];
        F4 = (float) o[19];
        F8 = (float) o[20];
        AF4 = (float) o[21];
    }

    public RawData(byte[] message) {
        this.message = message;
        byte[] reverseMessage = new byte[message.length];
        for (int i = 0; i < message.length; i++) {
            reverseMessage[i] = message[message.length - i - 1];
        }
        ByteBuffer buffer = ByteBuffer.wrap(reverseMessage);
        AF4 = buffer.getDouble();
        F8 = buffer.getDouble();
        F4 = buffer.getDouble();
        FC6 = buffer.getDouble();
        T8 = buffer.getDouble();
        P8 = buffer.getDouble();
        O2 = buffer.getDouble();
        O1 = buffer.getDouble();
        P7 = buffer.getDouble();
        T7 = buffer.getDouble();
        FC5 = buffer.getDouble();
        F3 = buffer.getDouble();
        F7 = buffer.getDouble();
        AF3 = buffer.getDouble();
        buffer.getInt();
        sync_Signal = buffer.getInt();
        marker = buffer.getInt();
        func_Value = buffer.getInt();
        func_ID = buffer.getInt();
        timeStamp = buffer.getInt();
        gyroY = buffer.getInt();
        gyroX = buffer.getInt();
        counter = buffer.getInt();
        errorCode = buffer.getInt();
    }

    @Override
    public String toString() {
        return "RawData [errorCode=" + errorCode + ", counter=" + counter + ", gyroX=" + gyroX + ", gyroY=" + gyroY + ", timeStamp="
                + timeStamp + ", func_ID=" + func_ID + ", func_Value=" + func_Value + ", marker=" + marker + ", sync_Signal=" + sync_Signal
                + ", AF3=" + AF3 + ", F7=" + F7 + ", F3=" + F3 + ", FC5=" + FC5 + ", T7=" + T7 + ", P7=" + P7 + ", O1=" + O1 + ", O2=" + O2
                + ", P8=" + P8 + ", T8=" + T8 + ", FC6=" + FC6 + ", F4=" + F4 + ", F8=" + F8 + ", AF4=" + AF4 + "]";
    }

    public Object[] toArray() {
        return new Object[] { counter, gyroX, gyroY, timeStamp, func_ID, func_Value, marker, sync_Signal, (float) AF3, (float) F7,
                (float) F3, (float) FC5, (float) T7, (float) P7, (float) O1, (float) O2, (float) P8, (float) T8, (float) FC6, (float) F4,
                (float) F8, (float) AF4 };
    }

}
