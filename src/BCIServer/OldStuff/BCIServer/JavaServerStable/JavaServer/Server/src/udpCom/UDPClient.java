package udpCom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ConstantsAndResponsePackages.Constants;

public class UDPClient {
    DatagramSocket udpSocket;
    InetAddress serverAddress;
    int serverPort;
    
    public UDPClient(String serverName, int serverPort){
        try {
            udpSocket = new DatagramSocket();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        try {
            serverAddress = InetAddress.getByName(serverName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        this.serverPort = serverPort;  
    }
    
    public byte[] getEmoState(){
        byte[] requestBuffer = {Constants.REQUESTEMOSTATE};
        DatagramPacket requestPackage = new DatagramPacket( requestBuffer, requestBuffer.length, serverAddress, serverPort);
        try {
            udpSocket.send(requestPackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] receiveBuffer = new byte[Constants.RESPONSEEMOSTATESIZE];
        DatagramPacket responsePackage = new DatagramPacket( receiveBuffer, receiveBuffer.length);
        try {
            udpSocket.receive(responsePackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveBuffer;
    }
}
