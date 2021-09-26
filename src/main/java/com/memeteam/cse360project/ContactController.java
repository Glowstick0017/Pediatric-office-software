package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ContactController {

    public static String phone;
    public static String email;
    public TextField phoneField;
    public TextField emailField;
    public Button cancelButton;
    public Button saveButton;

    public static void setPhone(String phone) {
        ContactController.phone = phone;
    }

    public static void setEmail(String email) {
        ContactController.email = email;
    }

    public void onSaveButtonClick(ActionEvent event) {
        RegisterController.setPhone(phoneField.getText());
        RegisterController.setEmail(emailField.getText());
        PatientController.setUserPhone(phoneField.getText());
        PatientController.setUserEmail(emailField.getText());
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void predefine(String phone, String email) {
        phoneField.setText(phone);
        emailField.setText(email);
    }

    public void onCancelButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
