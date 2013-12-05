package bci.Events;

/**
 * INF-PRO-FHL 
 * Semester WS 13/14   
 * Quellen: http://developer.neurosky.com/docs/doku.php?id=start
 * @author Koumenji, Mohamed Kemel;
 */

/*
 * Eine Interface die die Verschiedenen Erreignisse bereitstellt
 *
 */
public interface I_BCI_Events {
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis poorSignal auftretet
	 * @param level: int
	 */
	public void poorSignal_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis attention auftretet
	 * @param level: int
	 */
	public void attention_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis meditation auftretet
	 * @param level: int
	 */
	public void meditation_Event(int level);
	

     

}
