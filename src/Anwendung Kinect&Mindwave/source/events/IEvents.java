package events;

/**
 * Interface Name: Befehle.java
 * 
 * Zweck: Definiert die Cubase Befehle die Implementiert werden sollten
 * 
 * @author M.Kemel Koumenji
 * @version 1.0 19/11/13
 */
public interface IEvents {
    public void patch_Next_Event();
    public void patch_Prev_Event();
    public void patch_Record_Event();
    public void patch_Play_back_Track_Event();
    public void patch_Stop_back_Track_Event(); 
    public void patch_WAH_enable_Event();
    public void patch_WAH_disable_Event();
}
