package com.memeteam.cse360project;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.Connection;

import java.io.IOException;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class Main extends Application {
    @Override
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

    public static void main(String[] args) {
        launch();
    }

    public static Connection connect() {
        String url = "jdbc:sqlite:database.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
}