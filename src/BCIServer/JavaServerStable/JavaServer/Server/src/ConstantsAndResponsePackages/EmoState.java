package ConstantsAndResponsePackages;

import java.nio.ByteBuffer;

public class EmoState {
    public final int errorCode;
    public final int nSamplesTaken;
    public final double engagement;
    public final double frustration;
    public final double meditation;
    public final double excitement;
    
    
    public EmoState(byte[] message){
        byte[] reverseMessage = new byte[message.length];
        for(int i = 0; i < message.length; i++){
            reverseMessage[i] = message[message.length - i -1];
        }
        ByteBuffer buffer = ByteBuffer.wrap(reverseMessage);
        excitement = buffer.getDouble();
        meditation = buffer.getDouble();
        frustration = buffer.getDouble();
        engagement = buffer.getDouble();
        nSamplesTaken = buffer.getInt();
        errorCode = buffer.getInt();
    }

    
    public Object[] toArray(){
        return new Object[]{nSamplesTaken, (float)engagement, (float)frustration, (float)meditation, (float)excitement};
    }
    
    @Override
    public String toString() {
        return "EmoState [errorCode=" + errorCode + ", nSamplesTaken=" + nSamplesTaken + ", engagement=" + engagement + ", frustration="
                + frustration + ", meditation=" + meditation + ", excitement=" + excitement + "]";
    }
}
