package event.condition;

import dataPackages.EEGData;

public class StringCondition extends Condition {
    private final String leftOperator;
    private final String condition;
    private final String rightOperator;

    public StringCondition(String lO, String c, String rO) {
        leftOperator = lO;
        condition = c;
        rightOperator = rO;
    }

    @Override
    public boolean checkCondition(EEGData e) {
       return super.compare(super.getVariableValue(e, leftOperator), condition, super.getVariableValue(e, rightOperator));
    }
}
