package test;

import java.io.IOException;
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

public class OSCConfig {
    private static OSCPortOut portOut;
    private static OSCPortIn portIn;
    
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
        
        try {
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{"RawDataTest", "localhost", 58100, (float) 0.1}));
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"RawDataTest","/Test/rawData", ":rawdata"}));
            portOut.send(new OSCMessage("/Server/startThread", new Object[]{"RawDataTest"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/killThread", new Object[]{"RawDataTest"}));
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{"MyTest", "localhost", 58100, (float) 0.5}));
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"MyTest","/Test/myText", "Hallo"}));
            portOut.send(new OSCMessage("/Server/startThread", new Object[]{"MyTest"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"MyTest","/Test/myText", 5}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"MyTest","/Test/myText", "EmoState"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"MyTest","/Test/myText", (float)0.5}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/blockThread", new Object[]{"MyTest"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/unblockThread", new Object[]{"MyTest"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/killThread", new Object[]{"MyTest"}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

}
