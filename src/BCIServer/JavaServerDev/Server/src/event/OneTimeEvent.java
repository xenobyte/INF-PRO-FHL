package event;

import java.util.LinkedList;

import dataPackages.EEGData;

import event.condition.Condition;
import event.message.Message;

public class OneTimeEvent extends Event{
    private boolean lastResult;
    
    
    public OneTimeEvent(LinkedList<Condition> c, LinkedList<Message> m) {
        super(c,m);
    }

    @Override
    public boolean checkConditon(EEGData e) {
        boolean b = super.checkConditon(e);
        boolean temp = b && !lastResult;
        lastResult = b;
        return temp;
        
    }
}
