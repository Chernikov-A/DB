package com.example.dbproject.controllers.AddDocumentController;

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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DraftDocumentController implements Initializable {
    @FXML
    private Button DocCancelDraft;

    @FXML
    private DatePicker DocDateCreatingDraft;

    @FXML
    private DatePicker DocDateExDraft;

    @FXML
    private TextArea DocDescriptionDraft;

    @FXML
    private Button DocEditDraft;

    @FXML
    private TextField DocKontacktDraft;

    @FXML
    private TextField DocKontragentDraft;

    @FXML
    private TextField DocPreparedDraft;

    @FXML
    private TextField DocSignedDraft;

    @FXML
    private ComboBox<String> DocStatusDraft;

    @FXML
    private TextField DocSumDraft;

    @FXML
    private ComboBox<String> DocTipDraft;

    @FXML
    private TextField NameDocDraft;

    @FXML
    private Hyperlink mainListDoc;

    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    private boolean update;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ObservableList<String> tipDraft = FXCollections.observableArrayList("Организационные",
            "Распорядительные",
            "Информационно-справочные",
            "Учётно-расчетные (бухгалтерские)",
            "Договор");
    ObservableList<String> statusDraft = FXCollections.observableArrayList("Завершен",
            "Отклонен ",
            "Подтверждается получение",
            "Отсутствует получатель",
            "Готов к отправке ",
            "Подписан",
            "Подписывается");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DocTipDraft.setItems(tipDraft);
        DocStatusDraft.setItems(statusDraft);
    }
    @FXML
    void ActionMainListDocuments(ActionEvent event) {
    }

    @FXML
    void EditDocument(ActionEvent event) {
        try {
            connection = dataBaseHandler.getDbConnection();
            String nameDoc = NameDocDraft.getText();
            String tipDoc = DocTipDraft.getValue();
            String status = DocStatusDraft.getValue();
            String prepared = DocPreparedDraft.getText();
            String signed = DocSignedDraft.getText();
            int kontact = Integer.parseInt(DocKontacktDraft.getText());
            Date dataCreation = Date.valueOf(DocDateCreatingDraft.getValue());
            String description = DocDescriptionDraft.getText();
            Date responsePreparation = Date.valueOf(DocDateExDraft.getValue());
            int summa = Integer.valueOf(DocSumDraft.getText());
            String counterparty = DocKontragentDraft.getText();

            String sql = "UPDATE draft_documents set Draft_NameDocument= '" + nameDoc + "'," +
                    "Draft_Description= '" + description + "'," +
                    "Draft_dateOfCreation= '" + dataCreation + "'," +
                    "Draft_ExecutionDate= '" + responsePreparation + "'," +
                    "Draft_CurrentStatus= '" + status + "'," +
                    "Draft_Prepared= '" + prepared + "'," +
                    "Draft_Signed= '" + signed + "'," +
                    "Draft_Contact= '" + kontact + "'," +
                    "Draft_sum= '" + summa + "'," +
                    "Draft_TipDocumenta= '" + tipDoc + "'," +
                    "Draft_Kontragent= '" + counterparty + "' WHERE Draft_NameDocument= '" + nameDoc + "' ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            Logger.getLogger(AddDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    void closeEdit(ActionEvent event) {

    }
    public void setUpdate(boolean b) {
        this.update = b;
    }

    public void DraftDocument(String nameDocumenta, String description, Date dataCreation,
                         Date responsePreparationЕime, String status, String prepared,
                         String signed, String contact, int summa, String tipDocumenta, String counterparty) {

        NameDocDraft.setText(nameDocumenta);
        DocTipDraft.setValue(tipDocumenta);
        DocStatusDraft.setValue(status);
        DocPreparedDraft.setText(prepared);
        DocSignedDraft.setText(signed);
        DocKontacktDraft.setText(contact);
        DocDateCreatingDraft.setValue(dataCreation.toLocalDate());
        DocDescriptionDraft.setText(description);
        DocDateExDraft.setValue(responsePreparationЕime.toLocalDate());
        DocSumDraft.setText(String.valueOf(summa));
        DocKontragentDraft.setText(counterparty);

    }

}

