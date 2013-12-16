// EpocHandler.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include "windows.h"
#include <map>


#pragma comment(lib, "lib/edk.lib")

int _tmain(int argc, _TCHAR* argv[])
{
	EpocHandler a;
	int sampleCount = 0;
	while (!(sampleCount = a.updateData()))
	{
		Sleep(100);
		std::cout << "Fetching Data again" << std::endl;
	}
	//std::cout << "Test..." << std::endl;
	double* gyrox = a.getChannelData(EE_DataChannel_t::ED_COUNTER);
	double b = gyrox[0];
	std::cout << a.getFrustration() << std::endl;
	std::cout << a.getEngagement() << std::endl << a.getExcitment() << std::endl << a.getMeditation() << std::endl;
	std::cout << std::endl << "ENDE !!";
	std::getchar();
	return 0;
}

