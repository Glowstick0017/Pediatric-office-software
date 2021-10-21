package com.memeteam.cse360project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
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
        RegisterController.setMedCombo("000000000000000000000");
        RegisterController.setEmail("");
        RegisterController.setPhone("");
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

    //Robbie pls unhard code these pls -Tommy

    public void onLoginButtonClick(ActionEvent event) throws IOException, SQLException {
        // LOG IN CREDENTIAL CHECK LOGIC WILL GO HERE
        if (userExists(usernameField.getText().toUpperCase(Locale.ROOT))) {
            if (validatePassword(usernameField.getText().toUpperCase(Locale.ROOT))) {
                Main.setCurrentUser(usernameField.getText().toUpperCase(Locale.ROOT));
                Main.setCurrentID(usernameField.getText());
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("patient.fxml"));
                Parent root= loader.load();
                PatientController pc = loader.getController();
                var loggedUser = Main.DBS.GetUserByUsername(usernameField.getText().toUpperCase(Locale.ROOT));
                pc.setName(loggedUser.getFullName());
                pc.setAge(Integer.toString(loggedUser.getAge()));
                pc.setNotes(loggedUser.getDoctornotes());
                pc.setCurrentUser(loggedUser);
                pc.setCurrentID(PatientController.currentUser.getId());
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                credentialError.setText("Wrong Password");
                credentialError.setVisible(true);
            }
        } else

        // TEST CREDENTIALS
        if (usernameField.getText().trim().toUpperCase(Locale.ROOT).equals("DOCTOR")) {
            if (passwordField.getText().trim().equals("DocPass")) {
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("doctor.fxml"));
                Parent root= loader.load();
                DoctorController dc = loader.getController();
                for (int i = 1; i <= userCount(); i++) {
                    MenuItem mi = new MenuItem();
                    var user = Main.DBS.GetUserById(i);
                    mi.setText(user.getFullName());
                    mi.setId(Integer.toString(i));
                    mi.setOnAction(event1 -> {
                        try {
                            dc.onPatientClick(event1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                    dc.userMenu.getItems().add(mi);
                }
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
                FXMLLoader loader = new FXMLLoader(getClass().getResource("nurse.fxml"));
                Parent root= loader.load();
                NurseController nc = loader.getController();
                for (int i = 1; i <= userCount(); i++) {
                    MenuItem mi = new MenuItem();
                    var user = Main.DBS.GetUserById(i);
                    mi.setText(user.getFullName());
                    mi.setId(Integer.toString(i));
                    mi.setOnAction(event1 -> {
                        try {
                            nc.onPatientClick(event1);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
                    nc.userMenu.getItems().add(mi);
                }
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                credentialError.setText("Wrong Password");
                credentialError.setVisible(true);
            }
        }

        else {
            credentialError.setText("Username does not exist");
            credentialError.setVisible(true);
        }
    }
    //Username by ID
    private String getUsernameByID(int id) throws SQLException {
        return Main.DBS.GetUserById(id).getUsername();
    }
    //Fullname by username
    public String getName(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getFullName();
    }
    //Fullname by ID
    public String getNameByID(int id) throws SQLException {
        return Main.DBS.GetUserById(id).getFullName();
    }
    //Get Age
    public int getAge(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getAge();
    }
    //Gets the Doctor's Notes
    public String getNotes(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getDoctornotes();
    }
    //Checks if user exists
    public boolean userExists(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username.toUpperCase(Locale.ROOT)) != null ? true : false;
    }
    //Fetch user count
    public int userCount() throws SQLException {
        return Main.DBS.GetUserCount();
    }
    //Password validation
    public boolean validatePassword(String username) throws SQLException {
        var user = Main.DBS.GetUserByUsername(username.replaceAll("'","''"));
        String password = user.getPassword();
        return password.equals(passwordField.getText());
    }
    //Exit application
    public void exit(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        Window window = mi.getParentPopup().getOwnerWindow();
        Stage stage = (Stage) window;
        stage.close();
    }
    //About page
    public void about(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onGameClick(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("game.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.show();
        GameController gameController = fxmlLoader.getController();
        gameController.clicksPerSecond();
    }
}