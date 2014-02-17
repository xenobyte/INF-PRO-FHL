package controll;

import com.udp.DataSource;

public class PollThread extends Thread {
    private final DataSource udpClient;
    private final int waitTime;
    private final Controller ctrl;

    public PollThread(DataSource u, double f, Controller c) {
        udpClient = u;
        waitTime = (int) (1000 / f);
        ctrl = c;
    }

    public void run() {
        while (true) {
            ctrl.setEEGData(udpClient.getEEGData());
            try {
                Thread.sleep(waitTime - (System.currentTimeMillis() % waitTime));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
