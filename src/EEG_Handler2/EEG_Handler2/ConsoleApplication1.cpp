// ConsoleApplication1.cpp : Definiert den Einstiegspunkt für die Konsolenanwendung.
//

#include "stdafx.h"
#include "EpocHandler.h"
#include <iostream>

int _tmain(int argc, _TCHAR* argv[])
{
	EpocHandler a = EpocHandler();
	a.updateData();
	std::cout << "Press any key to exit..." << std::endl;
	std::cout << a.getEngagement();
	return 0;
}

