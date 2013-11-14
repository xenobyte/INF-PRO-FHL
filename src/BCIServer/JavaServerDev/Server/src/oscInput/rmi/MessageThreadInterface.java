package oscInput.rmi;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import event.Event;
import event.OneTimeEvent;
import osc.OSCMessage;
import osc.OSCPortOut;

public class MessageThreadInterface {
    private final String name;
    private OSCPortOut portOut;
    
    
    public MessageThreadInterface(String name, String destip, int destport, float frequency, String sevip, int sevport){
        this.name = name;
        try {
            portOut = new OSCPortOut(InetAddress.getByName(sevip),sevport);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        try {
            portOut.send(new OSCMessage("/Server/createThread", new Object[]{name, destip, destport, frequency}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void addEvent(Event e) {
        if(e instanceof OneTimeEvent){
            try {
                portOut.send(new OSCMessage("/Server/addEvent/OneTimeEvent",e.toOSCData()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }else{
            try {
                portOut.send(new OSCMessage("/Server/addEvent/AlwaysSend",e.toOSCData()));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public void start() {
        try {
            portOut.send(new OSCMessage("/Server/startThread", new Object[]{name}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void block() {
        try {
            portOut.send(new OSCMessage("/Server/blockThread", new Object[]{name}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void unblock() {
        try {
            portOut.send(new OSCMessage("/Server/unbockThread", new Object[]{name}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void kill() {
        try {
            portOut.send(new OSCMessage("/Server/killThread", new Object[]{name}));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
