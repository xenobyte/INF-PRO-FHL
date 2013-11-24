

package gui;

import javax.swing.*;

/**
 * Klassenname: Image.java
 * 
 * Zweck: Zeigt und verbergen eines Bildes auf dem Bildschirm
 * 
 * Ablauf: beim Abruf der setVisibility Methode wird zuerst geprüft 
 *         ob der JFrame angezeigt werden soll.
 *         Falls ja wird dann geprüft ob einen JFrame erstellt wurde.
 *         Wenn keiner erstellt wurde wird dann ein neues Erstellt und angezeigt.
 *         der JFrame wird verborgen und gelöscht  setvisible = false
 *           
 * @author M.Kemel Koumenji
 * @version 1.0 14/11/13
 */

public class Feedback extends JFrame {

    private static final long serialVersionUID = 1L;
    private static boolean isCreated = false;
    private static Feedback imgFeedback;

    /**
     * Konstruktor für die Initialisierung der Jframe
     * @param bildName die Name des Bildes das angezeigt werden soll
     */
    private Feedback(String bildName) {
        super("ShapedWindow");
        
        //ImageIcon erstellen
        ImageIcon ic = new ImageIcon(bildName);
        
        //Keine Deko
        setUndecorated(true);
        
        //Bildgröße einstellen
        setSize(ic.getIconWidth(), ic.getIconHeight());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Bild zu JFrame einfügen
        add(new JLabel(ic));
    }

    /**
     * Diese Methode zeigt einen Bild auf dem Bildschirm 
     * Das Bild dient   als Benutzer feedback
     * @param bildName  Das Bild das angezeigt werden soll
     * @param setvisible   vorzeigen bzw. verbergen des Bildes
     */
    public static void setVisibility(String bildName,boolean setvisible) {
        
        if (setvisible) {  
        
            if (!isCreated) {  //Wurde einen JFrame Erstellt? 
                
                //JFrame Erstellen
                imgFeedback = new Feedback(bildName);
                
                //Deckkraft des Bildes einstellen
                imgFeedback.setOpacity(0.5f);
                
                //Bild vorzeigen
                imgFeedback.setVisible(true);
                
                //Merken das ein JFrame erstellt wurde
                isCreated = true;
            }
        }else{ 
            if (isCreated) { //JFrame Erstellt?
                
                //JFrame verbergen
                imgFeedback.setVisible(false);
                
                //JFrame Löschen
                isCreated = false;
                imgFeedback = null;
            }
        }
    }

}
