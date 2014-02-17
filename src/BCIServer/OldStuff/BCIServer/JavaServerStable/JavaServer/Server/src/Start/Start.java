package Start;

import java.io.IOException;

import ConstantsAndResponsePackages.Constants;
import ConstantsAndResponsePackages.EmoState;
import threads.Controller;
import threads.Event;
import threads.MessageThread;
import udpCom.UDPClient;
import udpCom.UDPServer;
import user_Interface.StringParser;

public class Start {

    public static void main(String[] args){
        Controller c = new Controller(Constants.SERVERIP, Constants.SERVERPORT);
        StringParser s = new StringParser(c); 
        s.doMagic("createThread(Test:localhost:58100:10)");
        s.doMagic("Test.start()");
        s.doMagic("Test.addEvent(/Test:MyText)");
        s.doMagic("Test.addEvent(/Test/EmoState:EmoState)");
        s.doMagic("Test.block()");
        s.doMagic("Test.unblock()");
        s.doMagic("Test.block()");
        s.doMagic("Test.show()");
        s.doMagic("createThread(TestEvent:localhost:58100:10)");
        s.doMagic("TestEvent.addEvent(EmoState.Meditation:>:50|/Test:Meditation > 50)");
        s.doMagic("TestEvent.addEvent(EmoState.Meditation:<:50,EmoState.Excitement:<:50|/Test:Meditation < 50,Test:Excitement < 50)");
        s.doMagic("TestEvent.start();TestEvent.show(");
        s.doMagic("Test.kill()");
        //s.doMagic("createThread(Hodor:localhost:58100:10);Hodor.addevent(EmoState.Meditation:<:50|/WONDER/source/position:Emostate)");
        //s.doMagic("Hodor.start()");
        /*  
        UDPClient uc = new UDPClient(Constants.SERVERIP, Constants.SERVERPORT);
        
        
        //UDPServer u = new UDPServer(Constants.FORWARDINGPORT, Constants.SERVERIP, Constants.SERVERPORT);        
        //EmoState state = new EmoState(uc.getEmoState());
        //byte[] b = {1};
        //Event e = new Event("emostate.meditation:>:50,emostate.frustration:<=:30",b);
        //System.out.print(e.checkConditon(state));
        
        
        c.createNewMessageThread("Bla:localhost:58100:5");
        c.addEvent("Bla", new Event("emostate.meditation:<:10","/WONDER/source/position:EmoState"));
       // c.addEvent("Bla", new Event("/WONDER/source/position:Test"));
        c.startThread("Bla");
        
        //m.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        c.blockThread("Bla");
        
        try {
            System.in.read();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
    }
}
