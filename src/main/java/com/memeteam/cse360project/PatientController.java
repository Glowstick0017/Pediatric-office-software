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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class PatientController {

    public TextArea notesField;
    public Label nameField;
    public Label ageField;
    public static String currentUser;

    public void setName(String name) {
        nameField.setText(name);
    }

    public void setAge(String age) {
        ageField.setText(age);
    }

    public void setNotes(String notes) {
        notesField.setText(notes);
    }

    public void setCurrentUser(String cu) {
        currentUser = cu;
    }

    public void onEditMedicalButtonClick(ActionEvent event) throws IOException {
        MedicalController.setMedCombo(getUserMedcombo(currentUser));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(getUserMedcombo(currentUser));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onEditContactButtonClick(ActionEvent event) throws IOException {
        ContactController.setEmail(getUserEmail(currentUser));
        ContactController.setPhone(getUserPhone(currentUser));
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        ContactController cc = fxmlLoader.getController();
        cc.predefine(getUserPhone(currentUser), getUserEmail(currentUser));
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

    public static void setUserPhone(String phone) {
        String sql = "UPDATE patients\n" +
                "SET phone = '" + phone + "'\n" +
                "WHERE username='" + currentUser + "'";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUserEmail(String email) {
        String sql = "UPDATE patients\n" +
                "SET email = '" + email + "'\n" +
                "WHERE username='" + currentUser + "'";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setUserMedCombo(String medcombo) {
        String sql = "UPDATE patients\n" +
                "SET medical = '" + medcombo + "'\n" +
                "WHERE username='" + currentUser + "'";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void onSendMessageButtonClick(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("message.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
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
