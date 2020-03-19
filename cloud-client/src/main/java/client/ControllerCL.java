package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class ControllerCL {

    @FXML
    ListView filesList;



    public void menuItemFileExitAction(ActionEvent actionEvent) {
        Platform.exit();
    }
}
