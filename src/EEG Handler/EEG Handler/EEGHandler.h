// Folgender ifdef-Block ist die Standardmethode zum Erstellen von Makros, die das Exportieren 
// aus einer DLL vereinfachen. Alle Dateien in dieser DLL werden mit dem EEGHANDLER_EXPORTS-Symbol
// (in der Befehlszeile definiert) kompiliert. Dieses Symbol darf für kein Projekt definiert werden,
// das diese DLL verwendet. Alle anderen Projekte, deren Quelldateien diese Datei beinhalten, erkennen 
// EEGHANDLER_API-Funktionen als aus einer DLL importiert, während die DLL
// mit diesem Makro definierte Symbole als exportiert ansieht.
#ifdef EEGHANDLER_EXPORTS
#define EEGHANDLER_API __declspec(dllexport)
#else
#define EEGHANDLER_API __declspec(dllimport)
#endif

// Diese Klasse wird aus EEG Handler.dll exportiert.
class EEGHANDLER_API CEEGHandler {
public:
	/**
	* contains one of engagement, frustration, meditation excitement and samplestaken.
	* contains arrays for the rest --> arraysize == samplesTaken
	*/
	/*struct eegData {
		int samplesTaken;
		double engagement, frustration, meditation, excitement;
		int	*ED_COUNTER, *ED_GYROX, *ED_GYROY, *ED_TIMESTAMP,
			*ED_FUNC_ID, *ED_FUNC_VALUE, *ED_MARKER, *ED_SYNC_SIGNAL;
		double	*ED_AF3, *ED_F7, *ED_F3, *ED_FC5, *ED_T7,
			*ED_P7, *ED_O1, *ED_O2, *ED_P8, *ED_T8,
			*ED_FC6, *ED_F4, *ED_F8, *ED_AF4;
	};*/
	bool connect(void);
	EpocData getData();
};

extern EEGHANDLER_API int nEEGHandler;

EEGHANDLER_API int fnEEGHandler(void);
