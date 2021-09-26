package com.memeteam.cse360project;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;

import javax.xml.transform.Result;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Locale;
import java.util.Objects;

public class RegisterController {
    public DatePicker dateField;
    public TextField firstnameField;
    public TextField lastnameField;
    public TextField usernameField;
    public TextField passwordField;
    public Button registerButton;
    public Label existsLabel;

    private static String medCombo = "000000000000000000000";

    public void onMedicalButtonClick() throws IOException {
        MedicalController.setMedCombo(medCombo);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Parent root= fxmlLoader.load();
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(medCombo);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public static void setMedCombo(String medCombo) {
        RegisterController.medCombo = medCombo;
    }

    public void onContactButtonClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public void onLoginTextClick(MouseEvent mouseEvent) throws IOException {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        Parent root= FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("login.fxml")));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void onInputChange() {
        if (!firstnameField.getText().trim().isEmpty() &&
                !lastnameField.getText().trim().isEmpty() &&
                dateField.getValue() != null &&
                !usernameField.getText().trim().isEmpty() &&
                !passwordField.getText().trim().isEmpty()) {
            registerButton.setDisable(false);
        }
    }

    public void onRegisterButtonClick(ActionEvent event) throws IOException {
        if (userExists(usernameField.getText().toUpperCase(Locale.ROOT))) {
            existsLabel.setVisible(true);
        } else {
            registerUser();
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Parent root = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("login.fxml")));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public boolean userExists(String username) {
        String sql = "SELECT *\n" +
                "FROM patients\n" +
                "WHERE username='" + username + "'";
        int exists = -1;
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                exists = rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists > -1;
    }

    public void registerUser() {
        String sql = "SELECT COUNT(*)\n" +
                "FROM patients";
        int id = 0;
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                id = rs.getInt("COUNT(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int age = 2021 - dateField.getValue().getYear();
        sql = "INSERT INTO patients (id, firstname, lastname, age, username, password)\n"
                + "VALUES (" + id + ",'" + firstnameField.getText() + "','" + lastnameField.getText() +
                "'," + age + ",'" + usernameField.getText().toUpperCase(Locale.ROOT) +
                "','" + passwordField.getText() + "');";
        try (Connection conn = Main.connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent event) {
        MenuItem mi = (MenuItem) event.getSource();
        Window window = mi.getParentPopup().getOwnerWindow();
        Stage stage = (Stage) window;
        stage.close();
    }

    public void about() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("about.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }
}
