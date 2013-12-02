#include "EpocHandler.h"
#include "stdafx.h"
#include "windows.h"


//TODO facial expressions & boolean ruckgabewert bei update data
static const EE_DataChannel_t mydataChannels[22] = {
	ED_COUNTER, ED_GYROX, ED_GYROY, ED_TIMESTAMP,
	ED_FUNC_ID, ED_FUNC_VALUE, ED_MARKER, ED_SYNC_SIGNAL,
	ED_AF3, ED_F7, ED_F3, ED_FC5, ED_T7,
	ED_P7, ED_O1, ED_O2, ED_P8, ED_T8,
	ED_FC6, ED_F4, ED_F8, ED_AF4
};


EmoEngineEventHandle eEvent;
EmoStateHandle eState;
static const unsigned short composerPort = 1726;
unsigned int userID;
float secs;
unsigned int datarate;
bool readytocollect;
int state;
bool connected;
//Set to True for more output
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
bool useradded;



EpocHandler::EpocHandler()
{
	useradded = false;
	userID = 0;
	secs = 1;
	datarate = 0;
	readytocollect = false;
	state = 0;
	connected = false;
	updated = false;
	debug = true;
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

// Stellt nicht sicher ob eine Verbindung tatsächlich vorhanden ist.
bool EpocHandler::connect()
{
	connected = EE_EngineConnect() == EDK_OK;
	std::cout << "Connected: " << connected << std::endl;
	return EE_EngineConnect() == EDK_OK;
}

bool EpocHandler::disconnect()
{
	connected = EE_EngineDisconnect() == EDK_OK;
	useradded = false;
	return connected;
}

int EpocHandler::updateData()
{
	if(debug)
		std::cout << "1" <<std::endl;
	if(!connected){
		useradded = false;
		if(connect())
		{
			if(debug)
				std::cout << "Successfully connected" <<std::endl;
		}else
		{
			if(debug)
				std::cout << "Did not Connect Properly" << std::endl;
		}
	}else{

	}
	if(debug)
		std::cout << "2" <<std::endl;
	DataHandle hData = EE_DataCreate();
	EE_DataSetBufferSizeInSec(secs);
	while(!useradded){
		if (state = EE_EngineGetNextEvent(eEvent) == EDK_OK)
		{
			EE_Event_t eventType = EE_EmoEngineEventGetType(eEvent);
			EE_EmoEngineEventGetUserId(eEvent, &userID);
			EE_EmoEngineEventGetEmoState(eEvent, eState);
			// Log the EmoState if it has been updated
			if (eventType == EE_UserAdded) {
				if(debug)
					std::cout << "User added" << std::endl;
				useradded = true;
				EE_DataAcquisitionEnable(userID, true);
				readytocollect = true;
			}
		}
	}

	if (readytocollect)
	{
		if(debug)
			std::cout << "3" <<std::endl;
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
			if(debug)
				std::cout << "EmoState read " <<std::endl;
			pEngagement =  ES_AffectivGetEngagementBoredomScore(eState);
			pFrustration = ES_AffectivGetFrustrationScore(eState);
			pMeditation = ES_AffectivGetExcitementShortTermScore(eState);
			pExcitement = ES_AffectivGetExcitementShortTermScore(eState);
			if(debug)
				std::cout << "EmoState read " << pEngagement << ", " << pFrustration << ", " << pMeditation<<std::endl;
			//Map Speicher reservieren

			//for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); iterator++) {
			std::cout << "datachannels size " << sizeof(mydataChannels)/sizeof(mydataChannels[0]) << std::endl;
			for(int i = 0; i < sizeof(mydataChannels)/sizeof(mydataChannels[0]); ++i){
				if(debug)
					std::cout << "channel int " <<mydataChannels[i] << std::endl;
				eegDataMap[mydataChannels[i]] = (double*) malloc(nSamplesTaken);

			}
			// i = j++; 
			// i = ++j;
			if(debug)
				std::cout << "Map Size : "<< eegDataMap.size() <<std::endl;
			//Map füllen
			for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); ++iterator) {
				if(debug)
					std::cout << "check!\t";
				EE_DataGet(hData, iterator->first,iterator->second, nSamplesTaken);
				if(debug)
					std::cout << "It first " <<iterator->first << " it second " << iterator->second[0] << std::endl;

			} 
			if(debug)
				std::cout << "4" <<std::endl;
			updated = true;
			if(debug)
				std::cout << "sizeof Map: " << eegDataMap.size() <<std::endl;

		}

	}
	//disconnect();
	return nSamplesTaken;
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
