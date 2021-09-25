package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class MessageController {

    public TextArea messageField;
    public Button sendMessageButton;

    public void onMessageSendButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void onCancelButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void onMessageInputChange(KeyEvent keyEvent) {
        if (!messageField.getText().trim().isEmpty()) {
            sendMessageButton.setDisable(false);
        }
    }
}
