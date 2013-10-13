package NeuroSky;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * INF-PRO-FHL 
 * Semester WS 13/14   
 * Quellen: http://developer.neurosky.com/docs/doku.php?id=start
 * @author Koumenji, Mohamed Kemel;
 */

/*
 * Diese Klasse Inizialisiert ein Socket und den Daten autauschformat (Jason)  
 */
public class Connector{

	private Socket bciSocket;
	private OutputStream outStream;
	private InputStream inStream;

	
	/**
	 * @return int -> 0 wenn die Socketinitialisierung  erfolgreich war
	 * sonst  -1
	 */
	public int connect(){
		//init Socket
		try {
			bciSocket = new Socket("127.0.0.1",13854);
		} catch (UnknownHostException e) {
			System.err.println("Connection Error --> unknown Host ");
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			System.err.println("Connection Error --> IOException ");
			e.printStackTrace();
			return -1;
		}
		
		//get In/output Stream
		try {
			inStream  = bciSocket.getInputStream();
			outStream = bciSocket.getOutputStream();	
		} catch (IOException e) {
			System.err.println("Connection Error --> IOException ");
			e.printStackTrace();
			return -1;
		}
		
		
		//Tell Thinkgear to use jason msg
		JSONObject jFormat = new JSONObject();
		
		//get Optional Raw Data
		try {
			jFormat.put("enableRawOutput", true);
		} catch (JSONException e) {
			System.err.println("Jason error - raw data");
			e.printStackTrace();
			return -1;
		}
		try {
			jFormat.put("format", "Json");
		} catch (JSONException e) {
			System.err.println("Jason error - raw data");
			e.printStackTrace();
			return -1;
		}
		
		//Order Json pakcage format
		PrintWriter out = new PrintWriter(outStream, true);
		out.println(jFormat.toString());
		
		return 0;
	}
	

	/**
	 * Verbindung schließen
	 * @return 0 wenn die Verbindung erfolgreich geschlossen wurde
	 * sont -1
	 */
	public int disconnect(){
		
		try {
			bciSocket.close();
			inStream.close();
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}			
		return 0;
	}
	
	/**
	 * Liefert ein Socket zurück
	 * @return Socket
	 */
	public Socket getSocket(){
		return this.bciSocket;
	}
}
