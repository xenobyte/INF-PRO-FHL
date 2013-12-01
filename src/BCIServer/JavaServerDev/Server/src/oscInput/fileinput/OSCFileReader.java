package oscInput.fileinput;

import java.io.*;

import oscInput.StringParser;

public class OSCFileReader {

    public OSCFileReader(String path, String address, int port, String regex) {
        Reader fr;
        try {
            fr = new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            StringParser p = new StringParser(address, port);
            try {
                s = br.readLine();
                while (s != null) {
                    System.out.println(s);
                    p.parse(s, regex);
                    s = br.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

    }
}
