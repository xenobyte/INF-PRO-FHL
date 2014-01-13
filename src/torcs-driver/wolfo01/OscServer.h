/* 
 * File:   OscServer.h
 * Author: florian
 *
 * Created on November 23, 2013, 3:11 PM
 */

#ifndef OSCSERVER_H
#define	OSCSERVER_H

#include <iostream>
#include <cstdlib>
#include "lo/lo.h"	// LIBLO, an Open Sound Control library

class OscServer {
public:
    OscServer();
    ~OscServer();
    bool start(float *oscinfo);
private:
    static void error(int num, const char *msg, const char *path);
    static int gearControl(const char *path, const char *types, lo_arg ** argv,
            int argc, void *data, void *user_data);

    lo_server server;
};


#endif	/* OSCSERVER_H */

