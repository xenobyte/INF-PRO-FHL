package gui;

import SimpleOpenNI.SimpleOpenNI;
import bci.Events.BCI_Events;
import bci.Events.BCI_Signals;
import controlP5.ControlP5;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;
import user.Skelett.Skelett;

public class GUI {

    private BCI_Events bci_Events;
    private PApplet applet;
    private SimpleOpenNI context;
    private int attention;
    private int meditation;
    private boolean kunstDarstellung;
    private String feedback;
  
    
    // ___________Slider___________
    private ControlP5 BCI_Anzeige;
    
    //Hintergrundbild
    private PImage imgSkel;

    //Hotpoint objects
    //HotPoint imageTrigger;

    public GUI(PApplet applet, SimpleOpenNI context, BCI_Events events, boolean kunstDarstellung) {

        this.applet = applet;
        this.context = context;
        this.bci_Events = events;
        this.kunstDarstellung = kunstDarstellung;
        init_BCI_Anzeige();

        // Hintergrund Links Oben
        imgSkel = applet.loadImage("blackwall.jpg");

        //imageTrigger = new HotPoint(applet, "cool.jpg", 500, 200);


    }

    public void draw(int userID) {

        applet.image(imgSkel, 0, 0);
        attention = bci_Events.getEventValue(BCI_Signals.ATTENTION);
        meditation = bci_Events.getEventValue(BCI_Signals.MEDITATION);

        BCI_Anzeige.get("Attention").setValue(attention);
        BCI_Anzeige.get("Meditation").setValue(meditation);

        // NeuroFeedback Signal vorhanden?
        if (bci_Events.getEventValue(BCI_Signals.POOR_SIGNAL) > 100) {
            // set Text Farbe Rot
            applet.fill(255, 0, 0);
            // set Text Font
            applet.textFont(applet.createFont("Arial", 20, true));
            // Set text Position
            applet.text("Kein Signal", 250, 460);
        } else { // signal vorhanden
            // Attention
            applet.fill(255, 0, 0);
            applet.textFont(applet.createFont("Arial", 20, true));
            applet.text("Attention " + bci_Events.getEventValue(BCI_Signals.ATTENTION) + "%", 50, 450);

            // Meditation
            applet.fill(38, 103, 255);
            applet.textFont(applet.createFont("Arial", 20, true));
            applet.text("Meditation " + bci_Events.getEventValue(BCI_Signals.MEDITATION) + "%", 460, 450);
        }

        //Feedback Befehle
        if(feedback != null){
            applet.pushMatrix();
            applet.fill(255);
            applet.textFont(applet.createFont("Arial", 40, true));
            applet.text(feedback+"\n", 450, 60);
            applet.popMatrix();    
        }
        
        // User Skelett malen
        male_Skel(userID);
        
        
        if(this.kunstDarstellung){
         // malen
            male_Abstakt(userID);    
        }
    
        //imageTrigger.check(context, userID, Skelett.get(Skelett.RECHTS_HAND));
     
    }
    
   

    public void enFeedback(String feedback){
        this.feedback = feedback;    
    }
    /**
     * Diese Methode zeichnet alle Joints eines Skelett
     */
    private void male_Skel(int userID) {
        if (userID != -1) {
            applet.fill(255);
            male_Joints(userID, Skelett.get(Skelett.KOPF));
            male_Joints(userID, Skelett.get(Skelett.HALS));
            male_Joints(userID, Skelett.get(Skelett.TORSO));
            male_Joints(userID, Skelett.get(Skelett.LINKS_SCHULTER));
            male_Joints(userID, Skelett.get(Skelett.LINKS_ELLBOGEN));
            male_Joints(userID, Skelett.get(Skelett.LINKS_HAND));
            male_Joints(userID, Skelett.get(Skelett.LINKS_HUEFTE));
            male_Joints(userID, Skelett.get(Skelett.LINKS_KNIE));
            male_Joints(userID, Skelett.get(Skelett.LINKS_FUSS));
            male_Joints(userID, Skelett.get(Skelett.RECHTS_SCHULTER));
            male_Joints(userID, Skelett.get(Skelett.RECHTS_ELLBOGEN));
            male_Joints(userID, Skelett.get(Skelett.RECHTS_HAND));
            male_Joints(userID, Skelett.get(Skelett.RECHTS_HUEFTE));
            male_Joints(userID, Skelett.get(Skelett.RECHTS_KNIE));
            male_Joints(userID, Skelett.get(Skelett.RECHTS_FUSS));
        }

    }

