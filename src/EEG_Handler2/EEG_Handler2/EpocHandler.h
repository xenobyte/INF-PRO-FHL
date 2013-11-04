#include "EmoStateDLL.h"
#include "edk.h"
#include "edkErrorCode.h"
#include <map>

#pragma comment(lib, "lib/edk.lib")


class EPOC
{
public:
	EPOC();
	~EPOC();

	void updateData();
	bool getUpdate();
	std::map<EE_DataChannel_t, double*> getAllData();
	double* getChannelData;
	double* getChannelData(EE_DataChannel_t channel);
	double getEngagement();
	double getFrustration();
	double getMeditation();
	double getExcitment();
private:
	bool connect();
	bool disconnect();
};

