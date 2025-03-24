package com.example.homework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloApplication extends Application {

    private final String URL = "jdbc:mysql://127.0.0.1:3306/school";
    private final String USER = "root";
    private final String PASSWORD = "5507";

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("student.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Student Management");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
