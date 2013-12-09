#include "OscServer.h"


OscServer::OscServer() {
    server = lo_server_thread_new("8888", error);
    lo_server_thread_add_method(server , NULL, NULL, gearControl, NULL);
}

OscServer::~OscServer() {
    std::cout << "~OscServer" << std::endl;
}

bool OscServer::start(int *oscinfo) {
    *gearToTorcs = *oscinfo;
    lo_server_thread_start(server);
    std::cout << "Server gestartet" << std::endl;
    return true;
}

int OscServer::gearControl(const char *path, const char *types, lo_arg ** argv,
            int argc, void *data, void *user_data){
    std::cout << "Gear steht auf " << std::endl;
    return 0;
}

void OscServer::error(int num, const char *msg, const char *path) {
    std::cout << "liblo server error " << num << " in path " << path << msg << std::endl;
}
