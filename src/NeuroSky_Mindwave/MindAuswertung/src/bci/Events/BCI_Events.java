package bci.Events;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import steuerung.Osc.OSC;

public class BCI_Events implements I_BCI_Events {

   
    private int attention;
    private int meditation;
    private OSC _oscOsc;

    // Date/Time

    private Date start;
    private SimpleDateFormat timesdf;
    private boolean timergestartet;
    private String currentTime;

    // write in File
    private FileWriter fw;
    private BufferedWriter bw;
    private String writeInFile;
    private String fileName;

    public BCI_Events(String filename) {
        
        // OSC
        this._oscOsc = new OSC("localhost", 7400);

        // Date/Time
        this.timergestartet = false;
        this.currentTime = "";
        this.timesdf = new SimpleDateFormat("mm:ss"); // min:sec
        
        // File Writer
        this.fileName = filename;
        this.writeInFile = "";

        try {
            this.fw = new FileWriter(fileName);
        } catch (IOException e) {
            System.out.println("Error Datei erstellen");
        }

        this.bw = new BufferedWriter(fw);
    }

    @Override
    public void poorSignal_Event(int level) {
        //this.poorSignal = level;
    }

    @Override
    public void attention_Event(int level) {
        
        this.attention = level;
        
        if (!timergestartet) {
            this.start = new Date(); // time now
            this._oscOsc.send("/play", 70); // play
            this.timergestartet = true;
        }

        //write in File
        if (this.timergestartet) {
            printInFile(attention, meditation);
        }
    }

    @Override
    public void meditation_Event(int level) {
        this.meditation = level;

    }

    private void printInFile(int attention, int meditation) {

        this.currentTime = timesdf.format(new Date(new Date().getTime() - start.getTime()));

        // write in String
        this.writeInFile = this.currentTime.replace(":", ".") + "," + attention + "," + meditation;

        // Write in File
        try {
            bw.write(writeInFile);
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Writing in File");
        }

        // console output
        System.out.println("Time: " + currentTime + " Attention: " + attention + " Meditation: " + meditation);

        // Stop writing
        if (currentTime.compareToIgnoreCase("04:29") >= 0) {
            try {
                bw.write(writeInFile);
                bw.close();
            } catch (IOException e) {
                System.err.println("Writing in File");
            }
            System.exit(0);
        }
    }
}
