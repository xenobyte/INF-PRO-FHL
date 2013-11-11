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
    char errorCode;
};

class EPOCClient
{
public:
    EPOCClient(void);
    int getDataFacialExpression(struct FACIALEXPRESSIONPACKAGE * package);
    int getDataEmoState(struct EMOSTATEPACKAGE * package);
    int getDataRawData(struct RAWDATAPACKAGE * package);
    ~EPOCClient(void);
};

