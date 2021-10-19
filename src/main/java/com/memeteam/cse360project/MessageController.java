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

import com.memeteam.cse360project.models.User;

public class MessageController {

    public TextArea messageField;
    public Button sendMessageButton;

    public User currentUser;
    public int currentUserID;
    public Button cancelButton;

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public void setCurrentUserID(int id) throws SQLException{
        this.currentUserID = id;
    }

    public void onMessageSendButtonClick(ActionEvent event) throws SQLException {
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

    public void setUserMessage(User currentUser) throws SQLException {
        String message = messageField.getText().replaceAll("'", "''");
        currentUser.setMessage(message);
        Main.DBS.UserUpdate(currentUser);
    }
}
