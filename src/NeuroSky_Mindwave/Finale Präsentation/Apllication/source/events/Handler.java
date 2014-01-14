package events;

import gui.GUI;

import java.lang.reflect.Method;
import java.util.ArrayList;
import bci.Events.BCI_Events;
import user.Befehle;
import user.User;
import user.Stellung.Stellung;

/**
 * Klassenname: Handler.java
 * 
 * Zweck: Benutzer Stellungen identifizieren und entsprechende Aktionen aufrufen
 * 
 * Ablauf: Am Anfang werden Benutzer Stellungen hinzugefügt. Die Methode handleStellung sorgt für die Prüfung der Stellungen wenn sie aufgerufen wird. Falls eine Stellung identifiziert wurde wird die Entsprechende Aktion
 * ausgeführt. durch geführt
 * 
 * @author M.Kemel Koumenji
 * @version 1.0 14/11/13
 */
public class Handler {

    // MidiInterface die die Cubase Befehle Implementiert
    private IEvents events;

    // Liste für die Benutzer Stellungen
    private ArrayList<Stellung> stellungen;

    private User _User;

    /* Event Methoden */
    private Method play_Track_Event= null;
    private Method stop_Track_Event = null;
    private Method enable_Flanger_Event = null;
    private Method enable_Wah_Event = null;
    private Method enable_Delay_Event = null;
    private Method disable_Effects_Event = null;
    

    /**
     * Konstruktor
     */
    public Handler(User user, BCI_Events bci_Events,GUI gui) {
        this.events = new Events(gui);
        stellungen = new ArrayList<>();
        this._User = user;
        this.stellungen = _User.getUserStellungen();
        mapEventMethods();
    }

    /**************************************************** Stellungen Durchsuchen ********************************************/
    /**
     * Diese Methode prüft ob eine Stellung validiert wurde wenn ja dann wird der Entsprechende Ereignis Aufgerufen.
     * 
     * @param i_userID
     *            ID des Users
     */
    public void handle_User_Stellung() {
        // alle Stellungen durchsuchen
        for (Stellung s : stellungen) {
            // Ereignisauslösen falls eine stellung erkannt wurde
            if (s.check(_User.getUserid())) {
                ereignissAufrufen(s.getName());
            }
        }
    }

    /**
     * Die Methode speichert die Benutzer-Stellungen in einer Liste
     * 
     * @param stellung
     *            Benutzer Stellung
     */
    public void addStellung(Stellung stellung) {
        stellungen.add(stellung);
    }

    // *************************************************** Method Mapping ********************************************/
    /**
     * Diese Methode Initialisiert die callback Methoden
     */
    private void mapEventMethods() {
        try {
            play_Track_Event = this.events.getClass().getMethod("play_Track_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> play_Track_Event");
        }
        try {
            stop_Track_Event = this.events.getClass().getMethod("stop_Track_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> stop_Track_Event");
        }
       
        try {
            enable_Flanger_Event = this.events.getClass().getMethod("enable_Flanger_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> enable_Flanger_Event");
        }
        
        try {
            enable_Wah_Event = this.events.getClass().getMethod("enable_Wah_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> enable_Wah_Event");
        }

        try {
            enable_Delay_Event = this.events.getClass().getMethod("enable_Delay_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> enable_Delay_Event");
        }

        try {
            disable_Effects_Event = this.events.getClass().getMethod("disable_Effects_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> disable_Effects_Event");
        }
    }

    // *************************************************** Trigger Aufruf *****************************************************/
    /**
     * Diese Methode ruft den Entsprechenden Aktionstrigger
     * 
     * @param name
     *            name der Stellung
     */
    private void ereignissAufrufen(Befehle name) {

        switch (name) {

        case PATCH_PLAY_TRACK:
            trigger_play_Track_Event();
            break;
        case PATCH_STOP_TRACK:
            trigger_stop_Track_Event();
            break;     
        case PATCH_FLANGER_ENABLE:
            trigger_enable_Flanger_Event();
            break;   
        case PATCH_WAH_ENABLE:
            trigger_enable_Wah_Event();
            break;
        
        case PATCH_DELAY_ENABLE:
            trigger_enable_Delay_Event();
            break;
        case PATCH_EFFECTS_DISABLE:
            trigger_disable_Effects_Event();
            break;
        default:
            break;
        }

    }

    // *************************************************** Triggern ***********************************************************/
    /**
     * Diese Methode Triggert die entsprechende Aktion
     */
    private void trigger_play_Track_Event() {
        if (play_Track_Event != null) {
            try {
                play_Track_Event.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_play_Track_Event()");
            }
        }
    }

    /**
     *  Diese Methode Triggert die entsprechende Aktion
     */
    private void trigger_stop_Track_Event() {
        if (stop_Track_Event != null) {
            try {
                stop_Track_Event.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_stop_Track_Event()");
            }
        }
    }

    /**
     *  Diese Methode Triggert die entsprechende Aktion
     */
    private void trigger_enable_Flanger_Event() {
        if (enable_Flanger_Event != null) {
            try {
                enable_Flanger_Event.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_enable_Flanger_Event()");
            }
        }
    }

 
    /**
     *  Diese Methode Triggert die entsprechende Aktion
     */
    private void trigger_enable_Wah_Event() {
        if (enable_Wah_Event != null) {
            try {
                enable_Wah_Event.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_enable_Wah_Event()");
            }
        }
    }

    /**
     *  Diese Methode Triggert die entsprechende Aktion
     */
    private void trigger_enable_Delay_Event() {
        if (enable_Delay_Event != null) {
            try {
                enable_Delay_Event.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_enable_Delay_Event()");
            }
        }
    }


    /**
     *  Diese Methode Triggert die entsprechende Aktion
     */
    private void trigger_disable_Effects_Event() {
        if (disable_Effects_Event != null) {
            try {
                disable_Effects_Event.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_disable_Effects_Event()");
            }
        }
    }

}
