package com.example.dbproject.controllers.VboxControllers;

import com.example.dbproject.Config.POJO.ProductAndOffice.OfficeEquipment;
import com.example.dbproject.Config.POJO.ProductAndOffice.Product;
import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.AddProductController.AddNewOffice;
import com.example.dbproject.controllers.AddProductController.AddProductController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductController implements Initializable {
    @FXML
    private Button UpdateOffice;
    @FXML
    private TableView<OfficeEquipment> ViewOffice;
    @FXML
    private TableColumn<OfficeEquipment, Integer> amount;

    @FXML
    private TableColumn<OfficeEquipment, Integer> count;
    @FXML
    private TableColumn<OfficeEquipment, Integer> id;

    @FXML
    private TableColumn<OfficeEquipment, String> name;
    @FXML
    private TextField SearchOFfice;

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
    @FXML
    private TableColumn<Product, Integer> CollAmount;

    @FXML
    private TableColumn<Product, String> CollCategory;

    @FXML
    private TableColumn<Product, Integer> CollPrice;

    @FXML
    private TableColumn<Product, String> CollProvider;

    @FXML
    private TableColumn<Product, String> ColumName;

    @FXML
    private TableColumn<Product, Integer> IdProduct;

    @FXML
    private Button EditProduct;

    @FXML
    private TableView<Product> ViewTabProduct;

    @FXML
    private Button addProduct;
    @FXML
    private TextField SearchProducr;
    @FXML
    private Button deleteProduct;

    ResultSet resultSet = null;
    Connection conn = null;
    Product product = null;
    OfficeEquipment office = null;

    PreparedStatement preparedStatement = null;
    DataBaseHandler dbHandler = new DataBaseHandler();

    private ObservableList<OfficeEquipment> officeEquipments = FXCollections.observableArrayList();
    private ObservableList<Product> products = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoTableProduct();
        infoTableOffice();
        RefViewTable();
        SortProduct();
        SortOffice();
    }
    // сортировка office
    public void SortOffice(){
        FilteredList<OfficeEquipment> filteredData = new FilteredList<>(officeEquipments, b -> true);

        SearchOFfice.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(office -> {

                // Если значение поиска отсутствует, отобразите все записи или любые записи, которые есть в данный момент. без изменений
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();

                if (office.getName().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true; // означает, что мы нашли совпадение в Name
                } else
                    return false; // нет совпадений по поиску

            });
        });

        SortedList<OfficeEquipment> sortedData = new SortedList<>(filteredData);

        // связать отсортированный результат с табличным представлением
        sortedData.comparatorProperty().bind(ViewOffice.comparatorProperty());

        // Примените отфильтрованные и отсортированные данные к представлению таблицы.
        ViewOffice.setItems(sortedData);
    }
    // сортировка product
    public void SortProduct() {
        FilteredList<Product> filteredData = new FilteredList<>(products, b -> true);

        SearchProducr.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {

                // Если значение поиска отсутствует, отобразите все записи или любые записи, которые есть в данный момент. без изменений
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();

                if (product.getName().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true; // означает, что мы нашли совпадение в NameProdect
                } else if (product.getCategory().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в Category
                } else if (product.getProvider().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в Provider
                } else
                    return false; // нет совпадений по поиску

            });
        });

        SortedList<Product> sortedData = new SortedList<>(filteredData);

        // связать отсортированный результат с табличным представлением
        sortedData.comparatorProperty().bind(ViewTabProduct.comparatorProperty());

        // Примените отфильтрованные и отсортированные данные к представлению таблицы.
        ViewTabProduct.setItems(sortedData);
    }
    // редактирование product
    @FXML
    void ActionEditProduct(ActionEvent event) {
        product = ViewTabProduct.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dbproject/ButProduct/EditNewProduct.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
        AddProductController addProductController = loader.getController();
        addProductController.Product(product.getName(),
                product.getAmount(),
                product.getPrice(),
                product.getCategory(),
                product.getProvider());
        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    // добавление product
    @FXML
    void ActionAddProduct(ActionEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/dbproject/ButProduct/AddNewProduct.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage(StageStyle.UTILITY);
//      stage.setTitle("Hello!");
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }
    // обновление таблицы product
    public void RefViewTable() {

        IdProduct.setCellValueFactory(new PropertyValueFactory<>("id"));
        ColumName.setCellValueFactory(new PropertyValueFactory<>("name"));
        CollAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        CollPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        CollCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        CollProvider.setCellValueFactory(new PropertyValueFactory<>("provider"));

        products = dbHandler.getProducts();
        ViewTabProduct.setItems(products);
    }
    // обновление таблицы office
    public void RefViewTableOffice(){
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        count.setCellValueFactory(new PropertyValueFactory<>("count"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));

        officeEquipments = dbHandler.getOfficeEquipment();
        ViewOffice.setItems(officeEquipments);
    }

    // удаление product
    @FXML
    void ActionDeleteProduct(ActionEvent event) {
        try {
            product = ViewTabProduct.getSelectionModel().getSelectedItem();
            String query = "delete from product where idproduct =" + product.getId();
            conn = dbHandler.getDbConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            RefViewTable();
        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }

    }
    // вывод информации из office
    public void infoTableOffice(){
        conn = dbHandler.getDbConnection();
        String query = "SELECT * FROM officeequipment";

        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()){
                Integer idOffice = resultSet.getInt("idOfficeEquipment");
                String name = resultSet.getString("Name");
                Integer amount = resultSet.getInt("amount");
                Integer count = resultSet.getInt("count");

                officeEquipments.add(new OfficeEquipment(idOffice, name,count,  amount));
            }
            id.setCellValueFactory(new PropertyValueFactory<OfficeEquipment,Integer>("id"));
            name.setCellValueFactory(new PropertyValueFactory<OfficeEquipment,String>("name"));
            count.setCellValueFactory(new PropertyValueFactory<OfficeEquipment,Integer>("count"));
            amount.setCellValueFactory(new PropertyValueFactory<OfficeEquipment,Integer>("amount"));



            ViewOffice.setItems(officeEquipments);
        }catch (SQLException e){
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // вывод информации из product
    public void infoTableProduct() {
        conn = dbHandler.getDbConnection();
        String query = "select * from product";
        try {
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer idproduct = resultSet.getInt("idproduct");
                String name = resultSet.getString("name");
                Integer price = resultSet.getInt("price");
                String category = resultSet.getString("category");
                String provider = resultSet.getString("provider");
                Integer amount = resultSet.getInt("amount");

                products.add(new Product(idproduct, name, price, amount, category, provider));
            }
            IdProduct.setCellValueFactory(new PropertyValueFactory<Product, Integer>("id"));
            ColumName.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
            CollAmount.setCellValueFactory(new PropertyValueFactory<Product, Integer>("amount"));
            CollPrice.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));
            CollCategory.setCellValueFactory(new PropertyValueFactory<Product, String>("category"));
            CollProvider.setCellValueFactory(new PropertyValueFactory<Product, String>("provider"));

            ViewTabProduct.setItems(products);

        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // добавление office
    @FXML
    void ActionAddOffice(ActionEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/dbproject/ButProduct/AddNewOffice.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage(StageStyle.UTILITY);
//      stage.setTitle("Hello!");
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();
    }
    // удаление office
    @FXML
    void ActionDeleteOffice(ActionEvent event) {
        try {
            office = ViewOffice.getSelectionModel().getSelectedItem();
            String query = "delete from officeequipment where idOfficeEquipment =" + office.getId();
            conn = dbHandler.getDbConnection();
            preparedStatement = conn.prepareStatement(query);
            preparedStatement.execute();
            RefViewTableOffice();
        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // редактирование office
    @FXML
    void ActionEditOffice(ActionEvent event) {
        office = ViewOffice.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dbproject/ButProduct/EditNewOffice.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
        AddNewOffice addNewOffice = loader.getController();
        addNewOffice.setUpdate(true);
        addNewOffice.Office(office.getName(),
                office.getCount(),
                office.getAmount());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void UpdateOffice(ActionEvent event) {
        RefViewTableOffice();
    }

    public void UpdateProduct(ActionEvent actionEvent) {
        RefViewTable();
    }
}
