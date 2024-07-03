package com.example.dbproject.controllers.AddProductController;

import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.VboxControllers.ProductController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddProductController  {
    @FXML
    private Button ButtonAddProduct;

    @FXML
    private Button ButtonExitProduct;

    @FXML
    private TextField TextName;

    @FXML
    private TextField textAmount;

    @FXML
    private TextField textCategory;

    @FXML
    private TextField textPrice;

    @FXML
    private TextField textProvide;

    private boolean update;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    DataBaseHandler db = new DataBaseHandler();

    // Определение для редактирование product
    public void Product(String name,int amount,int price,String category,String provide){
        TextName.setText(name);
        textAmount.setText(String.valueOf(amount));
        textPrice.setText(String.valueOf(price));
        textCategory.setText(category);
        textProvide.setText(provide);
    }
    // Добавление в product
    @FXML
    void AddProduct(ActionEvent event) {
        db.singUpProduct(TextName.getText(),
                textAmount.getPrefColumnCount(),
                textPrice.getPrefColumnCount(),
                textCategory.getText(),
                textProvide.getText());


        Stage stage = (Stage) ButtonAddProduct.getScene().getWindow();
        stage.close();

    }
    public void setUpdate(boolean b) {
        this.update = b;
    }
    // редактирование product
    @FXML
    void EditProduct(ActionEvent event) {
        try {
            connection = db.getDbConnection();

            String name = TextName.getText();
            int amount = Integer.parseInt(textAmount.getText());
            int price = Integer.parseInt(textPrice.getText());
            String category = textCategory.getText();
            String provide = textProvide.getText();

            String sql = "UPDATE product set " +
                    "name= '" + name + "'," +
                    "amount= '" + amount + "'," +
                    "price= '" + price + "'," +
                    "category= '" + category + "'," +
                    "provider= '" + provide + "'WHERE name= '" + name +"'";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        }catch (Exception e){
            Logger.getLogger(AddProductController.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    @FXML
    void ExitEditProduct(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void ExitProduct(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
}
