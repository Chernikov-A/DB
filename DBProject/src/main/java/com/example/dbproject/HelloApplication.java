package com.example.dbproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("LoginPanel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 574, 406);
        stage.initStyle(StageStyle.UNDECORATED);


//      stage.setTitle("Hello!");
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}