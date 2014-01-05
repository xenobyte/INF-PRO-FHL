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

public class RawDataTest {
    private static OSCPortIn portIn;
    private static OSCPortOut portOut;
    
    private static OSCListener li = new OSCListener() {
        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
       //     RawData r = new RawData(message);
     //       System.out.println(r);

        }
    };

    private static OSCListener emostate = new OSCListener() {
        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
//            RawData r = new RawData(message);
   //         System.out.println(r);

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
        
        portIn.addListener("/Test", li);
        portIn.addListener("/Test", emostate);
        portIn.startListening();
        
        try {
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{"Test", "localhost", 58100, (float) 0.1}));
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"Test","/Test", ":rawdata"}));
            portOut.send(new OSCMessage("/Server/addPackages", new Object[]{"Test","/Test", "emostate"}));
            portOut.send(new OSCMessage("/Server/startThread", new Object[]{"Test"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/stopThread", new Object[]{"Test"}));
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{"Test"}));
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{"Test"}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

}
