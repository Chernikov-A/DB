package com.example.dbproject.controllers.AddDocumentController;

import com.example.dbproject.Config.POJO.Document.Document;
import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.VboxControllers.DocumentController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AddDocumentController implements Initializable {
    ObservableList<String> tip = FXCollections.observableArrayList("Организационные",
            "Распорядительные",
            "Информационно-справочные",
            "Учётно-расчетные (бухгалтерские)",
            "Договор");
    ObservableList<String> status = FXCollections.observableArrayList("Завершен",
            "Отклонен ",
            "Подтверждается получение",
            "Отсутствует получатель",
            "Готов к отправке ",
            "Подписан",
            "Подписывается");
    @FXML
    private Button DocADD;

    @FXML
    private Hyperlink DocAddDraft;

    @FXML
    private Button DocCancel;

    @FXML
    private DatePicker DocDateCreating;

    @FXML
    private DatePicker DocDateEx;

    @FXML
    private TextArea DocDescription;

    @FXML
    private TextField DocKontackt;

    @FXML
    private TextField DocKontragent;

    @FXML
    private TextField DocPrepared;

    @FXML
    private TextField DocSigned;

    @FXML
    private ComboBox<String> DocStatus;

    @FXML
    private TextField DocSum;

    @FXML
    private ComboBox<String> DocTip;

    @FXML
    private TextField NameDoc;

    DocumentController documentController = new DocumentController();
    Document document = null;
    private boolean update;
    DataBaseHandler dataBaseHandler = new DataBaseHandler();
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DocTip.setItems(tip);
        DocStatus.setItems(status);


    }

    @FXML
    void close(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void closeEdit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    // Добавление в таблицу document
    @FXML
    public void AddDocument(ActionEvent event) {
        dataBaseHandler.singUpDocument(NameDoc.getText(), DocDescription.getText(), Date.valueOf(DocDateCreating.getValue()), Date.valueOf(DocDateEx.getValue()),
                DocStatus.getValue(), DocPrepared.getText(), DocSigned.getText(), DocKontackt.getText(), DocSum.getPrefColumnCount(),
                DocTip.getValue(), DocKontragent.getText());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

    }

    // Добавление документа в таблицу draft_documents
    @FXML
    void ActionDocDraft(ActionEvent event) {
        dataBaseHandler.DraftDocument(NameDoc.getText(), DocDescription.getText(), Date.valueOf(DocDateCreating.getValue()), Date.valueOf(DocDateEx.getValue()),
                DocStatus.getValue(), DocPrepared.getText(), DocSigned.getText(), DocKontackt.getText(), DocSum.getPrefColumnCount(),
                DocTip.getValue(), DocKontragent.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }
    @FXML
    void MainListDocEditDraft(ActionEvent event) {
        dataBaseHandler.DraftDocument(NameDoc.getText(), DocDescription.getText(), Date.valueOf(DocDateCreating.getValue()), Date.valueOf(DocDateEx.getValue()),
                DocStatus.getValue(), DocPrepared.getText(), DocSigned.getText(), DocKontackt.getText(), DocSum.getPrefColumnCount(),
                DocTip.getValue(), DocKontragent.getText());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


    public void setUpdate(boolean b) {
        this.update = b;
    }

    // определение для редактирования для  document
    public void Document(String nameDocumenta, String description, Date dataCreation,
                         Date responsePreparationЕime, String status, String prepared,
                         String signed, String contact, int summa, String tipDocumenta, String counterparty) {

        NameDoc.setText(nameDocumenta);
        DocTip.setValue(tipDocumenta);
        DocStatus.setValue(status);
        DocPrepared.setText(prepared);
        DocSigned.setText(signed);
        DocKontackt.setText(contact);
        DocDateCreating.setValue(dataCreation.toLocalDate());
        DocDescription.setText(description);
        DocDateEx.setValue(responsePreparationЕime.toLocalDate());
        DocSum.setText(String.valueOf(summa));
        DocKontragent.setText(counterparty);

    }
    // редактирование document
    @FXML
    void EditDocument(ActionEvent event) {
        try {
            connection = dataBaseHandler.getDbConnection();
            String nameDoc = NameDoc.getText();
            String tipDoc = DocTip.getValue();
            String status = DocStatus.getValue();
            String prepared = DocPrepared.getText();
            String signed = DocSigned.getText();
            int kontact = Integer.parseInt(DocKontackt.getText());
            Date dataCreation = Date.valueOf(DocDateCreating.getValue());
            String description = DocDescription.getText();
            Date responsePreparation = Date.valueOf(DocDateEx.getValue());
            int summa = Integer.valueOf(DocSum.getText());
            String counterparty = DocKontragent.getText();

            String sql = "UPDATE document set  " +
                    "Name_Document= '" + nameDoc + "'," +
                    "Description= '" + description + "'," +
                    "date_of_creation= '" + dataCreation + "'," +
                    "Execution_date= '" + responsePreparation + "'," +
                    "Current_status= '" + status + "'," +
                    "prepared= '" + prepared + "'," +
                    "Signed= '" + signed + "'," +
                    "contact= '" + kontact + "'," +
                    "sum= '" + summa + "'," +
                    "tip_documenta= '" + tipDoc + "'," +
                    "kontragent= '" + counterparty + "' WHERE Name_Document= '" + nameDoc + "' ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            Logger.getLogger(AddDocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

}
