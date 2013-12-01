package test;

import constants.Constants;
import oscInput.StringParser;

public class StringTest {

    public static void main(String[] args) {
        StringParser s = new StringParser("localhost", Constants.OSCPORTIN);
        s.parse("/Server/createThread ssif name localhost 58100 1");

    }

}
