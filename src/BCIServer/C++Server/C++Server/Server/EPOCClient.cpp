#include "EPOCClient.h"


EPOCClient::EPOCClient(void)
{
}

int EPOCClient::getDataFacialExpression(struct FACIALEXPRESSIONPACKAGE * package){
    return 0;
}

int EPOCClient::getDataEmoState(struct EMOSTATEPACKAGE * package){
    handler.updateData();
    package->errorCode = 10;
    package->nSamplesTaken = 2;
    package->pEngagement = handler.getEngagement();
    package->pExcitement = handler.getEcitement();
    package->pFrustration = handler.getFrustration();
    package->pMeditation = handler.getMeditation();
    if(ed_counter != nullptr){
        delete(ed_counter);
        delete(ed_gyroX);
        delete(ed_gyroY);
        delete(ed_timeStamp);
        delete(ed_func_id);
        delete(ed_func_value);
        delete(ed_marker);
        delete(ed_sync_signal);
        delete(ed_af3);
        delete(ed_f7);
        delete(ed_f3);
        delete(ed_fc5);
        delete(ed_t7);
        delete(ed_p7);
        delete(ed_o1);
        delete(ed_o2);
        delete(ed_p8);
        delete(ed_t8);
        delete(ed_fc6);
        delete(ed_f4);
        delete(ed_f8);
        delete(ed_af4);
    }
    ed_counter = handler.getChannelData(ED_COUNTER);
    ed_gyroX = handler.getChannelData(ED_GYROX);
    ed_gyroY = handler.getChannelData(ED_GYROY);
    ed_timeStamp = handler.getChannelData(ED_TIMESTAMP);
    ed_func_id = handler.getChannelData(ED_FUNC_ID);
    ed_func_value = handler.getChannelData(ED_FUNC_VALUE);
    ed_marker = handler.getChannelData(ED_MARKER);
    ed_sync_signal = handler.getChannelData(ED_SYNC_SIGNAL);
    ed_af3 = handler.getChannelData(ED_AF3);
    ed_f7 = handler.getChannelData(ED_F7);
    ed_f3 = handler.getChannelData(ED_F3);
    ed_fc5 = handler.getChannelData(ED_FC5);
    ed_t7 = handler.getChannelData(ED_T7);
    ed_p7 = handler.getChannelData(ED_P7);
    ed_o1 = handler.getChannelData(ED_O1);
    ed_o2 = handler.getChannelData(ED_O2);
    ed_p8 = handler.getChannelData(ED_P8);
    ed_t8 = handler.getChannelData(ED_T8);
    ed_fc6 = handler.getChannelData(ED_FC6);
    ed_f4 = handler.getChannelData(ED_F4);
    ed_f8 = handler.getChannelData(ED_F8);
    ed_af4 = handler.getChannelData(ED_AF4);
    
    return 0;
}

int EPOCClient::getDataRawData(struct RAWDATAPACKAGE * package, int index){
    package->errorCode = 0;
    package->counter = (int) ed_counter[index];
    package->gyroX = (int) ed_gyroX[index];
    package->gyroY = (int) ed_gyroY[index];
    package->timeStamp = (int) ed_timeStamp[index];
    package->func_ID = (int) ed_func_id[index];
    package->func_Value = (int) ed_func_value[index];
    package->marker = (int) ed_marker[index];
    package->sync_Signal = (int) ed_sync_signal[index];
    package->AF3 = ed_af3[index];
    package->F7 = ed_f7[index];
    package->F3 = ed_f3[index];
    package->FC5 = ed_fc5[index];
    package->T7 = ed_t7[index];
    package->P7 = ed_p7[index];
    package->O1 = ed_o1[index];
    package->O2 = ed_o2[index];
    package->P8 = ed_p8[index];
    package->T8 = ed_t8[index];
    package->FC6 = ed_fc6[index];
    package->F4 = ed_f4[index];
    package->F8 = ed_f8[index];
    package->AF4 = ed_af4[index];
    return 0;
}

EPOCClient::~EPOCClient(void)
{
}
