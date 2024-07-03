package com.example.dbproject.controllers.AddTastController;

import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.AddDocumentController.AddDocumentController;
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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EditTaskController implements Initializable {


    @FXML
    private Button ButtonEditCloseTask;

    @FXML
    private Button ButtonEditTask;

    @FXML
    private DatePicker EditDateCreate;

    @FXML
    private DatePicker EditDateEnd;

    @FXML
    private TextArea EditDescription;

    @FXML
    private Hyperlink EditDraftTask;

    @FXML
    private TextField EditName;
    @FXML
    private ComboBox<String> EditStatus;
    private boolean update;
    Connection connection = null;
    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    PreparedStatement preparedStatement = null;
    TaskController taskController = new TaskController();

    ObservableList<String> status = FXCollections.observableArrayList("Текущие",
            "Завершенные",
            "На контроле");
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        EditStatus.setItems(status);
    }
    public void setUpdate(boolean b) {
        this.update = b;
    }

    @FXML
    void ActionEditCloseTask(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void ActionEditDraftTask(ActionEvent event) {
        dataBaseHandler.singUpDraftTask(EditName.getText(),
                EditStatus.getValue(),
                Date.valueOf(EditDateCreate.getValue()),
                Date.valueOf(EditDateEnd.getValue()),
                EditDescription.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    @FXML
    void ActionEditTask(ActionEvent event) {
        try {
        connection = dataBaseHandler.getDbConnection();
        String name = EditName.getText();
        String status = EditStatus.getValue();
        Date start = Date.valueOf(EditDateCreate.getValue());
        Date end = Date.valueOf(EditDateEnd.getValue());
        String description = EditDescription.getText();

        String sql = "UPDATE tasks set name= '" + name + "'," +
                "status= '" + status + "'," +
                "start_date= '" + start + "'," +
                "end_date= '" + end + "'," +
                "description= '" + description + "' WHERE name= '" + name + "' ";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    } catch (Exception e) {
        Logger.getLogger(EditTaskController.class.getName()).log(Level.SEVERE, null, e);
    }
    }
    public void TaskDraft(String nameDraft, String statusDraft, Date startDraft, Date endDraft, String descriptionDraft) {
        EditName.setText(nameDraft);
        EditStatus.setValue(statusDraft);
        EditDateCreate.setValue(startDraft.toLocalDate());
        EditDateEnd.setValue(endDraft.toLocalDate());
        EditDescription.setText(descriptionDraft);

    }

}
