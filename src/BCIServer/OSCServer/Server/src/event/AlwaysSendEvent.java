package event;

import java.util.LinkedList;

import event.condition.Condition;
import event.message.Message;

public class AlwaysSendEvent extends AbstractEvent{

    public AlwaysSendEvent(LinkedList<Condition> c, LinkedList<Message> m) {
        super(c, m);
    }

}
