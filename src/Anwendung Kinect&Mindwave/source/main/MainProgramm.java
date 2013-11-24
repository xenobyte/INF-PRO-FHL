package main;


import events.Handler;
import gui.GUI;
import bci.Events.BCI_Events;
import bci.Events.BCI_Handler;
import bci.HAL.BCI_HAL;
import kinect.HAL.Kinect_HAL;
import processing.core.PApplet;
import processing.core.PImage;
import user.User;

public class MainProgramm extends PApplet{

    // Java Serialisierung UID
    private static final long serialVersionUID = 1L;
    
    /************************************************************************** Globale Variablen **************************************************/

    // User
    private User user;

    // Handler
    private Handler handler;

    // Kinect Hardware Abstraktionsschicht
    private Kinect_HAL kinect_HAL;

    // BCI Hardware Abstraktionsschicht
    private BCI_HAL bci_HAL;

    // BCI Handler
    private BCI_Handler bci_Handler;

    // BCI Events
    private BCI_Events bci_Events;
    
    // __GUI_
    GUI _Gui;
    private boolean kunstDarstellung = false;
    
    //Depth frames
    PImage depthFrame;
    private int depthX;
    private int depthY;

    /***************************************************************************** Konstruktor ******************************************************/

    public void setup() {

        // ____ Init Applet_____
        
        if (!kunstDarstellung) {
            size(1280, 480, OPENGL );
            depthX = 640;
            depthY = 0;
        }else{
            size(1280, 960, P3D);
            depthX = 0;
            depthY = 480;
        }
     

        // _____Init BCI_______
        bci_HAL = new BCI_HAL();
        bci_HAL.connect();
        bci_Handler = new BCI_Handler(bci_HAL);
        bci_Events = bci_Handler.getEvents();

        // ________Init Kinect____________
        kinect_HAL = new Kinect_HAL(this);

        // _________Init User________________________
        user = new User(Kinect_HAL.getContext());

      

        // _________Init GUI_____________________________________
        _Gui = new GUI(this, Kinect_HAL.getContext(), bci_Events,kunstDarstellung);

        // _________Init Handler__________________________
        if (!kunstDarstellung) {
            handler = new Handler(user, bci_Handler.getEvents(),_Gui);
        }
        // Start BCI
        thread("handle_BCI");
    }

    /***************************************************************************** Hauptfunktion ***************************************************/

    public void draw() {

        //Depth Frame Darstellung
        depthFrame = Kinect_HAL.getContext().depthImage();
        image(depthFrame, depthX, depthY);
        
        kinect_HAL.update_Context();

        if (!kunstDarstellung) {
          handler.handle_User_Stellung();
        }
        
        _Gui.draw(user.getUserid());

    }

    /*********************************************************************************** handle_BCI ******************************************************/

    public void handle_BCI() { // in Thread ausführen
        bci_Handler.handleEvents();
    }

    /************************************************************* Callback Methoden für die Benutzer Identifizierung ************************************/

    public void onNewUser(int userId) {
        println(" Neuer Benutzer wurde erkannt !!!");
        Kinect_HAL.getContext().startPoseDetection("Psi", userId);
    }

    public void onEndCalibration(int userId, boolean successful) {
        if (successful) {
            println(" Der Benutzer wurde Kalibriert !!!");
            Kinect_HAL.getContext().startTrackingSkeleton(userId);
        } else {
            println(" Der Benutzer wurde nicht kalibriert !!!");
            Kinect_HAL.getContext().startPoseDetection("Psi", userId);
        }
    }

    public void onStartPose(String pose, int userId) {
        println("Kalibirierung starten");
        Kinect_HAL.getContext().stopPoseDetection(userId);
        Kinect_HAL.getContext().requestCalibrationSkeleton(userId, true);
    }

    
    
    public static void main(String args[])
    {
      PApplet.main(new String[] {MainProgramm.class.getName() });
    }
  
}
