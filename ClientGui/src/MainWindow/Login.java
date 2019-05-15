package MainWindow;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class Login {

    private final String regax = "^([a-zA-Z])[a-zA-Z0-9]{3,15}";

    @FXML
    private TextField textField;

    public String getname() {
        String name = textField.getText().trim();
        if((name.matches(regax)) && (name != null)) {
            return name;
        } else {
            Alerts.setAlertType("WARNING");
            return null;
        }
    }
}
