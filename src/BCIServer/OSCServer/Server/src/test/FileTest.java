package test;

import constants.Constants;
import oscInput.fileinput.OSCFileReader;

public class FileTest {

    public static void main(String[] args) {
        OSCFileReader f = new OSCFileReader("./OSCFileTest","localhost", Constants.OSCPORTIN, " ");
    }
}
