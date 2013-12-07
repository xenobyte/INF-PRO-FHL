package NeuroSky;

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
public interface Control {
	
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
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis delta auftretet
	 * @param level: int
	 */
	public void delta_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis theta auftretet
	 * @param level: int
	 */
	public void theta_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis lowAlpha auftretet
	 * @param level: int
	 */
	public void lowAlpha_Event(int level);

	/**
	 * Wird  aufgerufen wenn  das Ereignis highAlpha auftretet
	 * @param level: int
	 */
	public void highAlpha_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis lowBeta auftretet
	 * @param level: int
	 */
	public void lowBeta_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis highBeta auftretet
	 * @param level: int
	 */
	public void highBeta_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis lowGamma auftretet
	 * @param level: int
	 */
	public void lowGamma_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis highGamma auftretet
	 * @param level: int
	 */
	public void highGamma_Event(int level);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis rawData auftretet
	 * @param level: int
	 */
	public void rawData_Event( int data);
	
	/**
	 * Wird  aufgerufen wenn  das Ereignis blink auftretet
	 * @param level: int
	 */
	public void blink_Event(int strength);
     

}
