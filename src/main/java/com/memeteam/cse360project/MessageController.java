package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class MessageController {

    public TextArea messageField;
    public Button sendMessageButton;

    public String currentUser;
    public Button cancelButton;

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void onMessageSendButtonClick(ActionEvent event) {
        setUserMessage(currentUser);
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
        sendMessageButton.setDisable(false);
    }

    public void setUserMessage(String currentUser) {
        String message = messageField.getText().replaceAll("'", "''");
        String sql = "UPDATE patients\n" +
                "SET message = '" + message + "'\n" +
                "WHERE username='" + currentUser + "'";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
