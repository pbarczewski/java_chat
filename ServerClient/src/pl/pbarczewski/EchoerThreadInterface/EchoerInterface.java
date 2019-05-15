package pl.pbarczewski.EchoerThreadInterface;

import pl.pbarczewski.Server.Server;

import java.net.Socket;

public interface EchoerInterface {

    void setUp(Socket socket, Server setServerUp);
}
