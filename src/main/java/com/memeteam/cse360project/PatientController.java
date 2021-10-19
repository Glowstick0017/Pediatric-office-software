package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

import com.memeteam.cse360project.models.User;

public class PatientController {

    public TextArea notesField;
    public Label nameField;
    public Label ageField;
    public static User currentUser;
    public static int currentUserID;

    /* Sets thelabels */
    public void setName(String name) {
        nameField.setText(name);
    }

    public void setAge(String age) {
        ageField.setText(age);
    }

    public void setNotes(String notes) {
        notesField.setText(notes);
    }

    public void setCurrentUser(User cu) throws SQLException {
        currentUser = cu;
    }
    public void setCurrentID(int id) throws SQLException {
        currentUserID = id;
    }

    public void onEditMedicalButtonClick(ActionEvent event) throws IOException, SQLException {
        MedicalController.setMedCombo(currentUser.getMedical());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(currentUser.getMedical());
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onEditContactButtonClick(ActionEvent event) throws IOException, SQLException {
        ContactController.setEmail(currentUser.getEmail());
        ContactController.setPhone(currentUser.getPhone());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        ContactController cc = fxmlLoader.getController();
        cc.predefine(currentUser.getPhone(), currentUser.getEmail());
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    /* Use these getters if you're only needing one variable at one time */
    public String getUserPhone(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getPhone();
    }

    public String getUserEmail(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getEmail();
    }

    public static void setUserPhone(String phone) throws SQLException {
        currentUser.setPhone(phone);
        Main.DBS.PatientUpdate(currentUser);
    }

    public static void setUserEmail(String email) throws SQLException {
        currentUser.setEmail(email);
        Main.DBS.PatientUpdate(currentUser);
    }

    public static void setUserMedCombo(String medcombo) throws SQLException {
        currentUser.setMedical(medcombo);
        Main.DBS.PatientUpdate(currentUser);
    }

    public String getUserMedcombo(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getMedical();
    }

    public void onSendMessageButtonClick(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("message.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MessageController mc = fxmlLoader.getController();
        mc.setCurrentUser(currentUser);
        mc.setCurrentUserID(currentUserID);
        mc.messageField.setText(currentUser.getMessage());
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public String getUserMessage(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getMessage();
    }

    public void exit(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        Window window = mi.getParentPopup().getOwnerWindow();
        Stage stage = (Stage) window;
        stage.close();
    }

    public void logOut(ActionEvent event) throws IOException {
        MenuItem mi = (MenuItem) event.getSource();
        Window window = mi.getParentPopup().getOwnerWindow();
        Stage stage = (Stage) window;
        Parent root= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
