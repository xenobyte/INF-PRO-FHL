package steuerung.Midi;



/**
 * Klassenname:MidiPort.java
 * 
 * Zweck: Diese Klasse liefert einen MidiPort zur�ck.
 * 
 * Ablauf: die Klasse durchsucht alle verf�gbaren Midi-Ports,und gibt sie auf der Konsole aus. 
 *         anschlie�end soll der Benutzer einer de Ports w�hlen und dazu dem entsprechenden Kanal.
 *         
 * @author M.kemel Koumenji
 * 
 * @version 1.0 14/11/13
 *
 */

import promidi.MidiIO;
import promidi.MidiOut;

public class MidiInstanz {

    //Midi Instanz
    private MidiIO midi_IO;
    
    //Midi Port
    private MidiOut midi_Out;


    public MidiInstanz() {
        
        //Init MidiPort
        initMidiPort();
    }

    /**
     * Diese Methode liefert ein Midi Port zur�ck
     * 
     * @return   Midi-Port.
     */
    public MidiOut getMidiPort() {
        return midi_Out;
    }

    /**
     * Diese Methode Initialisiert den Midi Output Port in dem sie den Port loopMIDI w�hlt. 
     */
    private void initMidiPort() {
        midi_Out = null;
        while (midi_Out == null) {  
            // Midi Port �ffnen
            try {
                midi_IO = MidiIO.getInstance();
                if("loopMIDI".equalsIgnoreCase(midi_IO.getOutputDeviceName(midi_IO.numberOfOutputDevices()-1))){
                    midi_Out = midi_IO.getMidiOut(0, midi_IO.numberOfOutputDevices()-1);   
                    System.out.println(" verbunden mit Midi Port loopMIDI");
                }
            } catch (Exception e) {
                midi_Out = null;
                System.out.println("Midi Port ungueltig");
            }
        }

    }

}
