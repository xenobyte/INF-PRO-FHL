#include "Client.h"

int main( int argc, const char* argv[] )
{
	Client s = Client();
    //s.start();
    getc(stdin);
    struct EMOSTATEPACKAGE * p = new struct EMOSTATEPACKAGE;
    s.getEmoState(p);
    printf("%d\n", p->nSamplesTaken);
    printf("%f\n", p->pEngagement);
    printf("%f\n", p->pExcitement);
    printf("%f\n", p->pFrustration);
    printf("%f\n", p->pMeditation);
    printf("%d\n", p->errorCode);
    getc(stdin);
}