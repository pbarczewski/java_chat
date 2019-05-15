package pl.pbarczewski.EchoerThreadInterface;


import pl.pbarczewski.Server.Server;
import java.io.*;
import java.net.Socket;

public class EchoerUserInterfaceImpl implements EchoerInterface, Runnable {

    private Socket socket;
    private Server setServerUp;
    private Thread thread;
    private String message;

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public Server getSetServerUp() {
        return setServerUp;
    }

    public void setSetServerUp(Server setServerUp) {
        this.setServerUp = setServerUp;
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) {
        this.thread = thread;
    }

    @Override
    public void setUp(Socket socket, Server serverSetUp) {
        this.socket = socket;
        this.setServerUp = serverSetUp;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                message = bufferedReader.readLine();
                if (message != null) {
                    setServerUp.sendToAll(message);
                    System.out.println(message);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                setServerUp.getClientsConnected().remove(socket);
                socket.close();
                if(setServerUp.getClientsConnected().size() == 0 ) {
                    System.out.println("No users");
                } else {
                    System.out.println("Number of users " + setServerUp.getClientsConnected().size());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
