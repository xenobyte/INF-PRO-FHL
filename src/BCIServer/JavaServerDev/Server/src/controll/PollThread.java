package controll;

import com.udp.UDPClient;

public class PollThread extends Thread{
    private final UDPClient udpClient;
    private final int waitTime;   
    private final Controller ctrl;
    
    public PollThread(UDPClient u, double f, Controller c){
        udpClient = u;
        waitTime = (int) (1000/f);
        ctrl = c;
    }
    
    public void run(){
        ctrl.setEEGData(udpClient.getEEGData());
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
