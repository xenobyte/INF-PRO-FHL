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
    private Method delta_Event_Method = null;
    private Method theta_Event_Method = null;
    private Method lowAlpha_Event_Method = null;
    private Method highAlpha_Event_Method = null;
    private Method lowBeta_Event_Method = null;
    private Method highBeta_Event_Method = null;
    private Method lowGamma_Event_Method = null;
    private Method highGamma_Event_Method = null;
    private Method rawdata_Event_Method = null;
    private Method blink_Event_Method = null;

    public BCI_Handler(BCI_HAL hal) {
        this._Hal =hal;
        this.events = new BCI_Events(); // Init BCI
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
    
    
    public BCI_Events getEvents(){
        return this.events;
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
                    trigger_Attention_Event(esense.getInt("attention"));
                    trigger_Meditation_Event(esense.getInt("meditation"));
                }

                if (key.matches("eegPower")) {
                    JSONObject eegPower = jPacket.getJSONObject("eegPower");
                    trigger_delta_Event(eegPower.getInt("delta"));
                    trigger_theta_Event(eegPower.getInt("theta"));
                    trigger_lowAlpha_Event(eegPower.getInt("lowAlpha"));
                    trigger_highAlpha_Event(eegPower.getInt("highAlpha"));
                    trigger_lowBeta_Event(eegPower.getInt("lowBeta"));
                    trigger_highBeta_Event(eegPower.getInt("highBeta"));
                    trigger_lowGamma_Event(eegPower.getInt("lowGamma"));
                    trigger_highGamma_Event(eegPower.getInt("highGamma"));
                }

                if (key.matches("rawEeg")) {
                    int value = (Integer) jPacket.get("rawEeg");
                    trigger_RawData_Event(value);
                }

                if (key.matches("blinkStrength")) {
                    trigger_Blink_Event(jPacket.getInt(e.toString()));
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
            delta_Event_Method = events.getClass().getMethod("delta_Event", new Class[] { int.class });
            theta_Event_Method = events.getClass().getMethod("theta_Event", new Class[] { int.class });
            lowAlpha_Event_Method = events.getClass().getMethod("lowAlpha_Event", new Class[] { int.class });
            highAlpha_Event_Method = events.getClass().getMethod("highAlpha_Event", new Class[] { int.class });
            lowBeta_Event_Method = events.getClass().getMethod("lowBeta_Event", new Class[] { int.class });
            highBeta_Event_Method = events.getClass().getMethod("highBeta_Event", new Class[] { int.class });
            lowGamma_Event_Method = events.getClass().getMethod("lowGamma_Event", new Class[] { int.class });
            highGamma_Event_Method = events.getClass().getMethod("highGamma_Event", new Class[] { int.class });
            rawdata_Event_Method = events.getClass().getMethod("rawData_Event", new Class[] { int.class });
            blink_Event_Method = events.getClass().getMethod("blink_Event", new Class[] { int.class });
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

    private void trigger_delta_Event(int level) {
        if (delta_Event_Method != null) {
            try {
                delta_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("delta_Event_Method().");
            }
        }
    }

    private void trigger_theta_Event(int level) {
        if (theta_Event_Method != null) {
            try {
                theta_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("theta_Event_Method().");
            }
        }
    }

    private void trigger_lowAlpha_Event(int level) {
        if (lowAlpha_Event_Method != null) {
            try {
                lowAlpha_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("lowAlpha_Event_Method().");
            }
        }
    }

    private void trigger_highAlpha_Event(int level) {
        if (highAlpha_Event_Method != null) {
            try {
                highAlpha_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("highAlpha_Event_Method().");
            }
        }
    }

    private void trigger_lowBeta_Event(int level) {
        if (lowBeta_Event_Method != null) {
            try {
                lowBeta_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("lowBeta_Event_Method().");
            }
        }
    }

    private void trigger_highBeta_Event(int level) {
        if (highBeta_Event_Method != null) {
            try {
                highBeta_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("highBeta_Event_Method().");
            }
        }
    }

    private void trigger_lowGamma_Event(int level) {
        if (lowGamma_Event_Method != null) {
            try {
                lowGamma_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("lowGamma_Event_Method().");
            }
        }
    }

    private void trigger_highGamma_Event(int level) {
        if (highGamma_Event_Method != null) {
            try {
                highGamma_Event_Method.invoke(events, new Object[] { level });
            } catch (Exception e) {
                System.err.println("midGamma_Event_Method().");
            }
        }
    }

    private void trigger_RawData_Event(int data) {
        if (rawdata_Event_Method != null) {
            try {
                rawdata_Event_Method.invoke(events, new Object[] { data });
            } catch (Exception e) {
                System.err.println("rawdata_Event_Method().");
            }
        }
    }

    private void trigger_Blink_Event(int strength) {
        if (blink_Event_Method != null) {

            try {
                blink_Event_Method.invoke(events, new Object[] { strength });
            } catch (Exception e) {
                System.err.println("blink_Event_Method().");

            }
        }
    }

}
