#include "Server.h"
#include "stdafx.h"
Server::Server(void){
}

int Server::start(void){
    int rc = 0;

    WSAStartup(MAKEWORD(2,0),&wsaData);
    if(rc != 0){
        printf("Fehler: startWinsock, Fehlercode: %d\n",rc);
        return 1;
    }else{
        printf("Winsock gestartet!\n");
    }
    udpSocket = socket(AF_INET, SOCK_DGRAM, 0);
    if(udpSocket == INVALID_SOCKET){
        printf("Fehler: Der Socket konnte nicht erstellt werden, Fehlercode: %d\n", WSAGetLastError());
        return 1;
    }else{
        printf("UDP Socket erstellt!\n");
    }
    
    addr.sin_family = AF_INET;
    addr.sin_port = htons(STDPORT);
    addr.sin_addr.s_addr = INADDR_ANY;
    rc = bind(udpSocket, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
    if(rc==SOCKET_ERROR){
        printf("Fehler: bind, Fehlercode: %d\n", WSAGetLastError());
        return 1;
    }else{
        printf("Socket an Port %d gebunden\n", STDPORT);
    }
    
    int remoteAddrLen = sizeof(SOCKADDR_IN);
    struct REQUESTPACKAGE package;

	//Unsere Testschleife
	while(true){
		struct EMOSTATEPACKAGE responsePackage;
		rc = epoc.getDataEmoState(&responsePackage);
		printf("%d\n", responsePackage.nSamplesTaken);
		Sleep(100);
	}

    while(1){
        rc = recvfrom(udpSocket, (char*)&package, sizeof(REQUESTPACKAGE), 0, (SOCKADDR*)&remoteAddr, &remoteAddrLen);
        if(rc == SOCKET_ERROR){
            printf("Fehler: recvfrom, Fehlercode: %d\n",WSAGetLastError());
            return 1;
        }

        if(DEBUG){printf("Modus = %d\n", package.mode);}

        switch(package.mode){
            case FACIALEXPRESSION:
                respondFacialExpression();
                break;
            case EMOSTATE:
                respondEEGData();
                break;
            case RAWDATA:
                break;
        }
    }
}




inline void Server::respondFacialExpression(){
    int rc = 0;
    struct FACIALEXPRESSIONPACKAGE responsePackageF;
    rc = epoc.getDataFacialExpression(&responsePackageF);
    if(rc != 0){
        printf("Fehler: respondFacialExpression, Fehlercode %d\n, rc");
    }else{
        rc = sendto (udpSocket, (char*)&responsePackageF, sizeof(FACIALEXPRESSIONPACKAGE), 0, (SOCKADDR*)&remoteAddr, sizeof(SOCKADDR_IN));
        if(rc == SOCKET_ERROR){
            printf("Fehler: recvfrom, Fehlercode: %d\n",WSAGetLastError());
            return;
        }
    }
}

inline void Server::respondEEGData(){
    int rc = 0;
    struct EMOSTATEPACKAGE responsePackage;
    rc = epoc.getDataEmoState(&responsePackage);
    struct RAWDATAPACKAGE rawDataResponse;
    if(rc != 0){
        printf("Fehler: respondEmoState, Fehlercode %d\n, rc");
    }else{
        rc = sendto (udpSocket, (char*)&responsePackage, sizeof(EMOSTATEPACKAGE), 0, (SOCKADDR*)&remoteAddr, sizeof(SOCKADDR_IN));
        if(rc == SOCKET_ERROR){
            printf("Fehler: recvfrom, Fehlercode: %d\n",WSAGetLastError());
            return;
        }
    }
    for(int i = 0; i < responsePackage.nSamplesTaken; i++){
        epoc.getDataRawData(&rawDataResponse,i);
        rc = sendto (udpSocket, (char*)&rawDataResponse, sizeof(RAWDATAPACKAGE), 0, (SOCKADDR*)&remoteAddr, sizeof(SOCKADDR_IN));
    }

}


Server::~Server(void)
{
    WSACleanup();
}
