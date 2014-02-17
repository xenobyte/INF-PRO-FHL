#include "EPOCClient.h"


EPOCClient::EPOCClient(void)
{
}

int EPOCClient::getDataFacialExpression(struct FACIALEXPRESSIONPACKAGE * package){
    return 0;
}

int EPOCClient::getDataEmoState(struct EMOSTATEPACKAGE * package){
    package->errorCode = 10;
    package->nSamplesTaken = 2;
    package->pEngagement = 1;
    package->pExcitement = 2;
    package->pFrustration = 3;
    package->pMeditation = 4;
    return 0;
}

int EPOCClient::getDataRawData(struct RAWDATAPACKAGE * package, int index){
    package->errorCode = 0x12345678;
    package->counter = 1;
    package->gyroX = 2;
    package->gyroY = 3;
    package->timeStamp = 4;
    package->func_ID = 5;
    package->func_Value = 6;
    package->marker = 7;
    package->sync_Signal = 8;
    package->AF3 = 9;
    package->F7 = 10;
    package->F3 = 11;
    package->FC5 = 12;
    package->T7 = 13;
    package->P7 = 14;
    package->O1 = 15;
    package->O2 = 16;
    package->P8 = 17;
    package->T8 = 18;
    package->FC6 = 19;
    package->F4 = 20;
    package->F8 = 21;
    package->AF4 = 22;
    return 0;
}

EPOCClient::~EPOCClient(void)
{
}
