package main;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import bci.Events.BCI_Handler;


public class MainProgramm {

    // BCI Handler
    private static BCI_Handler bci_Handler;
 
    public static void main(String[] args) {
        DateFormat formatter = new SimpleDateFormat();
        Calendar cal  = Calendar.getInstance();
        
        String fileName = ("SWA_" + formatter.format(cal.getTime()) + ".csv").replace(" ", "_").replace(":", "");
        fileName = "D:\\BciAuswertung\\" + fileName;
        
        bci_Handler = new BCI_Handler(fileName);

        while(true){
            bci_Handler.handleEvents();    
        }
        
    }
    
    


}
