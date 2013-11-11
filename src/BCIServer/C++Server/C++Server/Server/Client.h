#pragma once
#include <winsock2.h>
#include <stdio.h>
#include <stdlib.h>
#pragma comment( lib, "ws2_32.lib" )
#include <stdint.h>
#define STDPORT 5000
#define IP "127.0.0.1"
#define DEBUG 1
#define FACIALEXPRESSION 0
#define EMOSTATE 1
#define RAWDATA 2

struct REQUESTPACKAGE{
    char mode;
};

struct FACIALEXPRESSIONPACKAGE{
    char errorCode;
};

struct EMOSTATEPACKAGE{
    int32_t errorCode;
    int32_t nSamplesTaken;
	double pEngagement;
	double pFrustration;
	double pMeditation;
	double pExcitement;
};

struct RAWDATAPACKAGE{
    char errorCode;
};

class Client{
public:
    int getFacialExpression(struct FACIALEXPRESSIONPACKAGE * rPackage);
    int getEmoState(struct EMOSTATEPACKAGE * rPackage);
    int getRawData(struct RAWDATAPACKAGE * rPackage);
    Client(void);
    ~Client(void);
private:
    SOCKET udpSocket;
    SOCKADDR_IN addr;
    SOCKADDR_IN remoteAddr;
    int remoteAddrLen;
    WSADATA wsaData;
};

