package MainWindow;



import Socket.ConfigureSocket;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javax.swing.*;
import java.io.IOException;
import java.util.Optional;

public class MainWindowController {



    @FXML
    public BorderPane mainBorderPane;
    @FXML
    private MenuItem save, close, login;
    @FXML
    private TextArea chatArea;
    @FXML
    private TextField messageField;
    private String nickname;
    @FXML
    private ListView listView;
    private boolean flag;



    @FXML
    public void initialize() {
        login.setGraphic(new ImageView("/resources/log-in.png"));
        save.setGraphic(new ImageView("/resources/save.png"));
        close.setGraphic(new ImageView("/resources/cross-remove-sign.png"));
        chatArea.setEditable(false);
        chatArea.setMouseTransparent(true);
        chatArea.setFocusTraversable(false);
        messageField.setEditable(false);
    }

    @FXML
    public void sendToserver() {
           String message = messageField.getText().trim();
            if(message.trim().length() > 0 ) {
              ConfigureSocket.sendToServer2(nickname, message);
                messageField.clear();
            }
    }

    @FXML
    public void saveChat() {
        new FileChooser();
        if(FileChooser.status == JFileChooser.APPROVE_OPTION) {
            FileChooser.saveChatToFile(chatArea);
        }
    }

    @FXML
    public void logIn()  {
            Dialog<ButtonType> dialog = new Dialog<>();
            FXMLLoader fxmlLoader = new FXMLLoader();
            dialog.initOwner(mainBorderPane.getScene().getWindow());
            fxmlLoader.setLocation(getClass().getResource("/fxmlTemplates/login.fxml"));
            try {
                dialog.getDialogPane().setContent(fxmlLoader.load());
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            dialog.setHeaderText("Type your nickname");
            dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

            Optional<ButtonType> result = dialog.showAndWait();
            if (result.get() == ButtonType.OK && result.isPresent()) {
                Login loginController = fxmlLoader.getController();
                if (loginController.getname() != null) {
                    flag = ConfigureSocket.setSocket();
                    nickname = loginController.getname();
                    new ReceiveMessage(chatArea);
                    messageField.setEditable(true);
                    login.setVisible(false);
                } else {
                    logIn();
                }
            }
        }

    @FXML
    public void close() {
        if(flag == true) {
            ConfigureSocket.stopConnection();
        }
        Platform.exit();
        System.exit(0);

    }
}
