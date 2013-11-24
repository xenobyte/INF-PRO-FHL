package events;

import gui.GUI;
import steuerung.Midi.Cubase_Befehle;
import steuerung.Midi.MidiController;
import steuerung.Midi.MidiInstanz;
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
    private boolean isRec;
    private boolean wah;

    // ______Control_____

    // OSC
    private OSC _oscOsc;
    
    // MIDI
    private MidiInstanz midiInstanz;
    private MidiController controller_Record;
    private MidiController controller_Stop;
    private final int CONTROLLER_VALUE = 80;

  
    public Events(GUI gui) {
        this.gui = gui;
        this._oscOsc = new OSC("localhost", 7400);
        isPlaying = false;
        isRec = false;
        wah = false;
        midiInstanz = new MidiInstanz();
        controller_Record = new MidiController(midiInstanz.getMidiPort(), Cubase_Befehle.RECORD);
        controller_Stop = new MidiController(midiInstanz.getMidiPort(), Cubase_Befehle.STOP);
    }

    @Override
    public void patch_Next_Event() {
        _oscOsc.send("/next", 1);
        gui.enFeedback("Clean");

        if (wah) {
            _oscOsc.send("/wahOff", 0);
            wah = false;
        }
    }

    @Override
    public void patch_Prev_Event() {
        _oscOsc.send("/prev", 1);
        gui.enFeedback("Distortion");
        // disable Wah
        if (wah) {
            _oscOsc.send("/wahOff", 0);
            wah = false;
        }
    }


    @Override
    public void patch_Record_Event() {
        if (!isRec) {
            gui.enFeedback("Rec");
            isRec = true;
            // send Rec Cbase
            controller_Record.send(CONTROLLER_VALUE);
        }
    }


    @Override
    public void patch_Play_back_Track_Event() {
        if (!isPlaying) {
            _oscOsc.send("/play", 22);
            gui.enFeedback("Play");
            isPlaying = true;
        }

    }

    @Override
    public void patch_Stop_back_Track_Event() {
        if (isPlaying) {
            _oscOsc.send("/stop", 22);
            gui.enFeedback("Stop");
            isPlaying = false;
        }

        if (isRec) {

            gui.enFeedback("Stop");
            
            //send Stop to cubase
            controller_Stop.send(CONTROLLER_VALUE);
            
            isRec = false;
        }
    }

    @Override
    public void patch_WAH_enable_Event() {
        if (!wah) {
            _oscOsc.send("/wahOn", 1);

            // enable Distortion
            patch_Prev_Event();

            gui.enFeedback("Wah On");

            wah = true;
        }

    }

    @Override
    public void patch_WAH_disable_Event() {
        if (wah) {
            _oscOsc.send("/wahOff", 0);
            gui.enFeedback("Wah Off");
            wah = false;
        }

    }

}
