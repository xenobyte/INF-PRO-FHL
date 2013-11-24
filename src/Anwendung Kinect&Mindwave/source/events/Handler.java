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
    private Method patch_Next_Event_Method = null;
    private Method patch_Prev_Event_Method = null;
    private Method patch_Record_Event_Method = null;
    private Method patch_Play_back_Track_Event_Method = null;
    private Method patch_Stop_back_Track_Event_Method = null;
    private Method patch_WAH_enable_Event_Method = null;
    private Method patch_WAH_disable_Event_Method = null;

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
            patch_Next_Event_Method = this.events.getClass().getMethod("patch_Next_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Next_Event_Method");
        }
        try {
            patch_Prev_Event_Method = this.events.getClass().getMethod("patch_Prev_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Prev_Event_Method");
        }
        try {
            patch_Prev_Event_Method = this.events.getClass().getMethod("patch_Prev_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Prev_Event_Method");
        }
        try {
            patch_Prev_Event_Method = this.events.getClass().getMethod("patch_Prev_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Prev_Event_Method");
        }
       
        try {
            patch_Record_Event_Method = this.events.getClass().getMethod("patch_Record_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Mute_Event_Method");
        }
        
        try {
            patch_Play_back_Track_Event_Method = this.events.getClass().getMethod("patch_Play_back_Track_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Play_back_Track_Event_Method");
        }

        try {
            patch_Stop_back_Track_Event_Method = this.events.getClass().getMethod("patch_Stop_back_Track_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_Stop_back_Track_Event_Method");
        }

        try {
            patch_WAH_enable_Event_Method = this.events.getClass().getMethod("patch_WAH_enable_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_WAH_enable_Event_Method");
        }

        try {
            patch_WAH_disable_Event_Method = this.events.getClass().getMethod("patch_WAH_disable_Event");
        } catch (Exception e) {
            System.err.println("in [Handler] mapEventMethods -> patch_BCI_Attention_disable_Event_Method");
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

        case PATCH_NEXT:
            trigger_patch_Next_Event();
            break;
        case PATCH_PREV:
            trigger_patch_Prev_Event();
            break;
        case PATCH_RECORD:
            trigger_patch_Record_Event();
            break;
        case PATCH_PLAY_BACK_TRACK:
            trigger_patch_Play_back_Track_Event();
            break;
        case PATCH_STOP_BACK_TRACK:
            trigger_patch_Stop_back_Track_Event();
            break;
        case PATCH_WAH_ENABLE:
            trigger_patch_WAH_enable_Event();
            break;
        case PATCH_WAH_DISABLE:
            trigger_patch_WAH_disable_Event();
            break;
        default:
            break;
        }

    }

    // *************************************************** Triggern ***********************************************************/

    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_Next_Event() {
        if (patch_Next_Event_Method != null) {
            try {
                patch_Next_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_Next_Event()");
            }
        }
    }

    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_Prev_Event() {
        if (patch_Next_Event_Method != null) {
            try {
                patch_Prev_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_Prev_Event()");
            }
        }
    }

    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_Record_Event() {
        if (patch_Record_Event_Method != null) {
            try {
                patch_Record_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_Record_Event()");
            }
        }
    }

 
    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_Play_back_Track_Event() {
        if (patch_Play_back_Track_Event_Method != null) {
            try {
                patch_Play_back_Track_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_Play_back_Track_Event()");
            }
        }
    }

    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_Stop_back_Track_Event() {
        if (patch_Stop_back_Track_Event_Method != null) {
            try {
                patch_Stop_back_Track_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_Prev_Event()");
            }
        }
    }

    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_WAH_enable_Event() {
        if (patch_Stop_back_Track_Event_Method != null) {
            try {
                patch_WAH_enable_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_WAH_enable_Event()");
            }
        }
    }

    /**
     * Diese Methode Triggert die entsprechende Events Aktion
     */
    private void trigger_patch_WAH_disable_Event() {
        if (patch_Stop_back_Track_Event_Method != null) {
            try {
                patch_WAH_disable_Event_Method.invoke(this.events);
            } catch (Exception e) {
                System.err.println("in [Handler]-> trigger_patch_WAH_disable_Event()");
            }
        }
    }

}
