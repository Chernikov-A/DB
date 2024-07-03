package com.example.dbproject.DB;

import com.example.dbproject.Config.*;
import com.example.dbproject.Config.POJO.Document.Document;
import com.example.dbproject.Config.POJO.Document.DraftDocument;
import com.example.dbproject.Config.POJO.ProductAndOffice.OfficeEquipment;
import com.example.dbproject.Config.POJO.ProductAndOffice.Product;
import com.example.dbproject.Config.POJO.Task.Task;
import com.example.dbproject.Config.POJO.Task.TaskDraft;
import com.example.dbproject.Config.POJO.User.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseHandler extends Configs {
    Connection dbConnection = null;
    ResultSet rs = null;

    // подключение к бд
    public Connection getDbConnection() {
        try {
            String connectionString = "jdbc:mysql://" + dbHoost + ":" + dbPort + "/" + dbName;
            Class.forName("com.mysql.cj.jdbc.Driver");

            dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
            return dbConnection;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ResultSet getUser(User user) {
        ResultSet rs = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_NAME + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            rs = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public void singUpDocument(String name, String description,
                               Date date_creating, Date execution, String status,
                               String prepared, String signed,
                               String contact, int sum, String tip, String kontragent) {


        String insert = "INSERT INTO " + Const.DOCUMENT_TABLE + "(" + Const.DOCUMENT_NAME + "," + Const.DOCUMENT_DESCRIPTION + "," +
                Const.DOCUMENT_CREATION_DATE + "," + Const.DOCUMENT_EXECUTION_DATE + "," + Const.DOCUMENT_CURRENT_STATUS + "," +
                Const.DOCUMENT_PREPARED + "," + Const.DOCUMENT_SIGNED + "," + Const.DOCUMENT_CONTACT + "," + Const.DOCUMENT_SUM + "," +
                Const.DOCUMENT_TIPS + "," + Const.DOCUMENT_KONTRAGENT + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDate(3, date_creating);
            statement.setDate(4, execution);
            statement.setString(5, status);
            statement.setString(6, prepared);
            statement.setString(7, signed);
            statement.setString(8, contact);
            statement.setInt(9, sum);
            statement.setString(10, tip);
            statement.setString(11, kontragent);

            statement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void singUoOfficeEquipment (String name,int amount,int count){
        String insert = "INSERT INTO " + Const.OFFICE_TABLE + "(" + Const.OFFICE_NAME + "," +Const.OFFICE_COUNT + "," + Const.OFFICE_AMOUNT + ")" + "VALUES(?,?,?)";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1,name);
            statement.setInt(2, amount);
            statement.setInt(3, count);

            statement.executeUpdate();
        }catch (SQLException e){
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void singUpTask(String name, String status, Date start_date, Date end_date, String description ){
        String insert = "INSERT INTO " + Const.TASKS_TABLE + "(" +Const.TASKS_NAME +"," + Const.TASKS_STATUS +","+
                Const.TASKS_START_DATE + "," + Const.TASKS_END_DATE + "," + Const.TASKS_DESCRIPTION + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1,name);
            statement.setString(2,status);
            statement.setDate(3,start_date);
            statement.setDate(4,end_date);
            statement.setString(5,description);

            statement.executeUpdate();
        }catch (SQLException e){
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    public void singUpDraftTask(String draft_name, String draft_status, Date draft_start_date, Date draft_end_date, String draft_description){
        String insert = "INSERT INTO " + Const.DRAFT_TASKS_TABLE + "(" + Const.DRAFT_TASKS_NAME + "," +
                Const.DRAFT_TASKS_STATUS + "," + Const.DRAFT_TASKS_START_DATE + "," + Const.DRAFT_TASKS_END_DATE + "," + Const.DRAFT_TASKS_DESCRIPTION + ")" + "VALUES(?,?,?,?,?)";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1,draft_name);
            statement.setString(2,draft_status);
            statement.setDate(3,draft_start_date);
            statement.setDate(4,draft_end_date);
            statement.setString(5,draft_description);

            statement.executeUpdate();
        }catch (SQLException e){
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public void DraftDocument(String name, String description,
                              Date date_creating, Date execution, String status,
                              String prepared, String signed,
                              String contact, int sum, String tip, String kontragent) {
        String insert = "INSERT INTO " + Const.DRAFT_DOCUMENT_TABLE + "(" + Const.DRAFT_DOCUMENT_NAME + "," + Const.DRAFT_DOCUMENT_DESCRIPTION + "," +
                Const.DRAFT_DOCUMENT_CREATION_DATE + "," + Const.DRAFT_DOCUMENT_EXECUTION_DATE + "," + Const.DRAFT_DOCUMENT_CURRENT_STATUS + "," +
                Const.DRAFT_DOCUMENT_PREPARED + "," + Const.DRAFT_DOCUMENT_SIGNED + "," + Const.DRAFT_DOCUMENT_CONTACT + "," + Const.DRAFT_DOCUMENT_SUM + "," +
                Const.DRAFT_DOCUMENT_TIPS + "," + Const.DRAFT_DOCUMENT_KONTRAGENT + ")" + "VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setDate(3, date_creating);
            statement.setDate(4, execution);
            statement.setString(5, status);
            statement.setString(6, prepared);
            statement.setString(7, signed);
            statement.setString(8, contact);
            statement.setInt(9, sum);
            statement.setString(10, tip);
            statement.setString(11, kontragent);

            statement.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    public void singUpProduct(String name, int price, int amount, String category, String provider) {
        String insert = "INSERT INTO " + Const.PRODUCT_TABLE + "(" + Const.PRODUCT_NAME + ","
                + Const.PRODUCT_AMOUNT + "," + Const.PRODUCT_PRICE + "," + Const.PRODUCT_CATEGORY + ","
                + Const.PRODUCT_PROVIDER + ")" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);
            statement.setString(1, name);
            statement.setInt(2, amount);
            statement.setInt(3, price);
            statement.setString(4, category);
            statement.setString(5, provider);

            statement.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
    }


    public ObservableList<Product> getProducts() {
        dbConnection = getDbConnection();
        ObservableList<Product> products = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement("SELECT * FROM product");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                products.add(new Product(Integer.parseInt(rs.getString("idproduct")),
                        rs.getString("name"),
                        rs.getInt("amount"),
                        rs.getInt("price"),
                        rs.getString("category"),
                        rs.getString("provider")));
            }
        } catch (Exception e) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        return products;
    }
    public ObservableList<Task> getTasks(){
        dbConnection = getDbConnection();
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement("SELECT * FROM tasks");
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                tasks.add(new Task(Integer.parseInt(rs.getString("idTasks")),
                        rs.getString("name"),
                        rs.getString("status"),
                        rs.getDate("start_date"),
                        rs.getDate("end_date"),
                        rs.getString("description")));
            }
        }catch (SQLException e){
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        return tasks;
    }
    public ObservableList<TaskDraft> getTaskDrafts(){
        dbConnection = getDbConnection();
        ObservableList<TaskDraft> taskDrafts = FXCollections.observableArrayList();
        try{
            PreparedStatement preparedStatement = getDbConnection().prepareStatement("SELECT * FROM draft_task");
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                taskDrafts.add(new TaskDraft(Integer.parseInt(rs.getString("iddraft_Task")),
                        rs.getString("draft_name"),
                        rs.getString("draft_status"),
                        rs.getDate("draft_start_date"),
                        rs.getDate("draft_end_date"),
                        rs.getString("draft_description")));
            }
        }catch (SQLException e){
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        return taskDrafts;
    }

    public ObservableList<OfficeEquipment> getOfficeEquipment(){
        dbConnection = getDbConnection();
        ObservableList<OfficeEquipment> officeEquipments = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement("SELECT * FROM officeequipment");
            rs = preparedStatement.executeQuery();

            while (rs.next()){
                officeEquipments.add(new OfficeEquipment(Integer.parseInt(rs.getString("idOfficeEquipment")),
                        rs.getString("name"),
                        rs.getInt("count"),
                        rs.getInt("amount")));
            }
        }catch (Exception e){
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        return officeEquipments;
    }

    public ObservableList<DraftDocument> getDraftDocuments() {
        Connection connection = getDbConnection();
        ObservableList<DraftDocument> draftDocuments = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM draft_documents");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                draftDocuments.add(new DraftDocument(Integer.parseInt(rs.getString("idDraft_documents")),
                        rs.getString("Draft_NameDocument"),
                        rs.getString("Draft_Description"),
                        rs.getDate("Draft_dateOfCreation"),
                        rs.getDate("Draft_ExecutionDate"),
                        rs.getString("Draft_CurrentStatus"),
                        rs.getString("Draft_Prepared"),
                        rs.getString("Draft_Signed"),
                        rs.getString("Draft_Contact"),
                        rs.getInt("Draft_sum"),
                        rs.getString("Draft_TipDocumenta"),
                        rs.getString("Draft_Kontragent")));
            }
        } catch (Exception e) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        return draftDocuments;
    }

    public ObservableList<Document> getDataDocument() {
        Connection connection = getDbConnection();
        ObservableList<Document> documents = FXCollections.observableArrayList();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM document");
            rs = preparedStatement.executeQuery();

            while (rs.next()) {
                documents.add(new Document(Integer.parseInt(rs.getString("idDocument")),
                        rs.getString("Name_Document"),
                        rs.getString("Description"),
                        rs.getDate("date_of_creation"),
                        rs.getDate("Execution_date"),
                        rs.getString("Current_status"),
                        rs.getString("prepared"),
                        rs.getString("Signed"),
                        rs.getString("contact"),
                        rs.getInt("sum"),
                        rs.getString("tip_documenta"),
                        rs.getString("kontragent")));
            }
        } catch (Exception e) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, e);
        }
        return documents;
    }

}



