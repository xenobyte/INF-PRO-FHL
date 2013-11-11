#include "SendThread.h"
SendThread::SendThread(void){

}

SendThread::SendThread(SOCKADDR_IN addr, int id_l){
    isRunning = 1;
    remoteAddr = addr;
    id = id_l;
}

void SendThread::start(SendThread t){
    //HANDLE handle = (HANDLE)_beginthreadex(NULL,0,SendThread::running,
		//		(LPVOID)this, START, &wThreadID);;
    
}

unsigned int __stdcall running(void* data){
    return 0;
}

SendThread::~SendThread(void){
}
