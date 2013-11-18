#include "EpocHandler.h"
#include "stdafx.h"



	static const EE_DataChannel_t dataChannels[] = {
		ED_COUNTER, ED_GYROX, ED_GYROY, ED_TIMESTAMP,
		ED_FUNC_ID, ED_FUNC_VALUE, ED_MARKER, ED_SYNC_SIGNAL,
		ED_AF3, ED_F7, ED_F3, ED_FC5, ED_T7,
		ED_P7, ED_O1, ED_O2, ED_P8, ED_T8,
		ED_FC6, ED_F4, ED_F8, ED_AF4
	};


//class EpocHandler{
	EmoEngineEventHandle eEvent;
	EmoStateHandle eState;
	static const unsigned short composerPort = 1726;
	unsigned int userID;
	float secs;
	unsigned int datarate;
	bool readytocollect;
	int state;
	bool connected;
	bool debug;

	//EEGDaten
	bool updated;
	std::map<EE_DataChannel_t, double*> eegDataMap;
	typedef std::map<EE_DataChannel_t, double*>::iterator it_type;
	unsigned int nSamplesTaken;
	double pEngagement;
	double pFrustration;
	double pMeditation;
	double pExcitement;




	EpocHandler::EpocHandler()
	{
		userID = 0;
		secs = 1;
		datarate = 0;
		readytocollect = false;
		state = 0;
		connected = false;
		updated = false;
		debug = false;
		eEvent = EE_EmoEngineEventCreate();
		eState = EE_EmoStateCreate();
	}

	EpocHandler::~EpocHandler()
	{
		//alte arrays löschen
		if(updated){
			for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); iterator++) {
				free(iterator->second);
			}

		}

	}

	bool EpocHandler::connect()
	{
		connected = EE_EngineConnect() == EDK_OK;
		std::cout << "Connected: " << connected << std::endl;
		return EE_EngineConnect() == EDK_OK;
	}

	bool EpocHandler::disconnect()
	{
		connected = EE_EngineDisconnect() == EDK_OK;
		return connected;
	}

	void EpocHandler::updateData()
	{
		if(connect()){
			std::cout << "Erfolgreich verbunden"  << std::endl;
		}else{
			std::cout << "Verbindung fehlgeschlagen"  << std::endl;
			exit(-1);
		}


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
			//alte arrays löschen
			if(updated){
				for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); iterator++) {
					free(iterator->second);
				}
				updated = false;
			}

			EE_DataUpdateHandle(0, hData);

			EE_DataGetNumberOfSample(hData, &nSamplesTaken);
			if (debug)
				std::cout << "Updated " << nSamplesTaken << std::endl;
			if (nSamplesTaken != 0) {
				pEngagement =  ES_AffectivGetEngagementBoredomScore(eState);
				pFrustration = ES_AffectivGetFrustrationScore(eState);
				pMeditation = ES_AffectivGetExcitementShortTermScore(eState);
				pExcitement = ES_AffectivGetExcitementShortTermScore(eState);

				//Map Speicher reservierren
				for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); iterator++) {
					iterator->second = (double*) malloc(nSamplesTaken);
				}
				//Map füllen
				for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); iterator++) {
					EE_DataGet(hData, iterator->first,eegDataMap[iterator->first], nSamplesTaken);
				} 
				updated = true;
			}

		}
		disconnect();
	};


	double getUpdated(){
		return updated;
	}

	std::map<EE_DataChannel_t, double*> getAllData(){
		return eegDataMap;
	}

	double* EpocHandler::getChannelData(EE_DataChannel_t channel){
		return eegDataMap[channel];
	}

	double EpocHandler::getEngagement(void){
		return pEngagement;
	}

	double EpocHandler::getFrustration(){
		return pFrustration;
	}

	double EpocHandler::getMeditation(){
		return pMeditation;
	}

	double EpocHandler::getExcitment(){
		return pExcitement;
	}
//};