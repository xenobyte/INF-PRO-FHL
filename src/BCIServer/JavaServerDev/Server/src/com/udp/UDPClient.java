package com.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.LinkedList;

import constants.Constants;
import dataPackages.EEGData;
import dataPackages.EmoState;
import dataPackages.RawData;


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
    
    public EEGData getEEGData(){
        byte[] requestBuffer = {Constants.REQUESTEEGDATA};
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
        EmoState eS = new EmoState(receiveBuffer);
        LinkedList<RawData> l = new LinkedList<RawData>();
        for(int i = 0; i < eS.nSamplesTaken; i++){
            receiveBuffer = new byte[Constants.RESPONSERAWDATASIZE];
            try {
                responsePackage = new DatagramPacket( receiveBuffer, receiveBuffer.length);
                udpSocket.receive(responsePackage);
                l.addLast(new RawData(receiveBuffer));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new EEGData(eS, l);
    }
}
