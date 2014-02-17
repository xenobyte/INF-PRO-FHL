package oscInput.fileinput;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import osc.OSCMessage;
import osc.OSCPortOut;

public class StringParser {
    OSCPortOut portOut;

    public StringParser(String address, int port) {
        try {
            portOut = new OSCPortOut(InetAddress.getByName(address), port);
        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void parse(String message, String regex) {
        String[] splitMessage = message.split(regex);
        String address = splitMessage[0];
        String arguments = splitMessage[1];
        if (arguments.length() + 2 == splitMessage.length) {
            Object[] data = new Object[arguments.length()];
            for (int i = 0; i < data.length; i++) {
                char arg = arguments.charAt(i);
                switch (arg) {
                case 'f':
                    data[i] = Float.parseFloat(splitMessage[i + 2]);
                    break;
                case 'i':
                    data[i] = Integer.parseInt(splitMessage[i + 2]);
                    break;
                case 's':
                    data[i] = splitMessage[i + 2];
                    break;
                }
            }
            try {
                portOut.send(new OSCMessage(address, data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.err
                    .println("Fehler: Anzahl der Argumente stimmt nicht mit der Anzahl von Daten ueberein");
        }
    }

    public void parse(String message) {
        parse(message, " ");
    }
}
