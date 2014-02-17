#pragma once

#include "EPOCClient.h"
#include "stdafx.h"
#include <stdlib.h>
#include <winsock2.h>
#pragma comment( lib, "ws2_32.lib" )
#pragma comment( lib, "lib/edk.lib")

#define STDPORT 5000
#define DEBUG 1
#define MAXTHREADS 10

#define FACIALEXPRESSION 0
#define EMOSTATE 1
#define RAWDATA 2


struct REQUESTPACKAGE{
    char mode;
};

class Server{
public:
    Server(void);
    static void s(void);
    int start(void);
    ~Server(void);
private:
    

    SOCKET udpSocket;
    SOCKADDR_IN addr;
    SOCKADDR_IN remoteAddr;
    WSADATA wsaData;
    EPOCClient epoc;

    void respondFacialExpression();
    void respondEEGData();


};

