package event.message;

import java.util.LinkedList;

import dataPackages.EEGData;
import osc.OSCMessage;

public interface Message {
    public LinkedList<OSCMessage> toMessage(EEGData e);
}
