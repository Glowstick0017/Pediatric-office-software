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
import org.controlsfx.control.action.Action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

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

    public String currentUser;
    public Button saveButton;

    public void onPatientClick(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        currentUser = mi.getId();
        userMenu.setText(mi.getText());
        medicalButton.setDisable(false);
        messagesButton.setDisable(false);
        contactButton.setDisable(false);
        nameLabel.setText(mi.getText());
        ageLabel.setText(getUserAge());
        weightField.setText(getUserWeight());
        heightField.setText(getUserHeight());
        bpField.setText(getUserbp());
        tempField.setText(getUserTemp());
        notesText.setText(getUserNurseNotes());
    }

    public String getUserAge() {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + currentUser + "'";
        String age = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                age = String.valueOf(rs.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return age;
    }

    public String getUserWeight() {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + currentUser + "'";
        String weight = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                weight = rs.getString("weight");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return weight;
    }

    public String getUserHeight() {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + currentUser + "'";
        String height = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                height = rs.getString("height");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return height;
    }

    public String getUserbp() {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + currentUser + "'";
        String bp = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                bp = rs.getString("bloodpressure");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bp;
    }

    public String getUserTemp() {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + currentUser + "'";
        String temp = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                temp = rs.getString("temperature");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return temp;
    }

    public String getUserNurseNotes() {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + currentUser + "'";
        String notes = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                notes = rs.getString("nursenotes");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notes;
    }

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

    public void onMedicalButtonClick(ActionEvent event) throws IOException {
        MedicalController.setMedCombo(getUserMedcombo(currentUser));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(getUserMedcombo(currentUser));
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

    public String getUserMedcombo(String username) {
        String sql = "SELECT medical\n" +
                "FROM patients\n" +
                "WHERE username='" + username + "'";
        String medCombo = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                medCombo = rs.getString("medical");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return medCombo;
    }


    public void onContactButtonClick(ActionEvent event) throws IOException {
        ContactController.setEmail(getUserEmail(currentUser));
        ContactController.setPhone(getUserPhone(currentUser));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        ContactController cc = fxmlLoader.getController();
        cc.predefine(getUserPhone(currentUser), getUserEmail(currentUser));
        cc.phoneField.setEditable(false);
        cc.emailField.setEditable(false);
        cc.cancelButton.setText("OK");
        cc.saveButton.setVisible(false);
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public String getUserPhone(String username) {
        String sql = "SELECT phone\n" +
                "FROM patients\n" +
                "WHERE username='" + username + "'";
        String phone = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                phone = rs.getString("phone");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phone;
    }

    public String getUserEmail(String username) {
        String sql = "SELECT email\n" +
                "FROM patients\n" +
                "WHERE username='" + username + "'";
        String email = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                email = rs.getString("email");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return email;
    }

    public void onMessageButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("message.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MessageController mc = fxmlLoader.getController();
        mc.setCurrentUser(currentUser);
        mc.messageField.setText(getUserMessage(currentUser));
        mc.messageField.setEditable(false);
        mc.sendMessageButton.setVisible(false);
        mc.cancelButton.setText("OK");
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public String getUserMessage(String username) {
        String sql = "SELECT message\n" +
                "FROM patients\n" +
                "WHERE username='" + username + "'";
        String message = "";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                message = rs.getString("message");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return message;
    }

    public void onSaveButtonClick(ActionEvent event) {
        String sql = "UPDATE patients\n" +
                "SET weight = '" + weightField.getText() + "'\n" +
                ", height = '" + heightField.getText().replaceAll("'","''") + "'\n" +
                ", bloodpressure = '" + bpField.getText() + "'\n" +
                ", temperature = '" + tempField.getText() + "'\n" +
                ", nursenotes = '" + notesText.getText().replaceAll("'","''") + "'\n" +
                "WHERE username='" + currentUser + "'";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
