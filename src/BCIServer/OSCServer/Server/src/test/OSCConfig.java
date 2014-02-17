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

public class OSCConfig {
    private static OSCPortOut portOut;
        
    public static void main(String[] args) {       
        try {
            portOut = new OSCPortOut(InetAddress.getByName("localhost"), Constants.OSCPORTIN);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        
        try {
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{"OSCTest", "localhost", 58100, (float) 10}));
            portOut.send(new OSCMessage("/Server/startThread", new Object[]{"OSCTest"}));
            portOut.send(new OSCMessage("/Server/blockThread", new Object[]{"OSCTest"}));
            portOut.send(new OSCMessage("/Server/unblockThread", new Object[]{"OSCTest"}));
            portOut.send(new OSCMessage("/Server/addEvent/AlwaysSend", new Object[]{"OSCTest", "EmoState.Meditation", ">", (float)0.5, "/Test", "Emostate"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/killThread", new Object[]{"OSCTest"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/loadFile", new Object[]{"./OSCFileTest"}));
            System.in.read();
            portOut.send(new OSCMessage("/Server/killThread", new Object[]{"FileTest"}));
        } catch (IOException e) {
            e.printStackTrace();
        }
        

    }

}