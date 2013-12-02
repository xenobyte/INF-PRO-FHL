package test;

import java.io.IOException;

import constants.Constants;
import oscInput.StringParser;

public class StringTest {

    public static void main(String[] args) {
        StringParser s = new StringParser("localhost", Constants.OSCPORTIN);
        try {
            s.parse("/Server/createThread ssif RawDataTest localhost 58100 0.1");
            s.parse("/Server/addPackages sss RawDataTest /Test/rawData :rawdata");
            s.parse("/Server/startThread s RawDataTest");
            System.in.read();
            s.parse("/Server/killThread s RawDataTest");
            s.parse("/Server/createThread ssif MyTest localhost 58100 0.2");
            s.parse("/Server/addPackages sss MyTest /Test/myText Hallo");
            s.parse("/Server/startThread s MyTest");
            System.in.read();
            s.parse("/Server/addPackages ssi MyTest /Test/myText, 5");
            System.in.read();
            s.parse("/Server/addPackages sss MyTest /Test/myText EmoState");
            System.in.read();
            s.parse("/Server/addPackages ssf MyTest /Test/myText 0.5");
            System.in.read();
            s.parse("/Server/blockThread s MyTest");
            System.in.read();
            s.parse("/Server/unblockThread s MyTest");
            System.in.read();
            s.parse("/Server/killThread s MyTest");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
