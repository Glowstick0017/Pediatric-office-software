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

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.AbstractMap.SimpleEntry;

public class RegisterController {
    public DatePicker dateField;
    public TextField firstnameField;
    public TextField lastnameField;
    public TextField usernameField;
    public TextField passwordField;
    public Button registerButton;
    public Label existsLabel;

    private static String medCombo = "000000000000000000000";
    private static String phone = "";
    private static String email = "";

    public void onMedicalButtonClick() throws IOException {
        MedicalController.setMedCombo(medCombo);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("medical.fxml"));
        Parent root = fxmlLoader.load();
        MedicalController mc = fxmlLoader.getController();
        mc.predefine(medCombo);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        stage.showAndWait();
    }

    public static void setPhone(String phone) {
        RegisterController.phone = phone;
    }

    public static void setEmail(String email) {
        RegisterController.email = email;
    }

    public static void setMedCombo(String medCombo) {
        RegisterController.medCombo = medCombo;
    }

    public void onContactButtonClick() throws IOException {
        ContactController.setPhone(phone);
        ContactController.setEmail(email);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("contact.fxml"));
        Parent root= fxmlLoader.load();
        ContactController cc = fxmlLoader.getController();
        cc.predefine(phone,email);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
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

    public void onRegisterButtonClick(ActionEvent event) throws IOException, SQLException {
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

    //Checks if user exists
    public boolean userExists(String username) throws SQLException {
        return Main.DBS.GetUserByUsername(username) != null ? true : false;
    }
    

    public void registerUser() throws SQLException{
        var arrayList = new ArrayList<SimpleEntry<TextField, Boolean>>(); 
            //cleaner method didn't work so let's just do this instead
            arrayList.add(new SimpleEntry<TextField, Boolean>(firstnameField, false));
            arrayList.add(new SimpleEntry<TextField, Boolean>(lastnameField, false));
            arrayList.add(new SimpleEntry<TextField, Boolean>(usernameField, true));
            arrayList.add(new SimpleEntry<TextField, Boolean>(passwordField, false));
            

            Date sqlDate =  Date.valueOf(dateField.getValue());
            String DateString = sqlDate + " 00:00:00";

        //Adds all of our parameters
        var stringList = cleanFields(arrayList);
        //1st param is index (not key), second is the data
        stringList.add(2, DateString);
        stringList.add(5, medCombo);
        stringList.add(6, phone);
        stringList.add(7, email.replaceAll("'", "''"));

        Main.DBS.registerUser(stringList);
    }
    //Cleans strings in a more clean and readable way
    public List<String> cleanFields(ArrayList<SimpleEntry<TextField, Boolean>> fields){
        List<String> fieldsText = new ArrayList<String>();
        for(SimpleEntry<TextField, Boolean> field : fields){
            if (field.getValue()){
                fieldsText.add(field.getKey().getText().toUpperCase().replaceAll("'", "''"));
            } 
            else{
                fieldsText.add(field.getKey().getText().replaceAll("'", "''"));
            } 
        }
        return fieldsText;
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
