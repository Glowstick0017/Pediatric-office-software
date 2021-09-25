package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class ContactController {

    public void onSaveButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
