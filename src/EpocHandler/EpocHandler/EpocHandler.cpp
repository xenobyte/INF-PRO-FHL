#include "EpocHandler.h"
#include "stdafx.h"
#include "windows.h"

bool useradded;


//TODO facial expressions

/**
* This contains all raw data channels we are reading.
* Note that ED_COUNTER, ED_FUNC_ID, ED_FUNC_VALUE ED_SYNC_SIGNAL, ED_MARKER will read int values from the headset
* The function of ED_FUNC_ID, ED_FUNC_VALUE ED_SYNC_SIGNAL, ED_MARKER is not known
*/
static const EE_DataChannel_t dataChannels[] = {
	ED_COUNTER, ED_GYROX, ED_GYROY, ED_TIMESTAMP,
	ED_FUNC_ID, ED_FUNC_VALUE, ED_MARKER, ED_SYNC_SIGNAL,
	ED_AF3, ED_F7, ED_F3, ED_FC5, ED_T7,
	ED_P7, ED_O1, ED_O2, ED_P8, ED_T8,
	ED_FC6, ED_F4, ED_F8, ED_AF4
};


//an Event instance which we need to read data
EmoEngineEventHandle eEvent;
//An EmoStateHandle which we need to retrieve the EmoState e.g. meditation
EmoStateHandle eState;
/**
* We need the userID to link the measured data to the current user.
* This will be for sure important when using the Cognitive Suite
*/
unsigned int userID;
//Determines the Buffersize of the Headset in Seconds (128*secs) samples
float secs;
//Not used, maybe remove 
//TODO Check it if can be removed
unsigned int datarate;
//Flag to see if all preconditions are met to collec data
bool readytocollect;
//used to save the statusCode (statecode) of the read state
int state;
//Flag to see if we are connected
bool connected;
//In Debugmode there is more output
const bool debug= true;

//Flag to see if there is Data to be collected
bool updated;
//Datastructe which holds the rawData read form the headset
std::map<EE_DataChannel_t, double*> eegDataMap;
//An Iterator to iterate over our Datamap
typedef std::map<EE_DataChannel_t, double*>::iterator it_type;
//the number of Samples that are read from the headset
unsigned int nSamplesTaken;
//the Value of engagement
double pEngagement;
//the Value of frustration
double pFrustration;
//the Value of meditation
double pMeditation;
//the Value of excitement
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
	eEvent = EE_EmoEngineEventCreate();
	eState = EE_EmoStateCreate();
}

EpocHandler::~EpocHandler()
{
	//alte arrays löschen
	//Delete all old arrays from map
	if(updated){
		for(it_type iterator = eegDataMap.begin(); iterator != eegDataMap.end(); iterator++) {
			free(iterator->second);
		}


	}
	//maybe disconnect

}



bool EpocHandler::disconnect()
{
	connected = EE_EngineDisconnect() == EDK_OK;
	useradded = false;
	return connected;
}

// Stellt nicht sicher ob eine Verbindung tatsächlich vorhanden ist.
bool EpocHandler::connect()
{
	//EE_EngineConnect() always returns EDK_OK regardles if there
	// is a Headset Connected to the PC
	connected = (EE_EngineConnect() == EDK_OK);
	if(debug)
		std::cout << "Connected: " << connected << std::endl;
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
				std::cout << "datachannels size " << sizeof(dataChannels)/sizeof(dataChannels[0]) << std::endl;
				for(int i = 0; i < sizeof(dataChannels)/sizeof(dataChannels[0]); ++i){
					if(debug)
						std::cout << "channel int " <<dataChannels[i] << std::endl;
					eegDataMap[dataChannels[i]] = (double*) malloc(nSamplesTaken);

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
	}
	return nSamplesTaken;
}


//This should be removed, we dont want go give away a Reference for our Datastructure
//Or it should create a deep Copy!
std::map<EE_DataChannel_t, double*> getAllData(){
	return eegDataMap;
}

//Here EE_DataChannel should be changed CONSTs from our Class, for less dependencies
double* EpocHandler::getChannelData(EE_DataChannel_t channel){
	return eegDataMap[channel];
}

double EpocHandler::getEngagement(){
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
