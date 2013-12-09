#include "Server.h"

int main( int argc, const char* argv[] )
{
	Server s = Server();
    s.start();
    getc(stdin);
}