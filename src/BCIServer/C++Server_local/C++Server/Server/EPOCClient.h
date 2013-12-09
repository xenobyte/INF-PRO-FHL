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
    int32_t counter;
    int32_t gyroX;
    int32_t gyroY;
    int32_t timeStamp;
    int32_t func_ID;
    int32_t func_Value;
    int32_t marker;
    int32_t sync_Signal;
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

