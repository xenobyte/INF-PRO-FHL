package midiSender;

import javax.sound.midi.*;

/**
 * INF-PRO-FHL 
 * Semester WS 13/14  
 * Quellen http://docs.oracle.com/javase/tutorial/sound/overview-MIDI.html 
 * @author Koumenji, Mohamed Kemel;
 */



/*
 * Eine Klasse die Midi Nachrichten  an einem bestimmten Midi Empfänger sendet.
 */
public class MidiSender {
	private String midiport;
	private MidiDevice dev;
	private long timeStamp = -1;
	

	public MidiSender(String midiPort){
		getDevice(midiPort);
	}
	
	
	/**
	 * Holt den Midi Treiber
	 * @param _port : Midi Portname
	 */
	private void getDevice(String _port) {
		
		MidiDevice.Info[] devices;
		devices = MidiSystem.getMidiDeviceInfo();
		this.midiport = _port;
		
		// get the device
		for (MidiDevice.Info info: devices) {	
		    MidiDevice device = null;
		    
			try {
				device = MidiSystem.getMidiDevice(info);
			} catch (MidiUnavailableException e) {
				System.err.println("error occured while getting the device");
			} 
			
			
			if (info.toString().equalsIgnoreCase(midiport)){ 			    
			    dev = device;		    
			}
		}	
	}

	 /**
	  * Sendet eine Midi Nachricht			   
	  * @param channel : Midi kanal
	  * @param note : Midi Note
	  * @param velocity : Anschlagsstärke
	  */
	public void send(int channel, int note, int velocity) {
		
		if (channel < 1){
			System.err.println("There is no channel <  1");
			return;
		}
    
    	try {
    		// Open Device
        	if (!dev.isOpen()) {
        		dev.open();
    	    }
    		dev.getReceiver().toString();					   
	    } catch(MidiUnavailableException e) {
	    System.err.println("Device is already Open");
	    }
		  	
		Receiver r = dev.getReceivers().get(0);	
	 
		//set Midi msg
		ShortMessage myMsg = new ShortMessage();
		
		//set msg
		try {
			myMsg.setMessage(ShortMessage.NOTE_ON, channel-1, note, velocity);
		} catch (InvalidMidiDataException e) {
			System.err.println("no Valid midi Data");
		}
		
		//Send midi msg
		r.send(myMsg, timeStamp);
 	
		//Close Device
		dev.close();
	}	    
	    
}

