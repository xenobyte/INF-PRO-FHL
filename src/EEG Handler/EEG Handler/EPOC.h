#include "EmoStateDLL.h"
#include "edk.h"
#include "edkErrorCode.h"


#pragma comment(lib, "lib/edk.lib")


class EPOC
{
public:

	EPOC();
	~EPOC();
	bool connect();
	void getData();
};

