package event;

import java.util.LinkedList;

import dataPackages.EEGData;
import event.condition.AbstractCondition;
import event.condition.Condition;
import event.message.Message;

public class OneTimeEvent extends AbstractEvent{
    private boolean lastResult;
    
    
    public OneTimeEvent(LinkedList<Condition> c, LinkedList<Message> m) {
        super(c,m);
    }

    @Override
    public boolean checkCondition(EEGData e) {
        boolean b = super.checkCondition(e);
        boolean temp = b && !lastResult;
        lastResult = b;
        return temp;
        
    }
}
