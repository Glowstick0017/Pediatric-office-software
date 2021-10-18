package com.memeteam.cse360project;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import com.memeteam.cse360project.orm.*;

public class Main extends Application {
    private static String currentUser;
    static int currentUserID;
    private static final String url = "jdbc:sqlite:database.db";
    public static DatabaseService DBS;
    @Override //JavaFX init parameters
    public void start(Stage stage) throws IOException {
        // load resources for scene
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        // define scene
        Scene scene = new Scene(fxmlLoader.load());
        // change scene title (toolbar title)
        stage.setTitle("Meme Team Medical Software");
        // set window to always display above all other windows
        stage.setAlwaysOnTop(true);
        // set window icon
        stage.getIcons().add(new Image(Objects.requireNonNull(Main.class.getResourceAsStream("med.png"))));
        // set the scene of the stage to what was defined
        stage.setScene(scene);
        // display
        stage.show();
    }

    public static void main(String[] args) throws SQLException {
        DBS = new DatabaseService(url); //Initiates database service
        DBS.CreateNewTable(); //Check if table exists
        
        launch();
    }

    //Kinda janky but it works
    public static void setCurrentUser(String cu) {
        currentUser = cu;
    }

    public static void setCurrentID(String cu) throws SQLException{
        Main.currentUserID = Main.DBS.GetUserByUsername(cu.toUpperCase(Locale.ROOT)).getId();
    }

    public String getCurrentUser() {
        return currentUser;
    }
}