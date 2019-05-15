package MainWindow;


import javafx.scene.control.Alert;

import java.util.HashMap;
import java.util.Map;

public class Alerts {

    private static Alert alert;

    private static Map<String, Alert> alertMap;

    private static void setAlertList() {
        if (alertMap == null){
            alertMap = new HashMap<>();
        }

        alertMap.put("WARNING", new Alert(Alert.AlertType.WARNING,
                "Name should equals more then 3, and less then 15 characters. Please try again"));
        alertMap.put("CONNECTION-FAILED", new Alert(Alert.AlertType.ERROR,
                "Socket isn't responding"));
        alertMap.put("CONNECTION-SUCCEEDED", new Alert(Alert.AlertType.INFORMATION,
                "You have been connected to the server"));
        alertMap.put("FILE-SAVE", new Alert(Alert.AlertType.INFORMATION,
                "File has been saved"));
        alertMap.put("FILE-FAILED", new Alert(Alert.AlertType.INFORMATION,
                "File hasn't been saved"));
    }

    public static Alert setAlertType(String type) {
        setAlertList();
        if(alertMap.containsKey(type)) {
            alert = alertMap.get(type);
            alert.showAndWait();
            return alert;
        }
        return null;
    }




}
