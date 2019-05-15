package MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(this.getClass().getResource("/fxmlTemplates/MainControllerTemplate.fxml"));
        BorderPane borderPane = fxmlLoader.load();
        Scene scene = new Scene(borderPane);
        primaryStage.setTitle("My chat");
        primaryStage.setHeight(700.0);
        primaryStage.setWidth(1000.0);
        primaryStage.setMinWidth(400.0);
        primaryStage.setMinHeight(400.0);
        primaryStage.setScene(scene);
        primaryStage.show();
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        primaryStage.setOnCloseRequest((event -> new MainWindowController().close()));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
