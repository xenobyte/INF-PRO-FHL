package dataPackages;

import java.util.LinkedList;

public class EEGData {
    public final EmoState emoState;
    public final LinkedList<RawData> rawData;
    
    public EEGData(EmoState e, LinkedList<RawData> r){
        emoState = e;
        rawData = r;
    }
    
    public String toString(){
        return emoState.toString() + "\n" + rawData.toString();
    }
}
