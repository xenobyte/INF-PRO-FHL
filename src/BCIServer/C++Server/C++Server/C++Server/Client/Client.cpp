#include "Client.h"


Client::Client(void){
    remoteAddrLen = sizeof(SOCKADDR_IN);
    int rc = 0;

    WSAStartup(MAKEWORD(2,0),&wsaData);
    if(rc != 0){
        printf("Fehler: startWinsock, Fehlercode: %d\n",rc);
        return;
    }else{
        printf("Winsock gestartet!\n");
    }

      udpSocket = socket(AF_INET, SOCK_DGRAM, 0);
      if(udpSocket == INVALID_SOCKET){
          printf("Fehler: Der Socket konnte nicht erstellt werden, Fehlercode: %d\n",WSAGetLastError());
          return;
      }else{
          printf("UDP Socket erstellt!\n");
      }
      
      addr.sin_family = AF_INET;
      addr.sin_port = htons(STDPORT);
      addr.sin_addr.s_addr = inet_addr(IP);
}

int Client::getFacialExpression(struct FACIALEXPRESSIONPACKAGE * rPackage){
    int rc = 0;
    struct REQUESTPACKAGE package;
    package.mode = FACIALEXPRESSION;

    if(DEBUG){printf("Aufruf der Methode getFacialExpression()\n");}

    rc = sendto (udpSocket, (char*)&package, sizeof(REQUESTPACKAGE), 0, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
    if(rc == SOCKET_ERROR){
        printf("Fehler: sendto, Fehlercode: %d\n",WSAGetLastError());
        return 1;
    }
    if(DEBUG){printf("%d Bytes gesendet!\n", rc);}

    rc = recvfrom(udpSocket, (char*)rPackage, sizeof(FACIALEXPRESSIONPACKAGE), 0, (SOCKADDR*)&remoteAddr, &remoteAddrLen);
    if(rc == SOCKET_ERROR){
        printf("Fehler: recvfrom, Fehlercode: %d\n",WSAGetLastError());
        return 1;
    }

    return 0;

}

int Client::getEEGData(struct EMOSTATEPACKAGE * rPackage){
    int rc = 0;
    struct REQUESTPACKAGE package;
    package.mode = EEGDATA;

    if(DEBUG){printf("Aufruf der Methode getEmoState()\n");}

    rc = sendto (udpSocket, (char*)&package, sizeof(REQUESTPACKAGE), 0, (SOCKADDR*)&addr, sizeof(SOCKADDR_IN));
    if(rc == SOCKET_ERROR){
        printf("Fehler: sendto, Fehlercode: %d\n",WSAGetLastError());
        return 1;
    }else{
        if(DEBUG){printf("%d Bytes gesendet!\n", rc);}
    }

    rc = recvfrom(udpSocket, (char*)rPackage, sizeof(EMOSTATEPACKAGE), 0, (SOCKADDR*)&remoteAddr, &remoteAddrLen);
    if(rc == SOCKET_ERROR){
        printf("Fehler: recvfrom, Fehlercode: %d\n",WSAGetLastError());
        return 1;
    }
    return 0;
}

Client::~Client(void)
{
}
