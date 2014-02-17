package start;


import com.osc.OSCInput;
import com.udp.DataSource;
import com.udp.UDPClient;

import controll.Controller;
import controll.PollThread;

public class StartConfig {
    public static void main(String[] args){
        Controller c = new Controller();
        OSCInput i = new OSCInput(Integer.parseInt(args[2]),c);
        DataSource u = new UDPClient(args[0], Integer.parseInt(args[1]));
        PollThread p = new PollThread(u, Float.parseFloat(args[3]), c);
        p.start();
    }
}
