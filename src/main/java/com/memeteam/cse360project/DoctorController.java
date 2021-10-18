package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Objects;

import com.memeteam.cse360project.models.User;

public class DoctorController {
    public MenuButton userMenu;
    public Label nameLabel;
    public Label ageLabel;
    public Label weightLabel;
    public Label heightLabel;
    public Label tempLabel;
    public Label bpLabel;
    public TextArea nurseNotes;
    public Button medicalButton;
    public Button messagesButton;
    public Button contactButton;
    public TextArea doctorsNotes;

    public int currentUserID;
    public User currentUser; //Pulls the full user
    public Button saveButton;

    public void onPatientClick(ActionEvent event) throws SQLException {
        doctorsNotes.setDisable(false);
        nurseNotes.setDisable(false);
        MenuItem mi = (MenuItem) event.getSource();
        currentUserID = Integer.parseInt(mi.getId());
        currentUser = Main.DBS.GetUserById(currentUserID); //grabbing the full user
        userMenu.setText(mi.getText());
        medicalButton.setDisable(false);
        messagesButton.setDisable(false);
        contactButton.setDisable(false);
        nameLabel.setText(mi.getText());
        ageLabel.setText(Integer.toString(convertAge()));
        weightLabel.setText(Integer.toString(currentUser.getWeight()));
        heightLabel.setText(getUserHeight());
        bpLabel.setText(Integer.toString(currentUser.getBloodpressure()));
        tempLabel.setText(Float.toString(currentUser.getTemperature()));
        nurseNotes.setText(currentUser.getNursenotes());
        doctorsNotes.setText(currentUser.getDoctornotes());
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

    public String getUserDoctorsNotes() throws SQLException {
        return Main.DBS.GetUserById(currentUserID).getDoctornotes();
    }

    public String getUserMedcombo(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getMedical();
    }

    public String getUserMessage(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getMessage();
    }
    
    public String getUserPhone(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getPhone();
    }

    public String getUserEmail(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username).getEmail();
    }

    /* Button Click Event Handlers */
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

    public void onMedicalButtonClick(ActionEvent event) throws IOException, SQLException {
        MedicalController.setMedCombo(currentUser.getMedical());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(currentUser.getMedical());
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

    public void onMessageButtonClick(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("message.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MessageController mc = fxmlLoader.getController();
        mc.setCurrentUser(currentUser);
        mc.setCurrentUserID(currentUserID);
        mc.messageField.setText(currentUser.getMessage());
        mc.messageField.setEditable(false);
        mc.sendMessageButton.setVisible(false);
        mc.cancelButton.setText("OK");
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onContactButtonClick(ActionEvent event) throws IOException, SQLException {
        ContactController.setEmail(currentUser.getEmail());
        ContactController.setPhone(currentUser.getPhone());
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        ContactController cc = fxmlLoader.getController();
        cc.predefine(currentUser.getPhone(), currentUser.getEmail());
        cc.phoneField.setEditable(false);
        cc.emailField.setEditable(false);
        cc.cancelButton.setText("OK");
        cc.saveButton.setVisible(false);
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }
    public void onSaveButtonClick(ActionEvent event) throws SQLException {
        //Change current user's doctor's notes
        currentUser.setDoctornotes(doctorsNotes.getText().replaceAll("'","''"));
        //Push updated user
        Main.DBS.PatientUpdate(currentUser);
    }
}
