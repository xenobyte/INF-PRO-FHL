package bci.Events;

import steuerung.Osc.OSC;

public class BCI_Events implements I_BCI_Events {

    private int poorSignal;
    private int attention;
    private int meditation;
    private int delta;
    private int theta;
    private int lowAlpha;
    private int highAlpha;
    private int lowBeta;
    private int highBeta;
    private int lowGamma;
    private int highGamma;
    private int rawData;
    private int blink;
    private OSC _oscOsc;
    
    public BCI_Events() {
        this._oscOsc = new OSC("localhost", 7400);
    }

    @Override
    public void poorSignal_Event(int level) {
        this.poorSignal = level;
    }

    @Override
    public void attention_Event(int level) {
        this.attention = level;
        _oscOsc.send("/attention", level);
    }

    @Override
    public void meditation_Event(int level) {
        this.meditation = level;
        _oscOsc.send("/meditaion", level);
    }

    @Override
    public void delta_Event(int level) {
        this.delta = level;
    }

    @Override
    public void theta_Event(int level) {
        this.theta = level;
    }

    @Override
    public void lowAlpha_Event(int level) {
        this.lowAlpha = level;
    }

    @Override
    public void highAlpha_Event(int level) {
        this.highAlpha = level;
    }

    @Override
    public void lowBeta_Event(int level) {
        this.lowBeta = level;
    }

    @Override
    public void highBeta_Event(int level) {
        this.highBeta = level;
    }

    @Override
    public void lowGamma_Event(int level) {
        this.lowGamma = level;
    }

    @Override
    public void highGamma_Event(int level) {
        this.highGamma = level;
    }

    @Override
    public void rawData_Event(int data) {
        this.rawData = data;
    }

    @Override
    public void blink_Event(int strength) {
        this.blink = strength;
    }

 

    public int getEventValue(BCI_Signals signal) {

        switch (signal) {
        case POOR_SIGNAL:
            return poorSignal;
        case ATTENTION:
            return attention;
        case MEDITATION:
            return meditation;
        case DELTA:
            return delta;
        case THETA:
            return theta;
        case LOW_ALPHA:
            return lowAlpha;
        case HIGH_ALPHA:
            return highAlpha;
        case LOW_BETA:
            return lowBeta;
        case HIGH_BETA:
            return highBeta;
        case LOW_GAMMA:
            return lowGamma;
        case HIGH_GAMMA:
            return highGamma;
        case RAW_DATA:
            return rawData;
        case BLINK:
            return blink;
        default:
            return -1;
        }
    }
  
}
