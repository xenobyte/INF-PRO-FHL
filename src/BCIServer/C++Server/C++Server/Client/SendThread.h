#pragma once
#pragma comment( lib, "ws2_32.lib" )

#include "EPOCClient.h"
#include <winsock2.h>
#include <stdio.h>
#include <stdlib.h>
#include <vector>
#include <process.h>

struct EVENT{
    int level;
    int action;
    int op;
};

class SendThread{
public:
    std::vector<EVENT> events;
    int frequenz;
    int isRunning;
    int id;

    SendThread(void);
    SendThread(SOCKADDR_IN addr, int id_l);
    ~SendThread(void);

    void start(SendThread t);
    static unsigned int __stdcall running(LPVOID pVoid);

private:
    SOCKADDR_IN remoteAddr;
    SOCKET udpSocket;


};

