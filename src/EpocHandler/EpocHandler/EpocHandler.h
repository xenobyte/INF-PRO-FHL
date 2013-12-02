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

	int updateData();
	std::map<EE_DataChannel_t, double*> getAllData();
	double* getChannelData(EE_DataChannel_t channel);
	double getEngagement();
	double getFrustration();
	double getMeditation();
	double getExcitment();

private:
	bool connect();
	bool disconnect();
};

