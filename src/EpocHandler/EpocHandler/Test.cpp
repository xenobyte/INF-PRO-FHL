// EpocHandler.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"


#pragma comment(lib, "lib/edk.lib")

int _tmain(int argc, _TCHAR* argv[])
{
	EpocHandler a;
	while (!a.updateData());
	//std::cout << "Test..." << std::endl;
	std::cout << a.getChannelData(EE_DataChannel_t::ED_GYROX) << std::endl;
	double *doubleArrayForSensorAF3 = a.getChannelData(EE_DataChannel_t::ED_AF3);


	return 0;
}

