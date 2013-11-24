package user;

import java.util.ArrayList;
import SimpleOpenNI.IntVector;
import SimpleOpenNI.SimpleOpenNI;
import user.Skelett.Position;
import user.Skelett.Skelett;
import user.Stellung.Stellung;

/**
 * 
 * @author M.Kemel Koumenji
 * 
 */
public class User {

    private SimpleOpenNI context;
    private ArrayList<Stellung> stellungen;
    

    // Stellungen
    private Stellung stellung_Patch_next;
    private Stellung stellung_Patch_prev;
    
    private Stellung stellung_Patch_Play_Back_Track;
    private Stellung stellung_Patch_Stop_Back_Track;
    private Stellung stellung_Patch_Record;
    
    private Stellung stellung_Patch_WAH_enable;
    private Stellung stellung_Patch_WAH_disable;
    

    public User(SimpleOpenNI context) {

        this.context = context;
        this.stellungen = new ArrayList<>();
        
        //Stellungen Hinzufügen
        init_Patch_Next();
        init_Patch_Prev();
        init_Patch_Play_Back_Track();
        init_Patch_Stop_Back_Track();
        init_Patch_Rec();
        init_Patch_WAH_enable();
        init_Patch_BCI_WAH_disable();
    }

    /**
     * Diese Methode liefert die userID zurück
     * 
     * @return (int) userID
     */
    public int getUserid() {
        int userID;
        IntVector userList = new IntVector();
        // get User ID
        context.getUsers(userList);
        if (userList.size() > 0) {
            userID = userList.get(0);
        } else {
            userID = -1;
        }
        return userID;
    }

    /**
     * diese Methode liefert die verschiedenen Benutzer Stellungen
     * 
     * @return ArrayList<Stellung>
     */
    public ArrayList<Stellung> getUserStellungen() {
        return stellungen;
    }

  
    /******************************************** Methoden für die Benutzer-Stellungen ********************************************************/

    /**
     * Diese Methode definiert die Regeln für die Next Patch Stellung
     */
    private void init_Patch_Next() {

        // Stellung initialisieren
        stellung_Patch_next = new Stellung(context, user.Befehle.PATCH_NEXT);
        // Regeln für den Rechten Arm
        stellung_Patch_next.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.KOPF));
        stellung_Patch_next.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.RECHTS_ELLBOGEN));
        stellung_Patch_next.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.RECHTS_VON, Skelett.get(Skelett.RECHTS_SCHULTER));
        //stellung_Patch_next.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.UEBER, Skelett.get(Skelett.KOPF));
        stellungen.add(stellung_Patch_next);
    }

    /**
     * Diese Methode definiert die Regeln für die Previous Patch Stellung
     */
    private void init_Patch_Prev() {

        // Stellung initialisieren
        stellung_Patch_prev = new Stellung(context, user.Befehle.PATCH_PREV);

        // Regeln für den Rechten Arm
        stellung_Patch_prev.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.KOPF));
        stellung_Patch_prev.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.RECHTS_ELLBOGEN));
        stellung_Patch_prev.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.KOPF));
        stellung_Patch_prev.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.RECHTS_VON, Skelett.get(Skelett.RECHTS_SCHULTER));
        //stellung_Patch_prev.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.UEBER, Skelett.get(Skelett.KOPF));
        stellungen.add(stellung_Patch_prev);
    }
    
    
    /**
     * Diese Methode definiert die Regeln für die Play Backtrack Stellung
     */
    private void init_Patch_Play_Back_Track() {

        // Stellung initialisieren
        stellung_Patch_Play_Back_Track = new Stellung(context, user.Befehle.PATCH_PLAY_BACK_TRACK);

        // Regeln für den Rechten Arm
        stellung_Patch_Play_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.KOPF));
        stellung_Patch_Play_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.HALS));
        stellung_Patch_Play_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.HALS));
        stellung_Patch_Play_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.LINKS_SCHULTER));
      
        //zur Liste Hinzufügen
        stellungen.add(stellung_Patch_Play_Back_Track);
    }
    
    
    /**
     * Diese Methode definiert die Regeln für die Stop Backtrack Stellung
     */
    private void init_Patch_Stop_Back_Track() {

        // Stellung initialisieren
        stellung_Patch_Stop_Back_Track = new Stellung(context, user.Befehle.PATCH_STOP_BACK_TRACK);

        // Regeln für den Rechten Arm
        stellung_Patch_Stop_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.KOPF));
        stellung_Patch_Stop_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.HALS));
        stellung_Patch_Stop_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.HALS));
        stellung_Patch_Stop_Back_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.RECHTS_SCHULTER));
        
        //zur Liste Hinzufügen
        stellungen.add(stellung_Patch_Stop_Back_Track);
    }
    
    /**
     * Diese Methode definiert die Regeln für die Stop Backtrack Stellung
     */
    private void init_Patch_Rec() {
        /*
        // Stellung initialisieren
        stellung_Patch_Record = new Stellung(context, multieffekt.PatchControl.PATCH_RECORD);

        // Regeln für den Rechten Arm
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.KOPF));
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.HALS));
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.LINKS_SCHULTER));
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.UEBER, Skelett.get(Skelett.HALS));
        //zur Liste Hinzufügen
        stellungen.add(stellung_Patch_Record);
         */      
        
        
     // Stellung initialisieren
        stellung_Patch_Record = new Stellung(context, user.Befehle.PATCH_RECORD);

        // Regeln für den Rechten Arm
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.KOPF));
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.HALS));
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.HALS));
        stellung_Patch_Record.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.LINKS_SCHULTER));
      
        //zur Liste Hinzufügen
        stellungen.add(stellung_Patch_Record);
        
    }

    
    
    /**
     * Diese Methode definiert die Regeln für die Stop Backtrack Stellung
     */
    private void init_Patch_WAH_enable() {

        // Stellung initialisieren
        stellung_Patch_WAH_enable = new Stellung(context, user.Befehle.PATCH_WAH_ENABLE);

        // Regeln für den Rechten Arm
        stellung_Patch_WAH_enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.TORSO));
        stellung_Patch_WAH_enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.TORSO));
        stellung_Patch_WAH_enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.LINKS_HUEFTE));
        stellung_Patch_WAH_enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.LINKS_HUEFTE));
        //zur Liste Hinzufügen
        stellungen.add(stellung_Patch_WAH_enable);
    }
    
    /**
     * Diese Methode definiert die Regeln für die Stop Backtrack Stellung
     */
    private void init_Patch_BCI_WAH_disable() {

        // Stellung initialisieren
        stellung_Patch_WAH_disable = new Stellung(context, user.Befehle.PATCH_WAH_DISABLE);

        // Regeln für den Rechten Arm
        stellung_Patch_WAH_disable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.RECHTS_KNIE));
        stellung_Patch_WAH_disable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.RECHTS_FUSS));
        //zur Liste Hinzufügen
        stellungen.add(stellung_Patch_WAH_disable);
    }
}
