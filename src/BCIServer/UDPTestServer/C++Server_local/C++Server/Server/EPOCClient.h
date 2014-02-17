#pragma once
#include <stdint.h>
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
    int32_t errorCode;
    double counter;
    double gyroX;
    double gyroY;
    double timeStamp;
    double func_ID;
    double func_Value;
    double marker;
    double sync_Signal;
    double AF3;
    double F7;
    double F3;
    double FC5;
    double T7;
    double P7;
    double O1;
    double O2;
    double P8;
    double T8;
    double FC6;
    double F4;
    double F8;
    double AF4;
};

class EPOCClient
{
public:
    EPOCClient(void);
    int getDataFacialExpression(struct FACIALEXPRESSIONPACKAGE * package);
    int getDataEmoState(struct EMOSTATEPACKAGE * package);
    int getDataRawData(struct RAWDATAPACKAGE * package, int index);
    ~EPOCClient(void);
};

