package pl.pbarczewski.Server;


import pl.pbarczewski.EchoerThreadInterface.EchoerUserInterfaceImpl;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {

    private ServerSocket serverSocket;
    private List<Socket> clientsConnected;
    private Socket socket;

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public List<Socket> getClientsConnected() {
        return clientsConnected;
    }

    public void setClientsConnected(List<Socket> clientsConnected) {
        this.clientsConnected = clientsConnected;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUp() {
            final int port = 5050;
            try {
                serverSocket = new ServerSocket(port);
                clientsConnected = new ArrayList<>();
                System.out.println("Server is running");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            while (true) {
                try {
                    socket = serverSocket.accept();
                    clientsConnected.add(socket);
                    System.out.println("Number of users: " + clientsConnected.size());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
                new EchoerUserInterfaceImpl().setUp(socket, this);
            }
        }

    public synchronized void sendToAll(String msg)
    {
        for(Iterator<Socket> it = clientsConnected.iterator(); it.hasNext();)
        {
            Socket singleclient =  it.next();
            if ( (!singleclient.isClosed()) && (clientsConnected.size() > 0) ) {
                try {
                    PrintWriter pw  = new PrintWriter(singleclient.getOutputStream(), true);
                    pw.println(msg);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

}
