package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;


public class ControllerManager implements Initializable {

    @FXML
    ListView<String> filesList;

    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void initialize(URL location, ResourceBundle resources) {
        filesList.getItems().addAll("Java", "Core", "MMMM", "adcf");

    }
}
