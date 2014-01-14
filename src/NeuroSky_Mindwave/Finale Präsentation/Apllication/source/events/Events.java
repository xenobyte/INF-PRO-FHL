package events;

import gui.GUI;
import steuerung.Osc.OSC;

/**
 * Klassenname : Events.java
 * 
 * Zweck: implementiert die Interface CubaseBefehle
 * 
 * Ablauf: die Methoden werden von Externe Instanzen aufgerufen bei jedem Aufruf wird die Entsprechende Aktion durchgeführt
 * 
 * @author M.Kemel Koumenji
 * @version 1.0 19/11/13
 * 
 */
public class Events implements IEvents {

    private GUI gui;

    private boolean isPlaying;
  
    private boolean wah;
    private boolean delay;
    private boolean flanger;

    // ______Control_____

    // OSC
    private OSC _oscOsc;
    
    public Events(GUI gui) {
        this.gui = gui;
        this._oscOsc = new OSC("localhost", 7400);
        isPlaying = false;
        wah = false;
        delay = false;
        flanger = false;
    }

    @Override
    public void play_Track_Event() {
        if (!isPlaying) {
            _oscOsc.send("/play", 22);
            gui.enFeedback("Play");
            isPlaying = true;
        } 
    }


    @Override
    public void stop_Track_Event() {
        if (isPlaying) {
            _oscOsc.send("/stop", 22);
            gui.enFeedback("Stop");
            isPlaying = false;
        }
    }

    @Override
    public void enable_Flanger_Event() {
        if (!flanger) {
            _oscOsc.send("/flanger", 1);
            gui.enFeedback("Flanger");
            flanger = true;
            disable_Wah();   
            disable_Delay();
        }
    }


    @Override
    public void enable_Wah_Event() {
        if (!wah) {
            _oscOsc.send("/wah", 1);
            gui.enFeedback("Wah");
            wah = true;
            disable_Falnger();
            disable_Delay();
        }
        
    }


    @Override
    public void enable_Delay_Event() {
        if (!delay) {
            _oscOsc.send("/delay", 1);
            gui.enFeedback("Delay");
            delay = true;
            disable_Falnger();
            disable_Wah();   
        }
        
    }


    @Override
    public void disable_Effects_Event() {
        disable_Wah();
        disable_Falnger();
        disable_Delay();
        gui.enFeedback("Disable");
    }

    
    private void disable_Wah(){
        if (wah) {
            _oscOsc.send("/wah", 0);
            wah = false;
        }
    }
    
    private void disable_Falnger(){
        if (flanger) {
            _oscOsc.send("/flanger", 0);
            flanger = false;
        }
    }
    
    private void disable_Delay(){
        if (delay) {
            _oscOsc.send("/delay", 0);
            delay = false;
        } 
    }
}
