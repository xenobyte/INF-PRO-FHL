/* ************************************************************

   File		: driver.h
   created	: 2010-05-30
   copyright	: (C) 2002 Bernhard Wymann
   ************************************************************ */


/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 ***************************************************************************/

#ifndef _DRIVER_H_
#define _DRIVER_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

#include <tgf.h>
#include <track.h>
#include <car.h>
#include <raceman.h>
#include <robottools.h>
#include <robot.h>
#include <pthread.h>

#include "linalg.h"
#include "opponent.h"
#include "pit.h"
#include "lo/lo.h"
#include "OscServer.h"

#define WOLFO_SECT_PRIV "wolfo01 private"
#define WOLFO_ATT_FUELPERLAP "fuelperlap"
#define WOLFO_ATT_ACCBOOST "accelerator boost"

class Opponents;
class Opponent;
class Pit;
void* driver_comm(void* arg);
int gearControl(const char *path, const char *types, lo_arg ** argv, int argc, void *data, void *user_data);
void error(int num, const char *msg, const char *path);

class Driver {
  public:
   Driver(int index);
   ~Driver();

   /* callback functions called from TORCS */
   void initTrack(tTrack* t, void *carHandle,
		  void **carParmHandle, tSituation *s);
   void newRace(tCarElt* car, tSituation *s);
   void drive(tSituation *s);
   int pitCommand(tSituation *s);
   void endRace(tSituation *s);
   tCarElt *getCarPtr() { return car; }
   tTrack *getTrackPtr() { return track; }
   float getSpeed() { return speed; }
   float getSteer();
   v2d getTargetPoint();   
   void *printHello(void);
   int oscinfo;

  private:
   /* utility functions */
   bool isStuck();
   void update(tSituation *s);
   void initCa();
   void initCw();
   float getAllowedSpeed(tTrackSeg *segment);
   float getDistToSegEnd();
   float getAccel();
   float filterABS(float brake);
   float getBrake();
   int getGear();
   float filterTCL(float accel);
   float filterTCL_RWD();
   float filterTCL_FWD();
   float filterTCL_4WD();
   void initTCLfilter();
   float filterTrk(float accel);
   float (Driver::*GET_DRIVEN_WHEEL_SPEED)();
   float filterBColl(float brake);
   float filterSColl(float steer);
   float getOvertakeOffset();
   float brakedist(float allowedspeed, float mu);
   float filterBPit(float brake);
   OscServer oscServer;

   /* per robot global data */
   int stuck;
   float trackangle;
   float angle;
   float   mass;        /* mass of car + fuel */
   tCarElt *car;      /* pointer to tCarElt struct */
   float CARMASS;     /* mass of the car only */
   float CA;          /* aerodynamic downforce coefficient */
   float CW;          /* aerodynamic drag coefficient */
   float speed;    /* speed in track direction */
   Opponents *opponents;
   Opponent *opponent;
   float myoffset;  /* overtake offset sideways */
   Pit *pit;
   float currentspeedsqr;
   float accel_boost;   
   int accel_boost_override;

   /* data that should stay constant after first initialization */
   int MAX_UNSTUCK_COUNT;
   int INDEX;
   
   /*OCS Server*/
   pthread_t t;
   int done; /* deaktiviert den OSC Server*/

   /* class constants */
   static const float MAX_UNSTUCK_ANGLE;
   static const float UNSTUCK_TIME_LIMIT;
   static const float MAX_UNSTUCK_SPEED;
   static const float MIN_UNSTUCK_DIST;
   static const float G;
   static const float FULL_ACCEL_MARGIN;
   static const float SHIFT;
   static const float SHIFT_MARGIN;
   static const float ABS_SLIP;
   static const float ABS_MINSPEED;
   static const float TCL_SLIP;
   static const float TCL_MINSPEED;
   static const float WIDTHDIV;
   static const float LOOKAHEAD_CONST;
   static const float LOOKAHEAD_FACTOR;
   static const float SIDECOLL_MARGIN;
   static const float SIDECOLL_ATT;
   static const float BORDER_OVERTAKE_MARGIN;
   static const float OVERTAKE_OFFSET_INC;
   static const float PIT_LOOKAHEAD;
   static const float PIT_BRAKE_AHEAD;
   static const float PIT_MU;
   static const float FUEL_PER_KM;

   /* track variables */
   tTrack* track;
};	

#endif // _DRIVER_H_
