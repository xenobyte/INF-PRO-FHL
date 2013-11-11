package udpCom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import ConstantsAndResponsePackages.Constants;

public class UDPServer extends Thread {
    private UDPClient udpClient;
    private DatagramSocket socketUDP;

    public UDPServer(int localPort, String server, int serverPort) {
        try {
            socketUDP = new DatagramSocket(localPort);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        udpClient = new UDPClient(server, serverPort);
        this.start();
    }

    @Override
    public void run() {
        while (true) {
            byte[] buffer = new byte[1];
            DatagramPacket receivePackage = new DatagramPacket(buffer, buffer.length);
            try {
                socketUDP.receive(receivePackage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            
            if (buffer[0] == Constants.REQUESTEMOSTATE) {
                buffer = udpClient.getEmoState();
            }
            DatagramPacket responsePackage = new DatagramPacket(buffer, buffer.length, receivePackage.getAddress(),
                    receivePackage.getPort());
            try {
                System.out.println("asdad");
                socketUDP.send(responsePackage);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
