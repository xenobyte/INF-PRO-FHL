package test;

import java.io.IOException;

import constants.Constants;
import oscInput.fileinput.StringParser;

public class StringTest {

    public static void main(String[] args) {
        StringParser s = new StringParser("localhost", Constants.OSCPORTIN);
        try {
            s.parse("/Server/createThread ssif StringTest localhost 58100 10");
            s.parse("/Server/startThread s StringTest");
            s.parse("/Server/blockThread s StringTest");
            s.parse("/Server/unblockThread s StringTest");
            s.parse("/Server/addEvent/AlwaysSend sssfss StringTest EmoState.Meditation > 0.5 /Test Emostate");
            System.in.read();
            s.parse("/Server/killThread s StringTest");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
