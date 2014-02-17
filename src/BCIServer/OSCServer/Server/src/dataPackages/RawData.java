package dataPackages;

import java.nio.ByteBuffer;

import osc.OSCMessage;

public class RawData {
    public final int errorCode;
    public final double counter;
    public final double gyroX;
    public final double gyroY;
    public final double timeStamp;
    public final double func_ID;
    public final double func_Value;
    public final double marker;
    public final double sync_Signal;
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

    public RawData(OSCMessage m) {
        errorCode = 0;
        message = null;
        Object[] o = m.getArguments();
        counter = (float) o[0];
        gyroX = (float) o[1];
        gyroY = (float) o[2];
        timeStamp = (float) o[3];
        func_ID = (float) o[4];
        func_Value = (float) o[5];
        marker = (float) o[6];
        sync_Signal = (float) o[7];
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
        ByteBuffer buffer = ByteBuffer.wrap(message);
        int errorCode = buffer.getInt();
        if (errorCode == 0x12345678) {
            this.errorCode = errorCode;
            buffer.getInt();
            counter = buffer.getDouble();
            gyroX = buffer.getDouble();
            gyroY = buffer.getDouble();
            timeStamp = buffer.getDouble();
            func_ID = buffer.getDouble();
            func_Value = buffer.getDouble();
            marker = buffer.getDouble();
            sync_Signal = buffer.getDouble();
            AF3 = buffer.getDouble();
            F7 = buffer.getDouble();
            F3 = buffer.getDouble();
            FC5 = buffer.getDouble();
            T7 = buffer.getDouble();
            P7 = buffer.getDouble();
            O1 = buffer.getDouble();
            O2 = buffer.getDouble();
            P8 = buffer.getDouble();
            T8 = buffer.getDouble();
            FC6 = buffer.getDouble();
            F4 = buffer.getDouble();
            F8 = buffer.getDouble();
            AF4 = buffer.getDouble();
        } else {
            byte[] reverseMessage = new byte[message.length];
            for (int i = 0; i < message.length; i++) {
                reverseMessage[i] = message[message.length - i - 1];
                // System.out.println(reverseMessage[i]);
            }
            buffer = ByteBuffer.wrap(reverseMessage);
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
            sync_Signal = buffer.getDouble();
            marker = buffer.getDouble();
            func_Value = buffer.getDouble();
            func_ID = buffer.getDouble();
            timeStamp = buffer.getDouble();
            gyroY = buffer.getDouble();
            gyroX = buffer.getDouble();
            counter = buffer.getDouble();
            buffer.getInt();
            this.errorCode = buffer.getInt();
        }
    }

    @Override
    public String toString() {
        return "RawData [errorCode=" + errorCode + ", counter=" + counter + ", gyroX=" + gyroX + ", gyroY=" + gyroY + ", timeStamp="
                + timeStamp + ", func_ID=" + func_ID + ", func_Value=" + func_Value + ", marker=" + marker + ", sync_Signal=" + sync_Signal
                + ", AF3=" + AF3 + ", F7=" + F7 + ", F3=" + F3 + ", FC5=" + FC5 + ", T7=" + T7 + ", P7=" + P7 + ", O1=" + O1 + ", O2=" + O2
                + ", P8=" + P8 + ", T8=" + T8 + ", FC6=" + FC6 + ", F4=" + F4 + ", F8=" + F8 + ", AF4=" + AF4 + "]";
    }

    public Object[] toArray() {
        return new Object[] { (float) counter, (float) gyroX, (float) gyroY, (float) timeStamp, (float) func_ID, (float) func_Value,
                (float) marker, (float) sync_Signal, (float) AF3, (float) F7, (float) F3, (float) FC5, (float) T7, (float) P7, (float) O1,
                (float) O2, (float) P8, (float) T8, (float) FC6, (float) F4, (float) F8, (float) AF4 };
    }

}
