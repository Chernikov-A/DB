package com.example.dbproject.controllers.VboxControllers;

import com.example.dbproject.Config.POJO.ProductAndOffice.Product;
import com.example.dbproject.Config.POJO.Task.Task;
import com.example.dbproject.Config.POJO.Task.TaskDraft;
import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.AddProductController.AddNewOffice;
import com.example.dbproject.controllers.AddTastController.AddTaskController;
import com.example.dbproject.controllers.AddTastController.DraftEditTrastController;
import com.example.dbproject.controllers.AddTastController.EditTaskController;
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

public class TaskController implements Initializable {

    @FXML
    private Button ButAddCurrentTask;

    @FXML
    private Button ButDeleteCurrentTask;

    @FXML
    private Button ButDeleteDraftsTask;

    @FXML
    private Button ButEditCurrentTast;

    @FXML
    private Button ButEditDraftsTask;

    @FXML
    private TextField SearchDraftTask;

    @FXML
    private TextField SearchTask;

    @FXML
    private Button UpdateDraftTask;
    @FXML
    private TableView<TaskDraft> ViewDraftTask;

    @FXML
    private TableView<Task> ViewTasks;
    @FXML
    private TableColumn<Task, Integer> id;

    @FXML
    private TableColumn<TaskDraft, Integer> idDraft;

    @FXML
    private TableColumn<TaskDraft, String> descriptionDraftTask;

    @FXML
    private TableColumn<Task, String> descriptionTask;

    @FXML
    private TableColumn<TaskDraft, Date> endDateDraftTask;

    @FXML
    private TableColumn<Task, Date> endDateTask;

    @FXML
    private TableColumn<TaskDraft, String> nameDraftTask;

    @FXML
    private TableColumn<Task, String> nameTask;

    @FXML
    private TableColumn<TaskDraft, Date> startDateDraftTask;

    @FXML
    private TableColumn<TaskDraft, String> statusDraftTask;

    @FXML
    private TableColumn<Task, String> statusTask;

    @FXML
    private TableColumn<Task, Date> startDateTask;

    @FXML
    private Button updateTask;

    Connection connection = null;
    Task task = null;
    TaskDraft taskDraft = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    DataBaseHandler dataBaseHandler = new DataBaseHandler();

