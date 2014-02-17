package event.condition;

import dataPackages.EEGData;

public interface Condition {
    public boolean checkCondition(EEGData eeg);
}
