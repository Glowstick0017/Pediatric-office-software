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
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        // define scene
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        // change scene title (toolbar title)
        stage.setTitle("Hello!");

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