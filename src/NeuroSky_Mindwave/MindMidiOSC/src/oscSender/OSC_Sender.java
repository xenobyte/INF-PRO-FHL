package oscSender;

import com.illposed.osc.*;

import java.io.IOException;
import java.net.*;

/**
 * INF-PRO-FHL 
 * Semester WS 13/14  
 * Quellen http://opensoundcontrol.org/implementation/java-osc 
 * @author Koumenji, Mohamed Kemel;
 */


/*
 * Eine Klasse die OSC Nachrichten über das Netzwerk sendet.
 */

public class OSC_Sender {
	
	//Empfangs Port
	private OSCPortOut sender;

	public OSC_Sender (String _remoteIp, int _port){
		try {
			 sender = new OSCPortOut(InetAddress.getByName(_remoteIp), _port);
		} catch (SocketException e) {
			System.err.println("OSC- Initializing Socket");
		}catch (UnknownHostException e) {
			System.err.println("OSC- getting loval host");
		}
	}
	
	/**
	 * Sendet eine Osc Machricht über das Netzwerk
	 * @param type : String -> Nachrichtentyp
	 * @param value : int  -> Wert
	 */
	public void send(String type , int value){	
		  
	    Object values[] = new Object[1];
	    values[0] = new Integer(value);
	   
	    //OSC msg 
	    OSCMessage msg = new OSCMessage(type,values);
	 
	    //send msg
	    try {  	
			sender.send(msg);
		} catch (IOException e) {
			System.err.println("OSC- error sending msg:" + type);
		}
	}
}
