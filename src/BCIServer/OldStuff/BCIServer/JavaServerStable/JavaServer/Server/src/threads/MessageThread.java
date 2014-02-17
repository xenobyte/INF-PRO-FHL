package threads;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import ConstantsAndResponsePackages.EmoState;

import udpCom.UDPClient;

public class MessageThread extends Thread{
    public final int id;
    public final String name;
    private int waitTime;
    private OSCOut portOut;
    private UDPClient udpClient;
    
    private LinkedList<Event> events = new LinkedList<Event>();
    private static int idCounter = 0;
    private boolean running = true;
    private Lock blockMutex = new ReentrantLock(true);
    
    private final String serverAddress;
    private final int port;
    private final int frequenzy;
    
    public MessageThread(String name, String sevAd, int port, int frequenzy, UDPClient uc){
        id = idCounter;
        idCounter++;
        
        portOut = new OSCOut(sevAd, port);
        udpClient = uc;
        
        
        this.name = name;
        waitTime = 1000/frequenzy;

        serverAddress = sevAd;
        this.port = port;
        this.frequenzy = frequenzy;
        
        
    }
    
    public void run(){

        while(running){
            blockMutex.lock();
            if(!events.isEmpty()){
                Iterator<Event> itEvents = events.iterator();
                while(itEvents.hasNext()){
                    Event e = itEvents.next();
                    EmoState state = new EmoState(udpClient.getEmoState()); 
                    
                    if(e.checkConditon(state)){
                        portOut.send(e.getMessages(state));
                    }
                }
            }
            
            blockMutex.unlock();
            try {
                Thread.sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public String getString(){
        return String.format("ID = %d,Name = %s{\n    Serveraddress = %s,\n    Serverport = %d,\n    Frequenzy = %dHz,\n    Number of Events = %d\n}\n", 
                id, name, serverAddress, port, frequenzy, events.size());
    }
    
    public String getEventsString(){
        String s = "";
        Iterator<Event> itEv = events.iterator();
        while(itEv.hasNext()){
            s += itEv.next().getString();
        }
        return s;
    }
    
    public void addEvent(Event e){
        events.add(e);
    }
    
    public void block(){
        blockMutex.lock();
    }
    
    public void unblock(){
        blockMutex.unlock();
    }
    
    public void kill(){
        running = false;
    }
}
