package com.osc;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Iterator;
import java.util.LinkedList;

import osc.OSCMessage;
import osc.OSCPortOut;

public class OSCOut {
    private OSCPortOut portOut;
    
    public OSCOut(String serverAddress, int port) {
        try {
            portOut = new OSCPortOut(InetAddress.getByName(serverAddress), port);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void send(LinkedList<OSCMessage> messages) {
        try {
            Iterator<OSCMessage> li = messages.iterator();
            while(li.hasNext()){    
                portOut.send(li.next());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(OSCMessage message) {
        try {
            portOut.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
