package com.example.dbproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.dbproject.Config.POJO.User.User;
import com.example.dbproject.DB.DataBaseHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LogIN;

    @FXML
    private TextField TextLogin;

    @FXML
    private PasswordField TextPassword;
    @FXML
    private ImageView close;

    @FXML
    void initialize() {
        LogIN.setOnAction(Event -> {
            String loginText = TextLogin.getText().trim();
            String passwordText = TextPassword.getText().trim();

            if(!loginText.isEmpty() && !passwordText.isEmpty()) {
              loginUser(loginText,passwordText);
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка входа!");
                alert.setHeaderText("Не правильный ввод: логин , пароль!");
                alert.setContentText("Проверте введеные данные!");
                alert.showAndWait();
            }

        });
        close.setStyle("/com/example/dbproject/icons/close.png");
        close.setOnMouseClicked(event -> {
           Stage stage = (Stage) close.getScene().getWindow();
           stage.close();
        });

    }
    // реализация авторизации пользователя
    private void loginUser(String loginText, String passwordText)  {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        User user = new User();
        user.setUsername(loginText);
        user.setPassword(passwordText);
         ResultSet resultSet = dataBaseHandler.getUser(user);

         int counter =0;

         try {
             while (resultSet.next()){
                 counter++;
             }
         }catch (SQLException e){
             e.printStackTrace();
         }

         if(counter >= 1){
             openNewScene("/com/example/dbproject/MainPanel.fxml");
         }
    }

    //переход на новую страницу
    public void openNewScene(String window){
        LogIN.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));
        try {
            loader.load();
        }catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setMaximized(false);
//        stage.initStyle(StageStyle.UNDECORATED);

        stage.showAndWait();

    }
}