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
    private Stellung stellung_Play_Track;
    private Stellung stellung_Stop_Track;
    private Stellung stellung_Flanger_Enable;
    private Stellung stellung_Wah_Enable;
    private Stellung stellung_Delay_Enable;
    private Stellung stellung_Effects_Disable;

    public User(SimpleOpenNI context) {

        this.context = context;
        this.stellungen = new ArrayList<>();

        // Initialisierung der Stellungen
        init_Play_Track();
        init_Stop_Track();
        init_Flanger_enable();
        init_Wah_Enable();
        init_Delay_Enable();
        init_effekct_disable();

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
     * Diese Methode definiert die Regeln der Stellung für das abspielen der Hintergrundsmusik
     */
    private void init_Play_Track() {

        // Stellung initialisieren
        stellung_Play_Track = new Stellung(context, user.Befehle.PATCH_PLAY_TRACK);

        // Regeln für den Rechten Arm
        stellung_Play_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.KOPF));
        stellung_Play_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.RECHTS_ELLBOGEN));
        stellung_Play_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.KOPF));
        stellung_Play_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.RECHTS_VON, Skelett.get(Skelett.RECHTS_SCHULTER));

        // Stellung zur Liste hinzufügen
        stellungen.add(stellung_Play_Track);
    }

    /**
     * Diese Methode definiert die Regeln der Stellung für das stoppen der Hintergrundsmusik
     */
    private void init_Stop_Track() {

        // Stellung initialisieren
        stellung_Stop_Track = new Stellung(context, user.Befehle.PATCH_STOP_TRACK);

        // Regeln für den Rechten Arm
        stellung_Stop_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.RECHTS_KNIE));
        stellung_Stop_Track.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.RECHTS_FUSS));

        // Stellung zur Liste hinzufügen
        stellungen.add(stellung_Stop_Track);

    }

    /**
     * Diese Methode definiert die Regeln der Stellung für für das einschalten des Flangers
     */
    private void init_Flanger_enable() {

        // Stellung initialisieren
        stellung_Flanger_Enable = new Stellung(context, user.Befehle.PATCH_FLANGER_ENABLE);

        // Regeln für den Rechten Arm
        stellung_Flanger_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.KOPF));
        stellung_Flanger_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.RECHTS_ELLBOGEN));
        stellung_Flanger_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_ELLBOGEN), Position.RECHTS_VON, Skelett.get(Skelett.RECHTS_SCHULTER));

        // Stellung zur Liste hinzufügen
        stellungen.add(stellung_Flanger_Enable);
    }

    /**
     * Diese Methode definiert die Regeln der Stellung für das anschalten des Wah wah Effekts
     */
    private void init_Wah_Enable() {

        // Stellung initialisieren
        stellung_Wah_Enable = new Stellung(context, user.Befehle.PATCH_WAH_ENABLE);

        // Regeln für den Rechten Arm
        stellung_Wah_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.TORSO));
        stellung_Wah_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.TORSO));
        stellung_Wah_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.LINKS_HUEFTE));
        stellung_Wah_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.LINKS_HUEFTE));
        
        // Stellung zur Liste hinzufügen
        stellungen.add(stellung_Wah_Enable);
    }

    /**
     * Diese Methode definiert die Regeln der Stellung für das anschalten des Delay Effekts
     */
    private void init_Delay_Enable() {

        // Stellung initialisieren
        stellung_Delay_Enable = new Stellung(context, user.Befehle.PATCH_DELAY_ENABLE);

        // Regeln für den Rechten Arm
        stellung_Delay_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.KOPF));
        stellung_Delay_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.HALS));
        stellung_Delay_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.HALS));
        stellung_Delay_Enable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.RECHTS_SCHULTER));

        //Stellung zur Liste hinzufügen
        stellungen.add(stellung_Delay_Enable);
    }

    /**
     * Diese Methode definiert die Regeln der Stellung für das abschalten der Effekte
     */
    private void init_effekct_disable() {

        // Stellung initialisieren
        stellung_Effects_Disable = new Stellung(context, user.Befehle.PATCH_EFFECTS_DISABLE);

        // Regeln für den Rechten Arm
        stellung_Effects_Disable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UNTER, Skelett.get(Skelett.KOPF));
        stellung_Effects_Disable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.UEBER, Skelett.get(Skelett.HALS));
        stellung_Effects_Disable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.LINKS_VON, Skelett.get(Skelett.HALS));
        stellung_Effects_Disable.regelHinzufuegen(Skelett.get(Skelett.RECHTS_HAND), Position.RECHTS_VON, Skelett.get(Skelett.LINKS_SCHULTER));

        //Stellung zur Liste hinzufügen
        stellungen.add(stellung_Effects_Disable);
    }
}
