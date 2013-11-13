package controll;

import java.util.HashMap;
import dataPackages.EEGData;

import event.Event;


public class Controller {
    private EEGData eegData = null;
    private HashMap<String, MessageThread> threads = new HashMap<String, MessageThread>();

    public void setEEGData(EEGData e){
        eegData = e;
    }

    public EEGData getEEGData(){
        return eegData;
    }
    
    public void createNewMessageThread(String name, String address, int port, float frequenz) {
        if (!threads.containsKey(name)) {
            MessageThread m = new MessageThread(address, port, frequenz, this);
            threads.put(name, m);
        } else {
            System.err.printf("A Thread with the Name %s already exist\n", name);
        }
    }

    public void addEvent(String name, Event e) {
        if (threads.containsKey(name)) {
            threads.get(name).addEvent(e);
        } else {
            System.err.printf("A Thread with the Name %s do not exist\n", name);
        }
    }

    public void startThread(String name) {
        if (threads.containsKey(name)) {
            threads.get(name).start();
        } else {
            System.err.printf("A Thread with the Name %s do not exist\n", name);
        }
    }

    public void blockThread(String name) {
        if (threads.containsKey(name)) {
            threads.get(name).block();
        } else {
            System.err.printf("A Thread with the Name %s do not exist\n", name);
        }
    }

    public void unblockThread(String name) {
        if (threads.containsKey(name)) {
            threads.get(name).unblock();
        } else {
            System.err.printf("A Thread with the Name %s do not exist\n", name);
        }
    }

    public void killThread(String name) {
        if (threads.containsKey(name)) {
            threads.get(name).kill();
            threads.remove(name);
        } else {
            System.err.printf("A Thread with the Name %s do not exist\n", name);
        }
    }
}
