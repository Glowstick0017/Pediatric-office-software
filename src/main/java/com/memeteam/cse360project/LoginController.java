package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

public class LoginController {
    public Button loginButton;
    public TextField usernameField;
    public PasswordField passwordField;
    public Button registerButton;
    public Label credentialError;

    @FXML
    protected void onRegisterButtonClick(ActionEvent event) throws IOException {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("register.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onUsernameInputChange(KeyEvent event) {
        if (!usernameField.getText().trim().isEmpty() && !passwordField.getText().trim().isEmpty()) {
            loginButton.setDisable(false);
            loginButton.setDefaultButton(true);
            registerButton.setDefaultButton(false);
        }
    }

    public void onPasswordInputChange(KeyEvent event) {
        if (!usernameField.getText().trim().isEmpty() && !passwordField.getText().trim().isEmpty()) {
            loginButton.setDisable(false);
            loginButton.setDefaultButton(true);
            registerButton.setDefaultButton(false);
        }
    }

    public void onLoginButtonClick(ActionEvent event) throws IOException {
        // LOG IN CREDENTIAL CHECK LOGIC WILL GO HERE
        if (usernameField.getText().trim().toUpperCase(Locale.ROOT).equals("DOCTOR")) {
            if (passwordField.getText().trim().equals("DocPass")) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("doctor.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                credentialError.setText("Wrong Password");
                credentialError.setVisible(true);
            }
        } else if (usernameField.getText().trim().toUpperCase(Locale.ROOT).equals("NURSE")) {
            if (passwordField.getText().trim().equals("NursePass")) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("nurse.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                credentialError.setText("Wrong Password");
                credentialError.setVisible(true);
            }
        } else if (usernameField.getText().trim().toUpperCase(Locale.ROOT).equals("PATIENT")) {
            if (passwordField.getText().trim().equals("PatPass")) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                Parent root= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("patient.fxml")));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                credentialError.setText("Wrong Password");
                credentialError.setVisible(true);
            }
        } else {
            credentialError.setText("Username does not exist");
            credentialError.setVisible(true);
        }
    }

    public void exit(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        Window window = mi.getParentPopup().getOwnerWindow();
        Stage stage = (Stage) window;
        stage.close();
    }

    public void about(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }
}