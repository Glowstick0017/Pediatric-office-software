package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

import com.memeteam.cse360project.models.User;

public class NurseController {
    public MenuButton userMenu;
    public Label nameLabel;
    public Label ageLabel;
    public Button medicalButton;
    public Button messagesButton;
    public Button contactButton;
    public TextArea notesText;
    public TextField weightField;
    public TextField heightField;
    public TextField bpField;
    public TextField tempField;

    public int currentUserID;
    public User currentUser; //Pulls the full user
    public Button saveButton;

    public void onPatientClick(ActionEvent event) throws SQLException {
        notesText.setDisable(false);
        MenuItem mi = (MenuItem) event.getSource();
        currentUserID = Integer.parseInt(mi.getId());
        currentUser = Main.DBS.GetUserById(currentUserID); //grabbing the full user
        userMenu.setText(mi.getText());
        medicalButton.setDisable(false);
        messagesButton.setDisable(false);
        contactButton.setDisable(false);
        nameLabel.setText(mi.getText());
        ageLabel.setText(Integer.toString(convertAge()));
        weightField.setText(Integer.toString(currentUser.getWeight()));
        heightField.setText(currentUser.getHeight());
        bpField.setText(Integer.toString(currentUser.getBloodpressure()));
        tempField.setText(Float.toString(currentUser.getTemperature()));
        notesText.setText(getUserNurseNotes());
    }

    public int convertAge(){
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return (currentDate.getYear() - currentUser.getBirthday().getYear());
    }

    /* Getters */ // These are kinda pointless because you don't need to hit the database for every label, just pull the full profile
    public int getUserAge() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getAge();
    }

    public int getUserWeight() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getWeight();
    }

    public String getUserHeight() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getHeight();
    }

    public int getUserbp() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getBloodpressure();
    }

    public float getUserTemp() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getTemperature();
    }

    public String getUserNurseNotes() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getNursenotes();
    }
    
    public String getUserPhone(int id) throws SQLException {
        return Main.DBS.GetUserById(id).getPhone();
    }

    public String getUserEmail(int id) throws SQLException {
        return Main.DBS.GetUserById(id).getEmail();
    }

    public String getUserMedcombo(int id) throws SQLException {
        return Main.DBS.GetUserById(id).getMedical();
    }

    public String getUserMessage(int id) throws SQLException {
        return Main.DBS.GetUserById(id).getMessage();
    }

    /* Button Click Event Handlers */
    public void exit(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        Window window = mi.getParentPopup().getOwnerWindow();
        Stage stage = (Stage) window;
        stage.close();
    }

    public void logOut(ActionEvent event) throws IOException {
        userMenu.getItems().removeAll();
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

    public void onMedicalButtonClick(ActionEvent event) throws IOException, SQLException {
        MedicalController.setMedCombo(getUserMedcombo(currentUserID));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(getUserMedcombo(currentUserID));
        mc.submitButton.setText("OK");
        mc.ardsRadio.setDisable(true);
        mc.anginaRadio.setDisable(true);
        mc.anxietyRadio.setDisable(true);
        mc.arthritisRadio.setDisable(true);
        mc.asthmaRadio.setDisable(true);
        mc.copdRadio.setDisable(true);
        mc.chfRadio.setDisable(true);
        mc.dddRadio.setDisable(true);
        mc.depressionRadio.setDisable(true);
        mc.diabetesRadio.setDisable(true);
        mc.emphysemaRadio.setDisable(true);
        mc.hearingRadio.setDisable(true);
        mc.heartRadio.setDisable(true);
        mc.msRadio.setDisable(true);
        mc.osteoRadio.setDisable(true);
        mc.pdRadio.setDisable(true);
        mc.pvdRadio.setDisable(true);
        mc.strokeRadio.setDisable(true);
        mc.ugdRadio.setDisable(true);
        mc.viRadio.setDisable(true);
        mc.vaxxRadio.setDisable(true);
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onContactButtonClick(ActionEvent event) throws IOException, SQLException {
        ContactController.setEmail(getUserEmail(currentUserID));
        ContactController.setPhone(getUserPhone(currentUserID));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        ContactController cc = fxmlLoader.getController();
        cc.predefine(getUserPhone(currentUserID), getUserEmail(currentUserID));
        cc.phoneField.setEditable(false);
        cc.emailField.setEditable(false);
        cc.cancelButton.setText("OK");
        cc.saveButton.setVisible(false);
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onMessageButtonClick(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("message.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MessageController mc = fxmlLoader.getController();
        mc.setCurrentUser(currentUser);
        mc.setCurrentUserID(currentUserID);
        mc.messageField.setText(getUserMessage(currentUserID));
        mc.messageField.setEditable(false);
        mc.sendMessageButton.setVisible(false);
        mc.cancelButton.setText("OK");
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    //Update button
    public void onSaveButtonClick(ActionEvent event) throws SQLException {
        //Change current users info
        currentUser.setWeight(Integer.parseInt(weightField.getText()));
        currentUser.setHeight(heightField.getText());
        currentUser.setBloodpressure(Integer.parseInt(bpField.getText()));
        currentUser.setTemperature(Float.parseFloat(tempField.getText()));
        currentUser.setNursenotes(notesText.getText().replaceAll("'","''"));

        //Push updated user
        Main.DBS.PatientUpdate(currentUser);
    }

}
