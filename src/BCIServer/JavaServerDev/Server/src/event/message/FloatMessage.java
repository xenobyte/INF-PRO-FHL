package event.message;

import java.util.LinkedList;

import dataPackages.EEGData;
import osc.OSCMessage;

public class FloatMessage implements Message{
    private final String address;
    private final Float data;
    
    public FloatMessage(String add, Float d){
        address = add;
        data = d;
    }
    
    @Override
    public LinkedList<OSCMessage> toMessage(EEGData e) {
        LinkedList<OSCMessage> l = new LinkedList<OSCMessage>();
        l.add(new OSCMessage(address, new Object[]{data}));
        return l;
    }

    @Override
    public LinkedList<Object> toOSCData() {
        LinkedList<Object> l = new LinkedList<Object>();
        l.add(address);
        l.add(data);
        return l;
    }

}
