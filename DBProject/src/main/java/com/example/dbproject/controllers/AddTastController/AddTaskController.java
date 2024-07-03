package com.example.dbproject.controllers.AddTastController;
import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.VboxControllers.TaskController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AddTaskController implements Initializable {

    @FXML
    private DatePicker AddDateCreate;

    @FXML
    private DatePicker AddDateEnd;

    @FXML
    private TextArea AddDescription;

    @FXML
    private Hyperlink AddDraftTask;

    @FXML
    private TextField AddName;

    @FXML
    private ComboBox<String> AddStatus;

    @FXML
    private Button ButtonAddTask;

    @FXML
    private Button ButtonCloseTask;
    private boolean update;
    public void setUpdate(boolean b) {
        this.update = b;
    }
    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    ObservableList<String> status = FXCollections.observableArrayList("Текущие",
            "Завершенные",
            "На контроле");
    TaskController taskController = new TaskController();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    AddStatus.setItems(status);
    }

    @FXML
    void ActionAddTask(ActionEvent event) {
        dataBaseHandler.singUpTask(AddName.getText(),
                AddStatus.getValue(),
                Date.valueOf(AddDateCreate.getValue()),
                Date.valueOf(AddDateEnd.getValue()),
                AddDescription.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void ActionAddDraftTask(ActionEvent event) {
        dataBaseHandler.singUpDraftTask(AddName.getText(),
                AddStatus.getValue(),
                Date.valueOf(AddDateCreate.getValue()),
                Date.valueOf(AddDateEnd.getValue()),
                AddDescription.getText());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void ActionCloseTask(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }



}
