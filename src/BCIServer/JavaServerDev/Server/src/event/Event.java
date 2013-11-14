package event;

import java.util.Iterator;
import java.util.LinkedList;
import dataPackages.EEGData;

import event.condition.Condition;
import event.message.Message;




import osc.OSCMessage;


public abstract class Event {
    private LinkedList<Condition> conditions;
    private LinkedList<Message> message;
    
    public Object[] toOSCData(){
        LinkedList<Object> l = new LinkedList<Object>();
        Iterator<Condition> c = conditions.iterator();
        while(c.hasNext()){
            l.addAll(c.next().toOSCData());
        }
        Iterator<Message> m = message.iterator();
        while(m.hasNext()){
            l.addAll(m.next().toOSCData());
        }
        return l.toArray();
    }   

    public Event(LinkedList<Condition> c, LinkedList<Message> m) {
        conditions = c;
        message = m;
    }
        

    public boolean checkConditon(EEGData eeg) {
        if(conditions.isEmpty())
            return true;
        Iterator<Condition> itConds = conditions.iterator();
        while(itConds.hasNext()){
            if(!itConds.next().checkCondition(eeg)){
                return false;
            }
        }
        return true;
    }
        
    public LinkedList<OSCMessage> getMessages(EEGData eeg){
        LinkedList<OSCMessage> l = new LinkedList<OSCMessage>();
        Iterator<Message> li = message.iterator();
        while(li.hasNext()){
            l.addAll(li.next().toMessage(eeg));
        }
        return l;
    }

}
