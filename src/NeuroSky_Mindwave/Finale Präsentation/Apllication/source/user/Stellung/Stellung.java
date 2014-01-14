package user.Stellung;

import java.util.ArrayList;

import user.Befehle;
import user.Skelett.Position;
import SimpleOpenNI.SimpleOpenNI;


/**
 * Klassenname: Stellung.java
 * 
 * Zweck: Dient zur Definition einer Benutzer Stellung
 * 
 * Ablauf : Der Benutzer fügt Regeln zu einer bestimmte Stellung hinzu
 *          mit check werden dann alle Regeln geprüft.
 *          
 * @author M.Kemel Koumenji
 * @version 1.0 14/11/13
 *
 */
public class Stellung {
    
    private SimpleOpenNI context;
    private ArrayList<Regel> regeln;
    private Befehle enum_StellungsName;
    
    /**
     * Konstruktor zur Initialisierung einer Benutzer-Stellung
     * @param context
     * @param enum_Name
     */
    public Stellung(SimpleOpenNI context, Befehle enum_Name) {
        this.context = context;
        regeln = new ArrayList<Regel>();
        enum_StellungsName = enum_Name;
    }

    /**
     * Diese Methode fügt eine Regel zu dieser Stellung
     * 
     * @param i_joint_1
     *            was?
     * @param lage
     *            wo?
     * @param i_joint_2
     *            bezogen auf?
     */
    public void regelHinzufuegen(int i_joint_1, Position lage, int i_joint_2) {
        Regel regel = new Regel(context, i_joint_1, lage, i_joint_2);
        regeln.add(regel);
    }

    /**
     * Diese Methode prüft ob alle Regeln einer Position erfüllt sind
     * 
     * @param userID
     * @return true wenn alle Regeln einer Stellung erfüllt worden . 
     *         false sonst
     */
    public boolean check(int i_userID) {
        boolean result = true;
        for (int i = 0; i < regeln.size(); i++) {
            
            //Regel holen
            Regel regel = (Regel) regeln.get(i);
            
            //Regeln verknüpfen und prüfen
            result = result && regel.check(i_userID);
        }
        return result;
    }
    
    public Befehle getName(){
        return this.enum_StellungsName;
    }
}
