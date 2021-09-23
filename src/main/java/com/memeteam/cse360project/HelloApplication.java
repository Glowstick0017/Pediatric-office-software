package com.memeteam.cse360project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // load resources for scene
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));

        // define scene
        Scene scene = new Scene(fxmlLoader.load(), 500, 350);

        // change scene title (toolbar title)
        stage.setTitle("Meme Team Medical Software");

        // set window to always display above all other windows
        stage.setAlwaysOnTop(true);

        // set window icon
        stage.getIcons().add(new Image(Objects.requireNonNull(HelloApplication.class.getResourceAsStream("med.png"))));

        // set the scene of the stage to what was defined
        stage.setScene(scene);

        // display
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}