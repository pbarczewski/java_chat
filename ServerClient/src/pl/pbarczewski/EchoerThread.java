/*
package pl.pbarczewski;

import java.io.*;
import java.net.Socket;

public class EchoerThread implements Runnable {

    private Socket socket;
    private Socket socket2;
    private Thread thread;
    private Server server;
    private BufferedReader bufferedReader;
    private String name;


    public EchoerThread(Socket socket, Socket socket2, String name) {
        this.socket = socket;
        this.socket2 = socket2;
        this.server = server;
        this.name = name;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        System.out.println("NO JESTEM TUTAJ");
        System.out.println(name);
      */
/*  try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(socket.getInputStream())))*//*

      try
        {
          //  bufferedReader = new BufferedReader(new InputStreamReader(socket2.getInputStream()));
            String textFromClient = bufferedReader.readLine();
            if (name != null) {
                System.out.println(name);
            } else {
                System.out.println("IS NULL");
            }
            while (true) {
               // String  textFromClient2 = bufferedReader1.readLine();
                server.sendToAll2(textFromClient);
               if(textFromClient != null) {
                   //server.sendToAll(textFromClient);
               }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
//                System.out.println(bufferedReader.readLine() + "tutaj");
                bufferedReader.close();
                server.getClientsConnected().remove(socket);
                socket.close();
                if(server.getClientsConnected().size() == 0 ) {
                    System.out.println("No users");
                } else {
                    System.out.println("Number of users " + server.getClientsConnected().size());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }



}
*/
