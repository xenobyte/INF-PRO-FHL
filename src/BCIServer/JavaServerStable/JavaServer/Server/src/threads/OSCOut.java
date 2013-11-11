package threads;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import osc.OSCMessage;
import osc.OSCPortOut;

public class OSCOut {
    private static OSCPortOut portOut;

    public OSCOut(String serverAddress, int port) {
        try {
            portOut = new OSCPortOut(InetAddress.getByName(serverAddress), 58100);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void send(OSCMessage[] messages) {
        try {
            for (int i = 0; i < messages.length; i++) {
                portOut.send(messages[i]);
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
