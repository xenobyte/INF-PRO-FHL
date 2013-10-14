package controller;

import midiSender.MidiSender;
import NeuroSky.Control;
import NeuroSky.Dispatcher;

/**
 * INF-PRO-FHL 
 * Semester WS 13/14   
 * @author Koumenji, Mohamed Kemel;
 */

/*
 * Diese Klasse stellt Methoden bereit. 
 * Die Methoden werden beim auftreten der jeweiligen Ereigniss aufgerufen.
 * eine Midi Nachricht wird bei jedem Aufruf geschickt. 
 */

public class MidiControl  implements Control{
	MidiSender _midiSender;
	
	public MidiControl(String  _Port){
		_midiSender = new MidiSender(_Port);
	}
	
	@Override
	public void poorSignal_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void attention_Event(int level) {
		_midiSender.send(1, 60, 93);
		
	}

	@Override
	public void meditation_Event(int level) {
		_midiSender.send(1, 60, 93);
		
	}

	@Override
	public void delta_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void theta_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lowAlpha_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void highAlpha_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lowBeta_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void highBeta_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void lowGamma_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void highGamma_Event(int level) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rawData_Event(int data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void blink_Event(int strength) {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		MidiControl _midiControl = new MidiControl("portIn");
		Dispatcher _dispatcher = new Dispatcher(_midiControl);
		Thread th = new Thread(_dispatcher);
		th.start();
	}


}
