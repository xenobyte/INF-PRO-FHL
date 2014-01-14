package kinect.HAL;

import processing.core.PApplet;
import SimpleOpenNI.SimpleOpenNI;

public class Kinect_HAL {

    private static SimpleOpenNI context;
    
    private PApplet papplet; 
    

    
    public Kinect_HAL(PApplet papplet){
        this.papplet =papplet;
        init();
    }
    
    /**
     * Diese Methode initialisiert die Hardware
     */
    private void init() {
        // init Context
        context = new SimpleOpenNI(papplet);

        // Enable Depth Images
        context.enableDepth();

        // Enable Tracking the user Skeleton
        context.enableUser(SimpleOpenNI.SKEL_PROFILE_ALL);

        // Spiegel Effekt deaktivieren
        context.setMirror(true);
       
    }
    
    
    /**
     * Diese Methode Holt die Daten aus der Kinect
     */
    public void update_Context() {
        // Daten aus der Kinect holen
        context.update();
    }
    
    public static SimpleOpenNI getContext() {
        return context;
    }
    
    
}
