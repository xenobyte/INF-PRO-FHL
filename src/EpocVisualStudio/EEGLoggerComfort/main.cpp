/*******************************************************************************
 * EmologgerComfort.cpp
 * Version: 0.2
 *******************************************************************************
 * Eine Variante des EEGLoggers (mitgeliefertes Example 5) fuer den
 * alltaeglichen Datenerfassungs-Gebrauch.
 *
 * Aenderungen:
 * 1. Keine Auswahl zwischen EmoEngine und EmoComposer. Beim Sammeln echter
 *	  Daten (von der EmoEngine) bremste diese Eingabeaufforderung nur den
 *    Workflow.
 *    -> Aenderung ist implementiert.
 * 2. Automatisches Einfuegen eines Timestamps (Datum, Uhrzeit) in den
 *	  Dateinamen.
 *    -> ist implementiert.
 * 3. Zwei zusaetzliche csv-Spalten fuer die Werte der "Cognitiv Suite"
 *    (Cognitiv Action, Cognitiv Power)
 *	  -> ist implementiert (seit Version 0.2).
 *******************************************************************************
 * Versionshistorie:
 * v0.1: erste Implementation (Aenderungen 1 & 2) 
 * v0.2: csv-Spalten fuer "Cognitiv Suite", Dateinamen jetzt .eeg (v0.1: .txt)
 ******************************************************************************/


#include <iostream>
#include <fstream>
#include <conio.h>
#include <sstream>
#include <windows.h>
#include <map>

#include "EmoStateDLL.h"
#include "edk.h"
#include "edkErrorCode.h"

#pragma comment(lib, "../lib/edk.lib")

EE_DataChannel_t targetChannelList[] = {
		ED_COUNTER,
		ED_AF3, ED_F7, ED_F3, ED_FC5, ED_T7, 
		ED_P7, ED_O1, ED_O2, ED_P8, ED_T8, 
		ED_FC6, ED_F4, ED_F8, ED_AF4, ED_GYROX, ED_GYROY, ED_TIMESTAMP, 
		ED_FUNC_ID, ED_FUNC_VALUE, ED_MARKER, ED_SYNC_SIGNAL
	};

const char header[] = "COUNTER,AF3,F7,F3, FC5, T7, P7, O1, O2,P8" 
                      ", T8, FC6, F4,F8, AF4,GYROX, GYROY, TIMESTAMP, "   
                      "FUNC_ID, FUNC_VALUE, MARKER, SYNC_SIGNAL, "
                      "Cognitiv Action, Cognitiv Power,";

int main(int argc, char** argv) {

	EmoEngineEventHandle eEvent			= EE_EmoEngineEventCreate();
	EmoStateHandle eState				= EE_EmoStateCreate();
	unsigned int userID					= 0;
	const unsigned short composerPort	= 1726;
	float secs							= 1;
	unsigned int datarate				= 0;
	bool readytocollect					= false;
	int option							= 0;
	int state							= 0;


	std::string input;

	try {
        if (argc != 2) {
            throw std::exception("Please supply the log file name.\nUsage: EEGLoggerComfort [log_file_theme].");
        }

        if (EE_EngineConnect() != EDK_OK) {
            throw std::exception("Emotiv Engine start up failed.");
        }
		

        // auto-generation of filename with timestamp:
        SYSTEMTIME lt;
        GetLocalTime(&lt);

        int filenameLength = strlen(argv[1]) + strlen("_YYYYMMDD_HHMMSS.eeg") + 1;
        char * filename = new char[filenameLength];

        int rv = sprintf_s(filename, filenameLength, "%s_%04d%02d%02d_%02d%02d%02d.eeg", argv[1], lt.wYear, lt.wMonth, lt.wDay,
            lt.wHour, lt.wMinute, lt.wSecond);
        if (rv < 0){
            throw std::exception("Generation of filename failed.");
        }


		std::cout << "Start receiving EEG Data! Press any key to stop logging...\n" << std::endl;
        std::ofstream ofs(filename, std::ios::trunc);
		ofs << header << std::endl;

        delete filename;
		
		DataHandle hData = EE_DataCreate();
		EE_DataSetBufferSizeInSec(secs);

		std::cout << "Buffer size in secs:" << secs << std::endl;

		while (!_kbhit()) {

			state = EE_EngineGetNextEvent(eEvent);

			if (state == EDK_OK) {

				EE_Event_t eventType = EE_EmoEngineEventGetType(eEvent);
				EE_EmoEngineEventGetUserId(eEvent, &userID);

				// Log the EmoState if it has been updated
				if (eventType == EE_UserAdded) {
					std::cout << "User added";
					EE_DataAcquisitionEnable(userID,true);
					readytocollect = true;
				}
			

				if (readytocollect && (eventType == EE_EmoStateUpdated)) {
							
					EE_DataUpdateHandle(0, hData);

					unsigned int nSamplesTaken=0;
					EE_DataGetNumberOfSample(hData,&nSamplesTaken);
			
					std::cout << "Updated " << nSamplesTaken << std::endl;
	
					if (nSamplesTaken != 0) {
	
						double* data = new double[nSamplesTaken];
						for (int sampleIdx=0 ; sampleIdx<(int)nSamplesTaken ; ++ sampleIdx) {
							for (int i = 0 ; i<sizeof(targetChannelList)/sizeof(EE_DataChannel_t) ; i++) {
	
								EE_DataGet(hData, targetChannelList[i], data, nSamplesTaken);
								ofs << data[sampleIdx] << ",";
							}
	
							// Cognitiv Suite results:
							ofs << static_cast<int>(ES_CognitivGetCurrentAction(eState)) << ",";
							ofs << ES_CognitivGetCurrentActionPower(eState) << ",";
							ofs << std::endl;
						}
						delete[] data;
					}

				}
			}
			Sleep(100);
		}

		ofs.close();
		EE_DataFree(hData);

	}
	catch (const std::exception& e) {
		std::cerr << e.what() << std::endl;
		std::cout << "Press any key to exit..." << std::endl;
		getchar();
	}

	EE_EngineDisconnect();
	EE_EmoStateFree(eState);
	EE_EmoEngineEventFree(eEvent);

	return 0;
}


