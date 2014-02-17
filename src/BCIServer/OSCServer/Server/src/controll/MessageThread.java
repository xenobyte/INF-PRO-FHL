package controll;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.osc.OSCOut;

import dataPackages.EEGData;
import event.Event;

public class MessageThread extends Thread {
    private int waitTime;
    private OSCOut portOut;
    private final Controller ctrl;

    private LinkedList<Event> events = new LinkedList<Event>();
    private boolean running = true;
    private Lock blockMutex = new ReentrantLock(true);

    public MessageThread(String sevAd, int port, float frequenz, Controller controller) {
        ctrl = controller;
        portOut = new OSCOut(sevAd, port);
        waitTime = (int) (1000 / frequenz);

    }

    public void run() {

        while (running) {
            blockMutex.lock();
            if (!events.isEmpty()) {
                Iterator<Event> itEvents = events.iterator();
                EEGData eeg = ctrl.getEEGData();
                while (itEvents.hasNext()) {
                    Event e = itEvents.next();
                    if (ctrl.getEEGData() != null) {
                        if (e.checkCondition(eeg)) {

                            portOut.send(e.getMessages(eeg));
                        }
                    }else{
                        System.err.println("No Connection");
                    }
                }
            }

            blockMutex.unlock();
            try {
                Thread.sleep(waitTime - (System.currentTimeMillis() % waitTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public void block() {
        blockMutex.lock();
    }

    public void unblock() {
        blockMutex.unlock();
    }

    public void kill() {
        running = false;
    }
}
