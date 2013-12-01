package test;

import constants.Constants;
import oscInput.fileinput.OSCFileReader;

public class FileTest {

    public static void main(String[] args) {
        OSCFileReader f = new OSCFileReader("C://Users//Stefan//Desktop//OSCTest.txt","localhost", Constants.OSCPORTIN, " ");
    }
}
