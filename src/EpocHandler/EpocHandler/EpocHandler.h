#include "EmoStateDLL.h"
#include "edk.h"
#include "edkErrorCode.h"
#include <map>
#include "stdafx.h"




class EpocHandler
{
public:
	EpocHandler();
	~EpocHandler();
	
	/**
	* Method which connects to the EPOC EEG Headset, and reads its Buffer
	* Note that usually the first call of this Method will get 0 results
	* Note if you call this method, all old Data will be forgotten
	* @return returns the number of Samples which are retrieved from the headset
	*/
	int updateData();
	/**
	* returns a Map which contains all raw data that was retrieved by updateData()
	* @see EpocHandler::updateData()
	* @return a map with {EE_DataChannel_t => double*} the number of entries
	* in each double array is equal to the returnvalue of updateData()
	*/
	std::map<EE_DataChannel_t, double*> getAllData();
	/**
	* returns the raw ChannelData according to the given EE_DataChannel_t
	* @return double[] the number of entries
	* in each double array is equal to the returnvalue of updateData()
	*/
	double* getChannelData(EE_DataChannel_t channel);
	/**
	* returns a single double value representing the engagement value of the 
	* last read number of Samples, that were read
	*/
	double getEngagement();
	/**
	* returns a single double value representing the frustration value of the 
	* last read number of Samples, that were read
	*/
	double getFrustration();
	/**
	* returns a single double value representing the meditation value of the 
	* last read number of Samples, that were read
	*/
	double getMeditation();
	/**
	* returns a single double value representing the excitement value of the 
	* last read number of Samples, that were read
	*/
	double getExcitment();

private:
	/**
	* Establishes a Connection to the EPOC HEadset
	* NOTE this does NOT assure you have a successful connection.
	* in fact this returns true even when there is no Headset present
	* this is due to a bug/error in the EPOC API built-in connect method!
	*/
	bool connect();
	
	/**
	* Disconnects the EPOC EEG Headset
	*/
	bool disconnect();
};

