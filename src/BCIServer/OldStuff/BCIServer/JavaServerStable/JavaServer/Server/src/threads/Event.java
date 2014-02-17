package threads;

import java.util.Iterator;
import java.util.LinkedList;

import osc.OSCMessage;

import ConstantsAndResponsePackages.EmoState;

public class Event {
    private byte[] packagesToSend;
    private LinkedList<Condition> conditions = new LinkedList<Condition>();
    private LinkedList<String> source = new LinkedList<String>();
    private LinkedList<String> path = new LinkedList<String>();

    public Event(String conds, String oscMessages) {
        this(oscMessages);

        String[] temp = conds.split(",");
        for (int i = 0; i < temp.length; i++) {
            conditions.add(new Condition(temp[i]));
        }


    }

    private String condtoString(){
        if(conditions.isEmpty())
            return "True";
        Iterator<Condition> itCons = conditions.iterator();
        String s = itCons.next().getString();
        while (itCons.hasNext()){
            s += " && ";
            s += itCons.next().getString();
        }
        return s;
    }
    
    public String getString(){
        String s = "";
        s = String.format("Event {\n    Condition = %s,\n    Sendpackages:\n", condtoString());
        Iterator<String> itSource = source.iterator();
        Iterator<String> itPath = path.iterator();
        while(itSource.hasNext()){
            s =  s + "    " + itPath.next() + ":" + itSource.next();
        }
        s += "}\n";
        return s;
    }
    
    
    public OSCMessage[] getMessages(EmoState s) {
        OSCMessage[] messages = new OSCMessage[source.size()];
        Iterator<String> itSource = source.iterator();
        Iterator<String> itPath = path.iterator();
        for (int i = 0; i < messages.length; i++) {
            String temp = itSource.next();
            switch (temp) {
            case ("EmoState"):
                messages[i] = new OSCMessage(itPath.next(), s.toArray());
                break;
                default:
                    messages[i] = new OSCMessage(itPath.next(), new Object[]{temp});
            }
            // messages[i] = new OSCMessage();
        }

        return messages;
    }

    public Event(String oscMessages) {
        String[] temp = oscMessages.split(",");
        for (int i = 0; i < temp.length; i++) {
            String[] temp2 = temp[i].split(":");
            path.add(temp2[0]);
            source.add(temp2[1]);
        }
    }

    public byte[] getPackageToSend() {
        return packagesToSend;
    }

    public boolean checkConditon(EmoState s) {
        if(conditions.isEmpty())
            return true;
        Iterator<Condition> itConds = conditions.iterator();
        Condition c;
        while (itConds.hasNext()) {
            c = itConds.next();
            switch (c.variable) {
            case "emostate.meditation":
                if (!c.checkCondition(s.meditation))
                    return false;
                break;
            case "emostate.frustration":
                if (!c.checkCondition(s.frustration))
                    return false;
                break;
            case "emostate.engagement":
                if (!c.checkCondition(s.engagement))
                    return false;
                break;
            case "emostate.excitement":
                if (!c.checkCondition(s.excitement))
                    return false;
                break;
            default:
                System.err.printf("Variable %s wurde nicht gefunden\n", c.variable);
                return false;
            }
        }
        return true;
    }

    public class Condition {
        private final String variable;
        private final String condition;
        private final String rightOperator;

        Condition(String s) {
            String[] temp = s.split(":");
            variable = temp[0].toLowerCase();
            condition = temp[1].toLowerCase();
            rightOperator = temp[2].toLowerCase();
        }

        private boolean checkCondition(int i) {
            Integer rOp = Integer.parseInt(rightOperator);
            switch (condition) {
            case "==":
                return i == rOp;
            case "<":
                return i < rOp;
            case ">":
                return i > rOp;
            case "<=":
                return i <= rOp;
            case ">=":
                return i >= rOp;
            default:
                return i != rOp;
            }
        }

        private String getString(){
            return variable + " " + condition + " " + rightOperator;
        }
        
        private boolean checkCondition(double d) {
            Double rOp = Double.parseDouble(rightOperator);
            switch (condition) {
            case "==":
                return d == rOp;
            case "<":
                return d < rOp;
            case ">":
                return d > rOp;
            case "<=":
                return d <= rOp;
            case ">=":
                return d >= rOp;
            default:
                return d != rOp;
            }
        }
    }
}
