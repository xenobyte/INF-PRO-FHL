package controller;
import NeuroSky.Control;

import NeuroSky.Dispatcher;
import oscSender.OSC_Sender;

/**
 * INF-PRO-FHL 
 * Semester WS 13/14   
 * @author Koumenji, Mohamed Kemel;
 */

/*
 * Diese Klasse stellt Methoden bereit. 
 * Die Methoden werden beim auftreten der jeweiligen Ereigniss aufgerufen
 * eine Osc Nachricht wird bei jedem Aufruf geschickt. 
 */

public class OscControl implements Control{

	private OSC_Sender _oscOsc;
	
	public OscControl (String ipAdd, int port){
		_oscOsc = new OSC_Sender(ipAdd, port);
	}
	
	
	@Override
	public void poorSignal_Event(int level) {
		if (level == 200 ){
			System.out.println("poorsignal"+ level);	
		}
	}
	
	@Override
	public void attention_Event(int level) {
		_oscOsc.send("/attention", level);
	}

	@Override
	public void meditation_Event(int level) {
		_oscOsc.send("/meditation", level);
		
	}
	
	@Override
	public void delta_Event(int level) {
		_oscOsc.send("/delta", level);
		
	}
	

	@Override
	public void theta_Event(int level) {
		_oscOsc.send("/theta", level);
		
	}
	
	@Override
	public void lowAlpha_Event(int level) {
		_oscOsc.send("/lowAlpha", level);
	}
	
	
	@Override
	public void highAlpha_Event(int level) {
		_oscOsc.send("/highAlpha", level);
		
	}
	
	@Override
	public void lowBeta_Event(int level) {
		_oscOsc.send("/lowBeta", level);
		
	}

	@Override
	public void highBeta_Event(int level) {
		_oscOsc.send("/highBeta", level);
		
	}
	
	
	@Override
	public void lowGamma_Event(int level) {
		_oscOsc.send("/lowGamma", level);
		
	}

	@Override
	public void highGamma_Event(int level) {
		_oscOsc.send("/highGamma", level);
		
	}
	
	@Override
	public void rawData_Event(int data) {
		_oscOsc.send("/raw", data);	
	}

	@Override
	public void blink_Event(int strength) {
		_oscOsc.send("/blink", strength);
		
	}

	public static void main(String[] args) {
		Control _control = new OscControl("localhost", 7400);
		Dispatcher _dispatcher = new Dispatcher(_control);
		Thread th = new Thread(_dispatcher);
		th.start();
	}

}
