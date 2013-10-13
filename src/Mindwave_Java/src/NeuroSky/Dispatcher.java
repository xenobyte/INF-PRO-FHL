package NeuroSky;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.SocketException;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * INF-PRO-FHL 
 * Semester WS 13/14   
 * Quellen: http://developer.neurosky.com/docs/doku.php?id=start
 * @author Koumenji, Mohamed Kemel;
 */


/*
 * Diese Klase verbindet Sich mit der BCI, die Parser klasse
 */
public class Dispatcher implements Runnable{

	private Connector _connector;
	private StreamParser _streamparser;
	private boolean isRunnig = false;
	private BufferedReader readFromBuffer;
	
	
	
	
	public Dispatcher(Control _control){
		
		//Verbindung aufbauen
		_connector = new Connector();
		
		while (_connector.connect() == -1){
			System.out.println("Trying to Connect to the BCI");
		}
		
		System.out.println("Dispatcher-Connected");
		isRunnig = true;
		//Verbindung aufgebaut
		
		
		//Init StreamParser
		_streamparser = new StreamParser(_control);	
		try {
			readFromBuffer = new BufferedReader(new InputStreamReader(_connector.getSocket().getInputStream()));
		} catch (IOException e) {
			System.err.println("Dispatcher Konstruktor ---Read from Buffer");
		}
	}
	
	@Override
	public void run() {
		
		if (isRunnig && _connector.getSocket().isConnected() ){
			String receivedtgsp;
			try {
				
				while ((receivedtgsp = readFromBuffer.readLine()) != null) {
					String[] jasonObjekctArray = receivedtgsp.split("/\r/");
					for(int s=0;s<jasonObjekctArray.length;s++){
						if(((String) jasonObjekctArray[s]).indexOf("{")>-1){
							JSONObject obj = new JSONObject((String) jasonObjekctArray[s]);
							_streamparser.parseStreamPacket(obj);
						}
					}				
				}
			} 
			catch(SocketException e){
				
			}
			catch (IOException e) {
				
			} catch (JSONException e) {	
			}
		}else{
			isRunnig = false;
		}
  }
	

}
