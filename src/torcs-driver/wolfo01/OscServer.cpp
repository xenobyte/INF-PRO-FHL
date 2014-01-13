#include "OscServer.h"


static float *accelToTorcs;

OscServer::OscServer() {
    server = lo_server_thread_new("58100", error);
    lo_server_thread_add_method(server , "/torcs/setaccel", "f", gearControl, NULL);
}

OscServer::~OscServer() {
//    std::cout << "~OscServer" << std::endl;
    lo_server_thread_free(server);
}

bool OscServer::start(float *oscinfo) {
    *oscinfo = 1;
    accelToTorcs = oscinfo;
    lo_server_thread_start(server);
//    std::cout << "Server gestartet" << std::endl;
    return true;
}

int OscServer::gearControl(const char *path, const char *types, lo_arg ** argv,
            int argc, void *data, void *user_data){
    *accelToTorcs = argv[0]->f;
//    std::cout << "Gear steht auf " << std::endl;
    return 0;
}

void OscServer::error(int num, const char *msg, const char *path) {
    std::cout << "liblo server error " << num << " in path " << path << msg << std::endl;
}
