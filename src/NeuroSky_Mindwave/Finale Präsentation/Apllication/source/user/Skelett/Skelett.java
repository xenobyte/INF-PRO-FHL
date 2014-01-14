package user.Skelett;

import SimpleOpenNI.SimpleOpenNI;



public enum Skelett {
    
    KOPF,
    HALS,
    TORSO,
    LINKS_SCHULTER,
    LINKS_ELLBOGEN,
    LINKS_HAND,
    LINKS_HUEFTE,
    LINKS_KNIE,
    LINKS_FUSS,
    RECHTS_SCHULTER,
    RECHTS_ELLBOGEN,
    RECHTS_HAND,
    RECHTS_HUEFTE,
    RECHTS_KNIE,
    RECHTS_FUSS;       

    public static int get(Skelett joint) {
        switch (joint) {
      case KOPF:
          return SimpleOpenNI.SKEL_HEAD;
      case HALS:
          return SimpleOpenNI.SKEL_NECK;  
      case TORSO:
          return SimpleOpenNI.SKEL_TORSO;  
      case LINKS_SCHULTER:
          return SimpleOpenNI.SKEL_LEFT_SHOULDER;  
      case LINKS_ELLBOGEN:
          return SimpleOpenNI.SKEL_LEFT_ELBOW;  
      case LINKS_HAND:
          return SimpleOpenNI.SKEL_LEFT_HAND;  
      case LINKS_HUEFTE:
          return SimpleOpenNI.SKEL_LEFT_HIP;  
      case LINKS_KNIE:
          return SimpleOpenNI.SKEL_LEFT_KNEE;  
      case LINKS_FUSS:
          return SimpleOpenNI.SKEL_LEFT_FOOT;       
      case RECHTS_SCHULTER:
          return SimpleOpenNI.SKEL_RIGHT_SHOULDER;  
      case RECHTS_ELLBOGEN:
          return SimpleOpenNI.SKEL_RIGHT_ELBOW;  
      case RECHTS_HAND:
          return SimpleOpenNI.SKEL_RIGHT_HAND;  
      case RECHTS_HUEFTE:
          return SimpleOpenNI.SKEL_RIGHT_HIP;  
      case RECHTS_KNIE:
          return SimpleOpenNI.SKEL_RIGHT_KNEE;  
      case RECHTS_FUSS:
          return SimpleOpenNI.SKEL_RIGHT_FOOT;  
      default:
          break;
      }
        return -1;
    }
}



