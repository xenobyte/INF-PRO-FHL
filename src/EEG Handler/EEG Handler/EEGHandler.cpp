// EEG Handler.cpp : Definiert die exportierten Funktionen für die DLL-Anwendung.
//

#include "stdafx.h"
#include "EEGHandler.h"
#include "EpocData.h"

// Dies ist das Beispiel einer exportierten Variable.
EEGHANDLER_API int nEEGHandler=0;

// Dies ist das Beispiel einer exportierten Funktion.
EEGHANDLER_API bool connect(void)
{
	return 42;
}

EEGHANDLER_API EpocData getData();

// Dies ist der Konstruktor einer Klasse, die exportiert wurde.
// Siehe EEG Handler.h für die Klassendefinition.
CEEGHandler::CEEGHandler()
{
	return;
}
