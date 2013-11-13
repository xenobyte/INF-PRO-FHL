package Start;


import com.osc.OSCInput;
import com.udp.UDPClient;

import constants.Constants;
import controll.Controller;
import controll.PollThread;


public class Start {

    public static void main(String[] args){
        Controller c = new Controller();
        OSCInput i = new OSCInput(Constants.OSCPORTIN,c);
        UDPClient u = new UDPClient(Constants.SERVERIP, Constants.SERVERPORT);
        PollThread p = new PollThread(u, Constants.POLLFREQUENCY, c);
        p.start();
    }
}
