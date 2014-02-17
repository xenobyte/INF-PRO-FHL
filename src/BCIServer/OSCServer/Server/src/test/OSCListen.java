package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import constants.Constants;
import osc.OSCListener;
import osc.OSCMessage;
import osc.OSCPortIn;
import osc.OSCPortOut;

public class OSCListen {
    private static OSCPortIn portIn;
    
    private static OSCListener emostate = new OSCListener() {
        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());
        }
    };
    
    public static void main(String[] args) {       
        try {
            portIn = new OSCPortIn(58100);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        
        portIn.addListener("/Test", emostate);
        portIn.startListening();

       
    }

}