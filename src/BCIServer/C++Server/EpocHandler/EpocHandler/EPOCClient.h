#pragma once
#include <stdint.h>
#include "stdafx.h"
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
    EpocHandler * handler;
    double* ed_counter;
    double* ed_gyroX;
    double* ed_gyroY;
    double* ed_timeStamp;
    double* ed_func_id;
    double* ed_func_value;
    double* ed_marker;
    double* ed_sync_signal;
    double* ed_af3;
    double* ed_f7;
    double* ed_f3;
    double* ed_fc5;
    double* ed_t7;
    double* ed_p7;
    double* ed_o1;
    double* ed_o2;
    double* ed_p8;
    double* ed_t8;
    double* ed_fc6;
    double* ed_f4;
    double* ed_f8;
    double* ed_af4;
    int getDataFacialExpression(struct FACIALEXPRESSIONPACKAGE * package);
    int getDataEmoState(struct EMOSTATEPACKAGE * package);
    int getDataRawData(struct RAWDATAPACKAGE * package, int index);
    ~EPOCClient(void);
};

