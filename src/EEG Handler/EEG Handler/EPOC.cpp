#include "stdafx.h"
#include "EPOC.h"
#include "EpocData.h"
#include <iostream>
#include "EEGHandler.h"


EmoEngineEventHandle eEvent;
EmoStateHandle eState;
const unsigned short composerPort = 1726;
unsigned int userID = 0;
float secs = 1;
unsigned int datarate = 0;
bool readytocollect = false;
int option = 0;
int state = 0;
bool connected = false;
bool debug = true;
EE_DataChannel_t targetChannelListDouble[] = {	
	ED_AF3, ED_F7, ED_F3, ED_FC5, ED_T7,
	ED_P7, ED_O1, ED_O2, ED_P8, ED_T8,
	ED_FC6, ED_F4, ED_F8, ED_AF4
};
enum DoubleChannel {
	AF3, F7, F3, FC5, T7,
	P7, O1, O2, P8, T8,
	FC6, F4, F8, AF4
};
EE_DataChannel_t targetChannelListInt[] = {
	ED_COUNTER, ED_GYROX, ED_GYROY, ED_TIMESTAMP,
	ED_FUNC_ID, ED_FUNC_VALUE, ED_MARKER, ED_SYNC_SIGNAL
};
enum IntChannel {
	COUNTER, GYROX, GYROY, TIMESTAMP,
	FUNC_ID, FUNC_VALUE, MARKER, SYNC_SIGNAL
};


EPOC::EPOC()
{
	eEvent = EE_EmoEngineEventCreate();
	eState = EE_EmoStateCreate();
}

EPOC::~EPOC()
{
	std::printf("DESTROY!\n");
	std::cout << "foo!";
}

bool EPOC::connect()
{
	return EE_EngineConnect() == EDK_OK;
	
}
void EPOC::getData()
{
	DataHandle hData = EE_DataCreate();
	EE_DataSetBufferSizeInSec(secs);
	if (state = EE_EngineGetNextEvent(eEvent) == EDK_OK)
	{
		EE_Event_t eventType = EE_EmoEngineEventGetType(eEvent);
		EE_EmoEngineEventGetUserId(eEvent, &userID);

		// Log the EmoState if it has been updated
		if (eventType == EE_UserAdded) {
			std::cout << "User added";
			EE_DataAcquisitionEnable(userID, true);
			readytocollect = true;
		}
	}
	if (readytocollect)
	{
		EE_DataUpdateHandle(0, hData);

		unsigned int nSamplesTaken = 0;
		EE_DataGetNumberOfSample(hData, &nSamplesTaken);
		if (debug)
			std::cout << "Updated " << nSamplesTaken << std::endl;
		EpocData data = EpocData(nSamplesTaken);
		if (nSamplesTaken != 0) {
			int *intAry = (int*) malloc(nSamplesTaken);
			double *doubleAry = (double*) malloc(nSamplesTaken);
			/*
			ED_AF3, ED_F7, ED_F3, ED_FC5, ED_T7,
			ED_P7, ED_O1, ED_O2, ED_P8, ED_T8,
			ED_FC6, ED_F4, ED_F8, ED_AF4
			Counter, ED_GYROX, ED_GYROY, ED_TIMESTAMP,
			ED_FUNC_ID, ED_FUNC_VALUE, ED_MARKER, ED_SYNC_SIGNAL
			*/
			
			//TODO Array von Einträgen mit ENUMs nutzen
			//TODO ENUMs für die Channel erstellen.!
		
			for (int sampleIdx = 0; sampleIdx < (int)nSamplesTaken; ++sampleIdx) {

				for (int i = 0; i < sizeof(targetChannelListDouble) / sizeof(EE_DataChannel_t); i++) {
					//fetch an Array if nSamples for 1 Channel
					EE_DataGet(hData, targetChannelListDouble[i],doubleAry, nSamplesTaken);
				}
				//One Line completed!
			}
			data.setMeditation(ES_AffectivGetEngagementBoredomScore(eState));
			data.setEngagement(ES_AffectivGetFrustrationScore(eState));
			data.setFrustration(ES_AffectivGetMeditationScore(eState));
			data.setExcitement(ES_AffectivGetExcitementShortTermScore(eState));			
		}
		
	}
}