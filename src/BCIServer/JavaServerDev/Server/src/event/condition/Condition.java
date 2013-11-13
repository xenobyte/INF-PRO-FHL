package event.condition;

import dataPackages.EEGData;

public abstract class Condition {
    public abstract boolean checkCondition(EEGData eeg);
    
    
    
    public double getVariableValue(EEGData e, String s){
        s = s.toLowerCase();
        switch(s){
        case "emostate.engagement":
            return e.emoState.engagement;
        case "emostate.frustration":
            return e.emoState.frustration;
        case "emostate.meditation":
            return e.emoState.meditation;
        case "emostate.excitement":
            return e.emoState.excitement;
        }
        return 0;
    }

    public boolean compare(double lO, String c, Integer rO){
        switch (c) {
        case "==":
            return lO == rO;
        case "<":
            return lO < rO;
        case ">":
            return lO > rO;
        case "<=":
            return lO <= rO;
        case ">=":
            return lO >= rO;
        case "!=":
            return lO != rO;
        default:
            return false;
        }
    }
    
    public boolean compare(double lO, String c, double rO){
        switch (c) {
        case "==":
            return lO == rO;
        case "<":
            return lO < rO;
        case ">":
            return lO > rO;
        case "<=":
            return lO <= rO;
        case ">=":
            return lO >= rO;
        case "!=":
            return lO != rO;
        default:
            return false;
        }
    }
}
