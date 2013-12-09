package test;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import constants.Constants;
import dataPackages.RawData;


import osc.OSCListener;
import osc.OSCMessage;
import osc.OSCPortIn;
import osc.OSCPortOut;

public class OSCListenerTest {
    private static OSCPortIn portIn;
    private static OSCPortOut portOut;
    
    private static OSCListener rawData = new OSCListener() {
        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
            RawData r = new RawData(message);
            System.out.println(r);

        }
    };
    
    private static OSCListener myText = new OSCListener(){
        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            for(int i = 0; i < message.getArguments().length; i++){
                System.out.println(message.getArguments()[i]);
            }
        }
    };
    
    
    public static void main(String[] args) {       
        try {
            portOut = new OSCPortOut(InetAddress.getByName("localhost"), Constants.OSCPORTIN);
            portIn = new OSCPortIn(58100);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
        portIn.addListener("/Test/rawData", rawData);
        portIn.addListener("/Test/myText", myText);
        portIn.startListening();
    }

}