    ObservableList<Task> tasks = FXCollections.observableArrayList();
    ObservableList<TaskDraft> taskDrafts = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoTableTask();
        infoTableDraftTask();
        UpdateDraftTask();
        UpdateTask();
        SortViewTask();
        sortViewDraftTask();
    }
    // отображение данных из draft_task
    public void infoTableDraftTask(){
        connection = dataBaseHandler.getDbConnection();
        String query = "select * from draft_task";
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer draft_idTasks = resultSet.getInt("iddraft_Task");
                String draft_name = resultSet.getString("draft_name");
                String draft_status = resultSet.getString("draft_status");
                Date draft_start_date = resultSet.getDate("draft_start_date");
                Date draft_end_date = resultSet.getDate("draft_end_date");
                String draft_description = resultSet.getString("draft_description");

                taskDrafts.add(new TaskDraft(draft_idTasks,draft_name,draft_status,draft_start_date,draft_end_date,draft_description));
            }
            UpdateDraftTask();


        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // отображение данных tasks
    public void infoTableTask(){
        connection = dataBaseHandler.getDbConnection();
        String query = "select * from tasks";
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Integer idTasks = resultSet.getInt("idTasks");
                String name = resultSet.getString("name");
                String status = resultSet.getString("status");
                Date start_date = resultSet.getDate("start_date");
                Date end_date = resultSet.getDate("end_date");
                String description = resultSet.getString("description");

                tasks.add(new Task(idTasks,name,status,start_date,end_date,description));
            }
            UpdateTask();

        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // добавление в таблицу tasks
    @FXML
    void ActionAddTask(ActionEvent event) {
        Parent fxmlLoader = null;
        try {
            fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/dbproject/ButTask/AddNewTask.fxml")));
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
    // удаление данных из табдицы draft_task
    @FXML
     void ActionDeleteDraftTask(ActionEvent event) {
        try {
            taskDraft = ViewDraftTask.getSelectionModel().getSelectedItem();
            String query = "delete from draft_task where iddraft_Task =" + taskDraft.getId();
            connection = dataBaseHandler.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            UpdateDraftTask();
        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // удаление данных из таблицы tasks
    @FXML
    void ActionDeleteTask(ActionEvent event) {
        try {
            task = ViewTasks.getSelectionModel().getSelectedItem();
            String query = "delete from tasks where idTasks =" + task.getId();
            connection = dataBaseHandler.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            UpdateTask();
        } catch (SQLException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // редактирование данных таблицыв draft_task
    @FXML
    void ActionEditDraftTask(ActionEvent event) {
        taskDraft = ViewDraftTask.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dbproject/ButTask/DraftEditNewTask.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
        DraftEditTrastController draftEditTrastController = loader.getController();
        draftEditTrastController.setUpdate(true);
        draftEditTrastController.DraftTaskDraft(taskDraft.getNameDraft(),
                taskDraft.getStatusDraft(),
                taskDraft.getStartDraft(),
                taskDraft.getEndDraft(),
                taskDraft.getDescriptionDraft());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }
    // редактирование данных таблицы tasks
    @FXML
    void ActionEditTask(ActionEvent event) {
        task = ViewTasks.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dbproject/ButTask/EditNewTask.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(ProductController.class.getName()).log(Level.SEVERE, null, e);
        }
        EditTaskController editTaskController = loader.getController();
        editTaskController.setUpdate(true);
        editTaskController.TaskDraft(task.getName(),
                task.getStatus(),
                task.getStart(),
                task.getEnd(),
                task.getDescription());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

    @FXML
    void ActionUpdateDraftTask(ActionEvent event) {
        UpdateDraftTask();
    }

    @FXML
    void ActionUpdateTask(ActionEvent event) {
        UpdateTask();
    }
    // обновление ViewTable из таблицы draft_task
    public void UpdateDraftTask(){
        idDraft.setCellValueFactory(new PropertyValueFactory<TaskDraft, Integer>("id"));
        nameDraftTask.setCellValueFactory(new PropertyValueFactory<TaskDraft, String>("nameDraft"));
        statusDraftTask.setCellValueFactory(new PropertyValueFactory<TaskDraft, String>("statusDraft"));
        startDateDraftTask.setCellValueFactory(new PropertyValueFactory<TaskDraft, Date>("startDraft"));
        endDateDraftTask.setCellValueFactory(new PropertyValueFactory<TaskDraft, Date>("endDraft"));
        descriptionDraftTask.setCellValueFactory(new PropertyValueFactory<TaskDraft, String>("descriptionDraft"));

        taskDrafts = dataBaseHandler.getTaskDrafts();
        ViewDraftTask.setItems(taskDrafts);
    }
    // обновление ViewTable из таблицы tasks
    public void UpdateTask(){
        id.setCellValueFactory(new PropertyValueFactory<Task, Integer>("id"));
        nameTask.setCellValueFactory(new PropertyValueFactory<Task, String>("name"));
        statusTask.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        startDateTask.setCellValueFactory(new PropertyValueFactory<Task,Date>("start"));
        endDateTask.setCellValueFactory(new PropertyValueFactory<Task, Date>("end"));
        descriptionTask.setCellValueFactory(new PropertyValueFactory<Task, String>("description"));
        tasks = dataBaseHandler.getTasks();
        ViewTasks.setItems(tasks);
    }
    // реализовать метод поиска данных таблицы tasks
    public void SortViewTask (){
        FilteredList<Task> filteredData = new FilteredList<>(tasks, b -> true);

        SearchTask.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(task -> {

                // Если значение поиска отсутствует, отобразите все записи или любые записи, которые есть в данный момент. без изменений
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();

                if (task.getName().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true; // означает, что мы нашли совпадение в Name
                } else {
                    return false;
                }
            });
        });

        SortedList<Task> sortedData = new SortedList<>(filteredData);

        // связать отсортированный результат с табличным представлением
        sortedData.comparatorProperty().bind(ViewTasks.comparatorProperty());

        // Примените отфильтрованные и отсортированные данные к представлению таблицы.
        ViewTasks.setItems(sortedData);
    }
    public void sortViewDraftTask(){
        FilteredList<TaskDraft> filteredData = new FilteredList<>(taskDrafts, b -> true);

        SearchDraftTask.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(taskDraft -> {

                // Если значение поиска отсутствует, отобразите все записи или любые записи, которые есть в данный момент. без изменений
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();

                if (taskDraft.getNameDraft().toLowerCase().contains(searchKeyWord)) {
                    return true; // означает, что мы нашли совпадение в Name
                } else {
                    return false;
                }
            });
        });

        SortedList<TaskDraft> sortedData = new SortedList<>(filteredData);

        // связать отсортированный результат с табличным представлением
        sortedData.comparatorProperty().bind(ViewDraftTask.comparatorProperty());

        // Примените отфильтрованные и отсортированные данные к представлению таблицы.
        ViewDraftTask.setItems(sortedData);
    }
}
