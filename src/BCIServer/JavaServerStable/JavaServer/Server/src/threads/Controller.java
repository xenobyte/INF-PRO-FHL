package threads;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import udpCom.UDPClient;

public class Controller {
    private HashMap<String, Integer> stringkey = new HashMap<String, Integer>();
    private HashMap<Integer, MessageThread> threads = new HashMap<Integer, MessageThread>();
    private final String serverAddress;
    private final int port;
    
    public Controller(String sev, int po){
        serverAddress = sev;
        port = po;
    }
    
    public void createNewMessageThread(String thread){
        String[] temp = thread.split(":");
        if(stringkey.containsKey(temp[0])){
            System.err.printf("Der Name des Thread ist nicht eindeutig: %s", temp[0]);
            return;
        }
        MessageThread m = new MessageThread(temp[0], temp[1],Integer.parseInt(temp[2]), Integer.parseInt(temp[3]), new UDPClient(serverAddress, port));
        stringkey.put(m.name, m.id);
        threads.put(m.id, m);
    }
    
    public String getString(){
        String s = ""; 
        Set<Integer> set = threads.keySet();
        Iterator<Integer> it = set.iterator();
        while (it.hasNext()){
            s += threads.get(it.next()).getString();
        }
        return s;
    }
    
    public String getString(String name){
        return getString(stringkey.get(name));
    }
    
    public String getString(int id){
        return  threads.get(id).getEventsString();
    }
    
    public void addEvent(String name, Event e){
        addEvent(stringkey.get(name) ,e);
    }
    
    public void addEvent(int id, Event e){
        threads.get(id).addEvent(e);
    }
    
    public void startThread(String name){
        startThread(stringkey.get(name));
    }
    
    public void startThread(int id){
        threads.get(id).start();
    }
    
    public void blockThread(String name){
        blockThread(stringkey.get(name));
    }
    
    public void blockThread(int id){
        threads.get(id).block();
    }
    
    public void unblockThread(String name){
        unblockThread(stringkey.get(name));
    }
    
    public void unblockThread(int id){
        threads.get(id).unblock();
    }
    
    public void killThread(String name){
        killThread(stringkey.get(name));
    }
    
    public void killThread(int id){
        threads.get(id).kill();
        threads.remove(id);
    }
}
