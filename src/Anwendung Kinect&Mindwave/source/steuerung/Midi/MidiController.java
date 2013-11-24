package steuerung.Midi;



/**
 * Klassenname: MidiController.java
 * 
 * Zweck: Initialisierung eines Midi-Controllers, sowie senden eines Midi Befehls
 *   
 * Ablauf: bei der Instanziierung dieser Klasse werden der Midi Port und Controller initialisiert
 *         so dass beim Aufruf der Methode send() einen Midi-Befehl gesendet wird 
 *
 * @author M.Kemel Koumenji
 *
 * @version 1.0 14/11/13
 */

import promidi.Controller;
import promidi.MidiOut;

public class MidiController {
    
    //Midi Port
    private MidiOut midi_Out;
    
    //Controller
    private Controller controller;
    
    private int controller_ID;
    
    /**
     * Der Konstruktor initialisiert den Midi Port bzw. Controller
     * @param midiPort       Midi-Port, für das senden von Midi Nachrichten
     * @param controllerID   Controller ID, für die Identifikation eines Controllers
     * @param controllerWert Der Wert des Controllers  
     */
    public MidiController(MidiOut midiPort, Cubase_Befehle record) {
        this.midi_Out = midiPort;
        controller_ID = record.ordinal();
    }
    
    /**
     * Diese Methode sendet einen MIDI Controller-Befehl
     */
    public void send(int controllerWert) {
        controller = new Controller(controller_ID, controllerWert);
        midi_Out.sendController(controller);
    }
    
}
