package Start;

import java.net.SocketException;
import java.util.Date;

import osc.OSCListener;
import osc.OSCMessage;
import osc.OSCPortIn;
import osc.OSCPortOut;

public class OSCTest {
    private static OSCPortIn portIn;
    private static OSCPortOut portOut;
    // private static String registrationAdress = "192.168.3.254";
    private static String registrationAdress = "localhost";
    private static int registrationPort = 58100;
    // private static int registrationPort = 23456;
    private static OSCListener li = new OSCListener() {

        @Override
        public void acceptMessage(Date time, OSCMessage message) {
            System.out.println(message.toString());

            // for(Object o : message.getArguments()){
            // System.out.println(o);
            // }
            // System.out.println(message.getArguments());
            // System.out.println(new String(message.getArguments()));

        }
    };

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Test started");

        System.out.println("Registrated " + registrationAdress + ":" + registrationPort + ".");

        try {
            portIn = new OSCPortIn(registrationPort);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        portIn.addListener("/Test", li);
        portIn.addListener("/Test/EmoState", li);
        //portIn.addListener("/WONDER/renderer/ping", li);
        // portIn.addListener("/WONDER/source/activate", li);
        // portIn.addListener("/WONDER/source/deactivate", li);
        portIn.startListening();
    }
}
