package com.example.dbproject.controllers.AddProductController;
import com.example.dbproject.DB.DataBaseHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddNewOffice {

    @FXML
    private Button ButtonAddOffice;

    @FXML
    private TextField amount;

    @FXML
    private Button closeOffice;

    @FXML
    private TextField count;

    @FXML
    private TextField nameOffice;


    @FXML
    private Button ButtonEditOffice;

    @FXML
    private TextField amountEdit;

    @FXML
    private Button closeEditOffice;

    @FXML
    private TextField countEdit;

    @FXML
    private TextField nameEditOffice;

    private boolean update;
    DataBaseHandler db = new DataBaseHandler();
    PreparedStatement preparedStatement = null;
    Connection connection = null;


    public void Office(String name, int amount,int count){
        nameEditOffice.setText(name);
        amountEdit.setText(String.valueOf(amount));
        countEdit.setText(String.valueOf(count));
    }
    @FXML
    void ActionCloseEditOffice(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void ActionEditOffice(ActionEvent event) {
        try {
            connection = db.getDbConnection();
            String name = String.valueOf(nameEditOffice.getText());
            int amountOffice = Integer.parseInt(amountEdit.getText());
            int countOffice = Integer.parseInt(countEdit.getText());

            String sql = "UPDATE officeequipment set " +
                    "Name= '" + name + "',"+
                    "count= '" + countOffice + "'," +
                    "amount= '" + amountOffice + "'" +"WHERE Name= '" + name + "'";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e){
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE,null,e);

        }
    }


    @FXML
    void ActionAddOffice(ActionEvent event) {
        db.singUoOfficeEquipment(
                nameOffice.getText(),
                amount.getPrefColumnCount(),
                count.getPrefColumnCount());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void setUpdate(boolean b) {
        this.update = b;
    }

    @FXML
    void ActionCloseOffice(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

}
