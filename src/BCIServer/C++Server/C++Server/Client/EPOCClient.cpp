#include "EPOCClient.h"


EPOCClient::EPOCClient(void)
{
}

int EPOCClient::getDataFacialExpression(struct FACIALEXPRESSIONPACKAGE * package){
    return 0;
}

int EPOCClient::getDataEmoState(struct EMOSTATEPACKAGE * package){
    package->errorCode = 10;
    package->nSamplesTaken = 1;
    package->pEngagement = 2;
    package->pExcitement = 5;
    package->pFrustration = 3;
    package->pMeditation = 4;
    return 0;
}

int EPOCClient::getDataRawData(struct RAWDATAPACKAGE * package){
    return 0;
}

EPOCClient::~EPOCClient(void)
{
}
