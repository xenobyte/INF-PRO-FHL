package com.udp;



public class UDPServer extends Thread {
 /*   private UDPClient udpClient;
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
            DatagramPacket responsePackage;
            if (buffer[0] == Constants.REQUESTEEGDATA) {
                buffer = udpClient.getEmoState();
            }
            
        }*/
   // }
}
