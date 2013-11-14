package event.condition;

import java.util.LinkedList;

import dataPackages.EEGData;

public class FloatCondition extends Condition{
    private final String leftOperator;
    private final String condition;
    private final Float rightOperator;
    
    
    public FloatCondition(String lO, String c, Float rO) {
        leftOperator = lO;
        condition = c;
        rightOperator = rO;
    }

    @Override
    public boolean checkCondition(EEGData e) {
        return super.compare(super.getVariableValue(e, leftOperator), condition, rightOperator);
    }

    @Override
    public LinkedList<Object> toOSCData() {
        LinkedList<Object> l = new LinkedList<Object>();
        l.add(leftOperator);
        l.add(condition);
        l.add(rightOperator);
        return l;
    }

}