    /**
     * Diese Methode zeichnet einen Joint
     * 
     * @param UserID
     * @param jointID
     */
    private void male_Joints(int UserID, int jointID) {
        PVector joint = new PVector();
        float confidence = context.getJointPositionSkeleton(UserID, jointID, joint);
        if (confidence < 0.5) {
            return;
        }
        PVector convertedJoint = new PVector();
        context.convertRealWorldToProjective(joint, convertedJoint);

        // je weiter der Benutzer von der Kamera entfernt desto kleiner sind die joints
        float z = PApplet.map(convertedJoint.z, 800, 2000, 1, 5);

        // joints zeichnen
        applet.ellipse(convertedJoint.x, convertedJoint.y, 80 / z, 80 / z);

        //draw_abstarctArt(UserID);
    }

    private void male_Abstakt(int UserID) {

        PVector leftHand = new PVector();
        PVector rightHand = new PVector();
        PVector linke_hand_RealWorld = new PVector();
        PVector rechte_Hand_RealWorld = new PVector();
        float zLeft;
        float zright;
     
        
        //Hand Koordinaten ermitteln
        context.getJointPositionSkeleton(UserID, SimpleOpenNI.SKEL_LEFT_HAND, leftHand);
        context.getJointPositionSkeleton(UserID, SimpleOpenNI.SKEL_RIGHT_HAND, rightHand);

        //Hand Koordinaten zur Welt Koordinaten konvertieren
        context.convertRealWorldToProjective(leftHand, linke_hand_RealWorld);
        context.convertRealWorldToProjective(rightHand, rechte_Hand_RealWorld);
        

        // _______Linien_____
        applet.pushMatrix(); //Transformatopn isolieren
        applet.translate(640, 0); // Applet Oben rechts
        applet.line(linke_hand_RealWorld.x, linke_hand_RealWorld.y, rechte_Hand_RealWorld.x, rechte_Hand_RealWorld.y);
        applet.popMatrix();

        
        //map Händre z werte für Ellipsen Grösse
        zLeft = PApplet.map(linke_hand_RealWorld.z, 800, 2000, 1, 5);
        zright = PApplet.map(rechte_Hand_RealWorld.z, 800, 2000, 1, 5);
        
        
        // ______Ellipsen________
        applet.pushMatrix(); //Transformatopn isolieren
            applet.translate(640, 481); // Applet unten rechts
            applet.ellipse(linke_hand_RealWorld.x, linke_hand_RealWorld.y, 80 / zLeft, 80 / zLeft);
            applet.ellipse(rechte_Hand_RealWorld.x, rechte_Hand_RealWorld.y, 80 / zright, 80 / zright);
        applet.popMatrix();
        
        
        //___malen__
        
        //Abstand zwischen den Händen berechnen
        PVector abstand = PVector.sub(leftHand, rightHand);
        
        //Abstand normieren (0-1)
        abstand.normalize();
      
        //Linien Farbe einstellen 
        applet.stroke(PApplet.map(attention, 0, 100, 0, 1) * 255,
                     abstand.x * 255, abstand.y * 255);
        
        //Lien Stärke einstellen
        applet.strokeWeight(PApplet.map(meditation, 0, 100, 1, 10));
    }

    /************************************************************************ Methoden für Initialisierung der Anzeige der BCI Signale *******************************************/
    private void init_BCI_Anzeige() {
        BCI_Anzeige = new ControlP5(applet);
        add_Slider_Attention();
        add_Slider_Meditation();
    }

    private void add_Slider_Attention() {
        BCI_Anzeige.addSlider("Attention").setPosition(10, 260).setWidth(20).setHeight(200).setRange(0, 100).setValue(0).setColorForeground(applet.color(255, 0, 0))
                .setColorBackground(applet.color(0, 0, 0)).setLabelVisible(false);

    }

    private void add_Slider_Meditation() {
        BCI_Anzeige.addSlider("Meditation").setPosition(615, 260).setWidth(20).setHeight(200).setRange(0, 100).setValue(0).setColorForeground(applet.color(38, 103, 255))
                .setColorBackground(applet.color(0, 0, 0)).setLabelVisible(false);
    }

}
