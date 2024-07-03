package com.example.dbproject.controllers.AddTastController;
import com.example.dbproject.DB.DataBaseHandler;
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
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DraftEditTrastController implements Initializable {


    @FXML
    private Button ButtonDraftEditCloseTask;

    @FXML
    private Button ButtonDraftEditTask;

    @FXML
    private DatePicker DraftEditDateCreate;

    @FXML
    private DatePicker DraftEditDateEnd;

    @FXML
    private TextArea DraftEditDescription;

    @FXML
    private TextField DraftEditName;

    @FXML
    private ComboBox<String> DraftEditStatus;
    private boolean update;
    @FXML
    private Hyperlink EditDraftTask;
    ObservableList<String> status = FXCollections.observableArrayList("Текущие",
            "Завершенные",
            "На контроле");
    Connection connection = null;
    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    PreparedStatement preparedStatement = null;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DraftEditStatus.setItems(status);
    }
    @FXML
    void ActionDraftEditCloseTask(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void ActionDraftEditTask(ActionEvent event) {
        try {
            connection = dataBaseHandler.getDbConnection();
            String name = DraftEditName.getText();
            String status = DraftEditStatus.getValue();
            Date start = Date.valueOf(DraftEditDateCreate.getValue());
            Date end = Date.valueOf(DraftEditDateEnd.getValue());
            String description = DraftEditDescription.getText();

            String sql = "UPDATE draft_task set draft_name= '" + name + "'," +
                    "draft_status= '" + status + "'," +
                    "draft_start_date= '" + start + "'," +
                    "draft_end_date= '" + end + "'," +
                    "draft_description= '" + description + "' WHERE draft_name= '" + name + "' ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
    }catch (SQLException e) {
            Logger.getLogger(DraftEditTrastController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    @FXML
    void ActionEditDraftDraftTask(ActionEvent event) {
        dataBaseHandler.singUpTask(DraftEditName.getText(), DraftEditStatus.getValue(), Date.valueOf(DraftEditDateCreate.getValue()),
                Date.valueOf(DraftEditDateEnd.getValue()), DraftEditDescription.getText());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    public void DraftTaskDraft(String nameDraft, String statusDraft, Date startDraft, Date endDraft, String descriptionDraft) {
        DraftEditName.setText(nameDraft);
        DraftEditStatus.setValue(statusDraft);
        DraftEditDateCreate.setValue(startDraft.toLocalDate());
        DraftEditDateEnd.setValue(endDraft.toLocalDate());
        DraftEditDescription.setText(descriptionDraft);

    }
    public void setUpdate(boolean b) {
        this.update = b;
    }

}
