package event;

import java.util.LinkedList;

import osc.OSCMessage;
import dataPackages.EEGData;

public interface Event {
    public boolean checkCondition(EEGData eeg);
    public LinkedList<OSCMessage> getMessages(EEGData eeg);
}
