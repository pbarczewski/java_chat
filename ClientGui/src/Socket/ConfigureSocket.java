package Socket;


import MainWindow.ReceiveMessage;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;


public class ConfigureSocket {

    private static Socket socket;
    private static PrintWriter printWriter;
    private static BufferedReader bufferedReader;

    public static BufferedReader getBufferedReader() {
        return bufferedReader;
    }

    public static void setBufferedReader(BufferedReader bufferedReader) {
        ConfigureSocket.bufferedReader = bufferedReader;
    }
    public static PrintWriter getPrintWriter() {
        return printWriter;
    }

    public static Socket getSocket() {
        return socket;
    }

    public static void setSocket(Socket socket) {
        ConfigureSocket.socket = socket;
    }

    public static void setPrintWriter(PrintWriter printWriter) {
        ConfigureSocket.printWriter = printWriter;
    }

    public static boolean setSocket()
    {
        final String host = "localhost";
        final int port = 5050;
        try {
            socket = new Socket(host, port);
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Socket has been created");
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void getMessage(TextArea textArea) {
        while (true) {
            try {
                String message = bufferedReader.readLine();
                textArea.appendText(message + "\r\n");
            } catch (IOException e) {
                break;
            }
        }
    }
    public static void sendToServer2(String name, String message) {
            if (socket != null) {
                printWriter.println(name + ": " + message);
            } else {
                System.out.println("Socket hasn't been initialized");
            }
    }

    public static void stopConnection() {
        try {
            printWriter.close();
            bufferedReader.close();
            socket.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }




}
