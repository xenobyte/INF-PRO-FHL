package user.Stellung;

import processing.core.PVector;
import user.Skelett.Position;
import SimpleOpenNI.SimpleOpenNI;

/**
 * Klassenname: Regel
 * 
 * Zweck: Prüfung der Position von zwei verschieden Joints
 * 
 * Ablauf: mit der Instanziierung wird eine Regel erstellt
 *       : mit check wird die Regel geprüft
 * 
 * @author M.Kemel Koumenji
 * @version 1.0 14/11/13
 */
public class Regel {
    
    private int joint_1;
    private int Joint_2;
    private PVector v_Joint_1;
    private PVector v_Joint_2;
    private SimpleOpenNI context;
    private Position position;

    
    /**
     * Konstruktor zur Erstellung einer Regel
     * @param context 
     * @param joint_1 
     * @param position
     * @param joint_2
     */
    public Regel(SimpleOpenNI context, int joint_1, Position position, int joint_2) {
        this.context = context;
        this.joint_1 = joint_1;
        this.Joint_2 = joint_2;
        this.position = position;
        v_Joint_1 = new PVector();
        v_Joint_2 = new PVector();
    }

    /**
     * Diese Methode prüft ob die joint Positionen der Lage entsprechen.
     * 
     * @param userID
     * @return true wenn die Regel erfüllt wurde
     *         false sonst
     */
    public boolean check(int userID) {
        boolean ret = false;

        // Hole die Aktuellen Joint Postionen
        context.getJointPositionSkeleton(userID, joint_1, v_Joint_1);
        context.getJointPositionSkeleton(userID, Joint_2, v_Joint_2);

        // joint coordinaten vergleichen
        switch (position) {
        case UEBER:
            ret = (v_Joint_1.y > v_Joint_2.y+20);
            break;
        case UNTER:
            ret = (v_Joint_1.y < v_Joint_2.y-20);
            break;
        case LINKS_VON:
            ret = (v_Joint_1.x < v_Joint_2.x);
            break;
        case RECHTS_VON:
            ret = (v_Joint_1.x > v_Joint_2.x);
            break;
        default:
            break;
        }
        return ret;
    }
}