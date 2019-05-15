package MainWindow;

import Socket.ConfigureSocket;
import javafx.collections.ObservableList;
import javafx.scene.control.TextArea;
import java.io.IOException;
import java.util.ArrayList;

public class ReceiveMessage implements Runnable {

    private Thread thread;
    private TextArea textArea;
    private String nickname;

    public ReceiveMessage(TextArea textArea) {
        this.textArea = textArea;
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        if(thread.isAlive()) {
            ConfigureSocket.getMessage(textArea);
        } else {
            System.out.println("Thread is not active");
        }
    }

}

