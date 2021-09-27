package com.memeteam.cse360project;

import javafx.application.Application;
import javafx.application.ConditionalFeature;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.sql.*;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    private static String currentUser;

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
        createNewTable();
        launch();
    }

    public static void createNewTable() {
        // SQLite connection string
        String url = "jdbc:sqlite:database.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS patients (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	firstname text,\n"
                + "	lastname text,\n"
                + "	age int,\n"
                + "	username text,\n"
                + "	password text,\n"
                + "	medical text,\n"
                + "	phone text,\n"
                + "	email text,\n"
                + "	message text,\n"
                + "	weight text,\n"
                + "	height text,\n"
                + "	temperature text,\n"
                + "	bloodpressure text,\n"
                + "	nursenotes text,\n"
                + "	doctornotes text\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public static void setCurrentUser(String cu) {
        currentUser = cu;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}