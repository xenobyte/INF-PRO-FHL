package controller;
import java.util.Scanner;
import NeuroSky.Control;
import NeuroSky.Dispatcher;
import promidi.*;

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
	
	private MidiIO midiIO;
	private MidiOut midiOut;
	private int channel;
	private int port;
	private Scanner in; 
	private int poorsignal = 200;
	private int meditation = 0;
	private int attention = 0;
	public MidiControl(){
		in = new Scanner(System.in);
		
		 //Alle Midi Instanzen holen
		  midiIO = MidiIO.getInstance();
		
		  System.out.println("\t\t Midi Ports");
		  System.out.println("******************************************");
		  //print Midi Intanzen
		  midiIO.printDevices();

		  System.out.println("\t\t User input");
		  System.out.println("******************************************");
		  //User Eingabe
		  System.out.println("Enter Midi port :");
		  port = in.nextInt();
		  
		  System.out.println("Enter Midi Channel :");
		  channel = in.nextInt();
		   
		 //open Device
		  midiOut = midiIO.getMidiOut(channel,port);
		 
	}
	
	@Override
	public void poorSignal_Event(int level) {
		poorsignal = level;
		if (level == 200 ){
			System.out.println("poorsignal"+ level);	
		}
	}

	@Override
	public void attention_Event(int level) {
		attention = level;
		//System.out.println(attention);
		
		if (poorsignal != 200 ){
			playNote(64,meditation,500);
			playNote(68,meditation,500);
			playNote(71,meditation,500);	
		}
		
	}

	@Override
	public void meditation_Event(int level) {
		meditation = level;
		//System.out.println(meditation);
	}

	@Override
	public void delta_Event(int level) {
	
	}

	@Override
	public void theta_Event(int level) {
		
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
	
	  void playNote(int pitch, int velo, int duration){
		   Note  note = new Note(pitch,velo,duration);
		    midiOut.sendNote(note);
		  }
	
	public static void main(String[] args) {
		MidiControl _midiControl = new MidiControl();
		Dispatcher _dispatcher = new Dispatcher(_midiControl);
		Thread th = new Thread(_dispatcher);
		th.start();	

	}


}
