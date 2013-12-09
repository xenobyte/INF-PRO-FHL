package bci.Events;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.SocketException;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import bci.HAL.BCI_HAL;

/**
 * INF-PRO-FHL Semester WS 13/14 Quellen: http://developer.neurosky.com/docs/doku.php?id=start
 * 
 * @author Koumenji, Mohamed Kemel;
 */

/*
 * diese Klasse liest den Datenstrom aus dem ThinkGear Connector (TGC), sie parst die JASON packete und ordnet die verschiedenen aufgetreteten Ereignissen an den Korrespondierenden Methoden
 */
public class BCI_Handler {

    private BCI_Events events;
    private BCI_HAL _Hal;
    private BufferedReader readFromBuffer;

    /* Event Methoden */
    private Method poorsignal_Event_Method = null;
    private Method meditation_Event_Method = null;
    private Method attention_Event_Method = null;
   
    
    public BCI_Handler(String filename) {    
        this._Hal = new BCI_HAL();
        this._Hal.connect();
        this.events = new BCI_Events(filename); // Init BCI
        mapControlMethods(); // Map Method
        try {
            readFromBuffer = new BufferedReader(new InputStreamReader(_Hal.getSocket().getInputStream()));
        } catch (IOException e) {
            System.err.println("Dispatcher Konstruktor ---Read from Buffer");
        }
    }

    
    public void handleEvents(){
        if (_Hal.getSocket().isConnected() ){
            String receivedtgsp;
            try {       
                while ((receivedtgsp = readFromBuffer.readLine()) != null) {
                    String[] jasonObjekctArray = receivedtgsp.split("/\r/");
                    for(int s=0;s<jasonObjekctArray.length;s++){
                        if(((String) jasonObjekctArray[s]).indexOf("{")>-1){
                            JSONObject obj = new JSONObject((String) jasonObjekctArray[s]);
                            parsePacket(obj);
                        }
                    }               
                }
            } 
            catch(SocketException e){
                
            }
            catch (IOException e) {
                
            } catch (JSONException e) { 
            }
        }
    }
    
  
    /******************************************** Parse Packets ***************************************************/

    public void parsePacket(JSONObject jPacket) {

        Iterator<?> itr = jPacket.keys(); // iterieren

        while (itr.hasNext()) {

            Object e = itr.next();
            String key = e.toString();

            try {

                if (key.matches("poorSignalLevel")) {
                    trigger_PoorSignal_Event(jPacket.getInt(e.toString()));
                }

                if (key.matches("eSense")) {
                    JSONObject esense = jPacket.getJSONObject("eSense");
                    trigger_Meditation_Event(esense.getInt("meditation"));
                    trigger_Attention_Event(esense.getInt("attention"));                    
                }

               
            }

            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    // *************************************************** Method Mapping ********************************************/
    private void mapControlMethods() {

        try {
            poorsignal_Event_Method = events.getClass().getMethod("poorSignal_Event", new Class[] { int.class });
            attention_Event_Method = events.getClass().getMethod("attention_Event", new Class[] { int.class });
            meditation_Event_Method = events.getClass().getMethod("meditation_Event", new Class[] { int.class });
        } catch (Exception e) {

            System.err.println("Methode Mapping");
        }

    }

    /******************************************** Trigger Events *************************************************/

    private void trigger_PoorSignal_Event(int level) {
        if (poorsignal_Event_Method != null) {
            try {
                poorsignal_Event_Method.invoke(events, new Object[] { level });

            } catch (Exception e) {
                System.err.println("poorsignal_Event_Method()");
            }
        }
    }

    private void trigger_Attention_Event(int level) {
        if (attention_Event_Method != null) {
            try {
                attention_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("trigger_Attention_Event");
            }
        }
    }

    private void trigger_Meditation_Event(int level) {
        if (meditation_Event_Method != null) {
            try {
                meditation_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("meditation_Event_Method().");
            }
        }
    }

   

}
