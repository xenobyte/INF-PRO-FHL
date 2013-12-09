// EpocHandler.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include "windows.h"
#include <map>


#pragma comment(lib, "lib/edk.lib")

int _tmain(int argc, _TCHAR* argv[])
{
	EpocHandler a;
	while (!a.updateData())
		Sleep(100);
	//std::cout << "Test..." << std::endl;
	//double* gyrox = a.getChannelData(EE_DataChannel_t::ED_COUNTER);
	//double b = gyrox[0];
	/*std::cout << sizeof(a.getAllData()) << std::endl;
	std::cout << a.getFrustration() << std::endl;
	std::cout << a.getEngagement() << std::endl;*/
//	double *doubleArrayForSensorAF3 = a.getChannelData(EE_DataChannel_t::ED_AF3);
	std::cout << "ENDE !!";

	return 0;
}

