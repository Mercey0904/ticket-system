package view.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import view.LaunchWindow;
import view.MsgDialog;

public class ThankController {

    @FXML
    void close(ActionEvent event) {
    	Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
    }

}