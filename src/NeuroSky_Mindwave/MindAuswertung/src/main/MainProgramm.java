package main;


import bci.Events.BCI_Handler;


public class MainProgramm {

    // BCI Handler
    private static BCI_Handler bci_Handler = new BCI_Handler();
    
    public static void main(String[] args) {
        
        while(true){
            bci_Handler.handleEvents();    
        }
        
    }
    
    


}
