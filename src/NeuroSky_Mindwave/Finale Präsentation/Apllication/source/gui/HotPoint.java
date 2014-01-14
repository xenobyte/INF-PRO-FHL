package gui;

import SimpleOpenNI.SimpleOpenNI;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class HotPoint {

    private boolean warDrin;
    private boolean istDrin;
    private PApplet applet;

    // store the image
    PImage image;

    // Image Coordinates
    private int imageX;
    private int imageY;

    public HotPoint(PApplet applet, String imageName, int x, int y) {
        istDrin = false;
        this.applet = applet;
        image = applet.loadImage("cool.jpg");
        imageX = x;
        imageY = y;
    }

    public void check(SimpleOpenNI context, int UserID, int jointID) {
        PVector joint = new PVector();
        float confidence = context.getJointPositionSkeleton(UserID, jointID, joint);
        if (confidence < 0.5) {
            return;
        }

        PVector convertedJoint = new PVector();
        context.convertRealWorldToProjective(joint, convertedJoint);

        if (convertedJoint.x > imageX - image.width / 2 && convertedJoint.x < imageX + image.width / 2) {
            if (convertedJoint.y > imageY - image.height / 2 && convertedJoint.y < imageY + image.height / 2) {
                istDrin = true;
            }
        }

        // Play
        if (istDrin()) {
            System.out.println("Hit");
        }
        reset();
    }

    public boolean istDrin() {
        return istDrin && !warDrin;
    }

    private void reset() {
        warDrin = istDrin;// currentlyHit();
        istDrin = false;
    }

    public void drawHotpoint() {
        applet.image(image, imageX, imageY);
    }

}
