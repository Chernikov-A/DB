package com.example.dbproject.controllers.VboxControllers;

import com.example.dbproject.Config.POJO.Document.Document;
import com.example.dbproject.Config.POJO.Document.DraftDocument;
import com.example.dbproject.DB.DataBaseHandler;
import com.example.dbproject.controllers.AddDocumentController.AddDocumentController;
import com.example.dbproject.controllers.AddDocumentController.DraftDocumentController;
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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentController implements Initializable {
    @FXML
    private TableView<DraftDocument> DraftViewDoc;
    @FXML
    private TableColumn<DraftDocument, String> IdDraftPrepared;

    @FXML
    private TableColumn<DraftDocument, Date> IdDraftResponsePreparationЕime;
    @FXML
    private TableColumn<DraftDocument, String> idDraftContact;

    @FXML
    private TableColumn<DraftDocument, String> idDraftCounterparty;

    @FXML
    private TableColumn<DraftDocument, Date> idDraftDataCreation;

    @FXML
    private TableColumn<DraftDocument, String> idDraftDescription;

    @FXML
    private TableColumn<DraftDocument, Integer> idDraftDoc;

    @FXML
    private TableColumn<DraftDocument, String> idDraftNameDocumenta;

    @FXML
    private TableColumn<DraftDocument, String> idDraftSigned;

    @FXML
    private TableColumn<DraftDocument, String> idDraftStatus;

    @FXML
    private TableColumn<DraftDocument, Integer> idDraftSumma;
    @FXML
    private TableColumn<DraftDocument, String> DraftidTipDocumenta;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ButAddDocument;

    @FXML
    private Button ButDeleteConfirmedDocuments;

    @FXML
    private Button ButDeleteDocument;

    @FXML
    private Button ButDeleteDraftDocuments;

    @FXML
    private Button ButDeleteListOfUnconfirmedDocuments;

    @FXML
    private Button ButEditDocument;

    @FXML
    private Button ButEditDraftDocuments;

    @FXML
    private Button ButEditListOfUnconfirmedDocuments;

    @FXML
    private TableColumn<Document, String> IdPrepared;

    @FXML
    private TableColumn<Document, Date> IdResponsePreparationЕime;

    @FXML
    private TableColumn<Document, String> idContact;

    @FXML
    private TableColumn<Document, String> idCounterparty;

    @FXML
    private TableColumn<Document, Date> idDataCreation;

    @FXML
    private TableColumn<Document, String> idDescription;

    @FXML
    private TableColumn<Document, String> idNameDocumenta;

    @FXML
    private TableColumn<Document, String> idSigned;

    @FXML
    private TableColumn<Document, String> idStatus;

    @FXML
    private TableColumn<Document, Integer> idSumma;

    @FXML
    private TableColumn<Document, String> idTipDocumenta;

    @FXML
    private TableColumn<Document, Integer> idDoc;

    @FXML
    private TextField idSearch;
    @FXML
    private Button Edit;

    @FXML
    private TableView<Document> idTabDocument;

    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    Document document = null;
    DraftDocument draftDocument = null;

    DataBaseHandler db = new DataBaseHandler();

    ObservableList<Document> documents;
    ObservableList<DraftDocument> draftDocumentObservableList;
    private ObservableList<Document> data = FXCollections.observableArrayList();
    private ObservableList<DraftDocument> draftDocuments = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        infoTableDocument();
        infoDraftDocument();
        UpdateTable();
        DraftUpdateTable();
        sortTableDocument();
    }


    @FXML
    void EditButton(ActionEvent event) {
        UpdateTable();
    }
    @FXML
    void UpdateDraft(ActionEvent event) {
        idDraftDoc.setCellValueFactory(new PropertyValueFactory<DraftDocument, Integer>("idDraft"));
        idDraftNameDocumenta.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftNameDocumenta"));
        DraftidTipDocumenta.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftTipDocumenta"));
        idDraftStatus.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftStatus"));
        idDraftDataCreation.setCellValueFactory(new PropertyValueFactory<DraftDocument, Date>("DraftDataCreation"));
        IdDraftResponsePreparationЕime.setCellValueFactory(new PropertyValueFactory<DraftDocument, Date>("DraftResponsePreparationЕime"));
        IdDraftPrepared.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftPrepared"));
        idDraftCounterparty.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftCounterparty"));
        idDraftSigned.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftSigned"));
        idDraftContact.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftContact"));
        idDraftSumma.setCellValueFactory(new PropertyValueFactory<DraftDocument, Integer>("DraftSumma"));
        idDraftDescription.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("DraftDescription"));

        draftDocumentObservableList = db.getDraftDocuments();
        DraftViewDoc.setItems(draftDocumentObservableList);
    }

    public void infoDraftDocument() {
        connection = db.getDbConnection();
        // sql запрос для данной сущности
        String query = "select * from draft_documents";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // определяем для каждой переменной свои параметры
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idDraft_documents");
                String name = resultSet.getString("Draft_NameDocument");
                String description = resultSet.getString("Draft_Description");
                Date creationDate = resultSet.getDate("Draft_dateOfCreation");
                Date responsePreparationЕime = resultSet.getDate("Draft_ExecutionDate");
                String status = resultSet.getString("Draft_CurrentStatus");
                String preparation = resultSet.getString("Draft_Prepared");
                String signed = resultSet.getString("Draft_Signed");
                String contact = resultSet.getString("Draft_Contact");
                Integer summa = resultSet.getInt("Draft_sum");
                String tip = resultSet.getString("Draft_TipDocumenta");
                String counterparty = resultSet.getString("Draft_Kontragent");
                draftDocuments.add(new DraftDocument(id, name, description, creationDate, responsePreparationЕime, status, preparation, signed, contact, summa, tip, counterparty));
            }
            // заполняем таблицу
            idDraftDoc.setCellValueFactory(new PropertyValueFactory<DraftDocument, Integer>("idDraft"));
            idDraftNameDocumenta.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftNameDocumenta"));
            DraftidTipDocumenta.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftTipDocumenta"));
            idDraftStatus.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftStatus"));
            idDraftDataCreation.setCellValueFactory(new PropertyValueFactory<DraftDocument, Date>("draftDataCreation"));
            IdDraftResponsePreparationЕime.setCellValueFactory(new PropertyValueFactory<DraftDocument, Date>("draftResponsePreparationЕime"));
            IdDraftPrepared.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftPrepared"));
            idDraftCounterparty.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftCounterparty"));
            idDraftSigned.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftSigned"));
            idDraftContact.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftContact"));
            idDraftSumma.setCellValueFactory(new PropertyValueFactory<DraftDocument, Integer>("draftSumma"));
            idDraftDescription.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftDescription"));
            // сохраняем ее

            DraftViewDoc.setItems(draftDocuments);

        } catch (SQLException e) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, e);

            e.printStackTrace();
        }
    }

    // заполняем таблицу данными из бд
    public void infoTableDocument() {

        connection = db.getDbConnection();
        // sql запрос для данной сущности
        String query = "select * from document";

        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            // определяем для каждой переменной свои параметры
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idDocument");
                String name = resultSet.getString("Name_Document");
                String description = resultSet.getString("Description");
                Date creationDate = resultSet.getDate("date_of_creation");
                Date responsePreparationЕime = resultSet.getDate("Execution_date");
                String status = resultSet.getString("Current_status");
                String preparation = resultSet.getString("prepared");
                String signed = resultSet.getString("Signed");
                String contact = resultSet.getString("contact");
                Integer summa = resultSet.getInt("sum");
                String tip = resultSet.getString("tip_documenta");
                String counterparty = resultSet.getString("kontragent");
                data.add(new Document(id, name, description, creationDate, responsePreparationЕime, status, preparation, signed, contact, summa, tip, counterparty));
            }
            // заполняем таблицу
            idDoc.setCellValueFactory(new PropertyValueFactory<Document, Integer>("id"));
            idNameDocumenta.setCellValueFactory(new PropertyValueFactory<Document, String>("NameDocumenta"));
            idTipDocumenta.setCellValueFactory(new PropertyValueFactory<Document, String>("TipDocumenta"));
            idStatus.setCellValueFactory(new PropertyValueFactory<Document, String>("status"));
            idDataCreation.setCellValueFactory(new PropertyValueFactory<Document, Date>("DataCreation"));
            IdResponsePreparationЕime.setCellValueFactory(new PropertyValueFactory<Document, Date>("responsePreparationЕime"));
            IdPrepared.setCellValueFactory(new PropertyValueFactory<Document, String>("Prepared"));
            idCounterparty.setCellValueFactory(new PropertyValueFactory<Document, String>("counterparty"));
            idSigned.setCellValueFactory(new PropertyValueFactory<Document, String>("signed"));
            idContact.setCellValueFactory(new PropertyValueFactory<Document, String>("contact"));
            idSumma.setCellValueFactory(new PropertyValueFactory<Document, Integer>("summa"));
            idDescription.setCellValueFactory(new PropertyValueFactory<Document, String>("description"));
            // сохраняем ее

            idTabDocument.setItems(data);

        } catch (SQLException e) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, e);
            e.printStackTrace();
        }
    }
    // сортировка таблицы document
    public void sortTableDocument() {
        // начальный фильтрованный список
        FilteredList<Document> filteredData = new FilteredList<>(data, b -> true);

        idSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(document -> {

                // Если значение поиска отсутствует, отобразите все записи или любые записи, которые есть в данный момент. без изменений
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();

                if (document.getNameDocumenta().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true; // означает, что мы нашли совпадение в NameDocument
                } else if (document.getDescription().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true; //означает, что мы нашли совпадение в Description
                } else if (document.getStatus().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true; //означает, что мы нашли совпадение в Status
                } else if (document.getPrepared().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в Prepared
                } else if (document.getSigned().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в Signed
                } else if (document.getContact().toLowerCase().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в Contact
                } else if (document.getDataCreation().toString().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в DataCreation
                } else if (document.getResponsePreparationЕime().toString().indexOf(searchKeyWord) > -1) {
                    return true;//означает, что мы нашли совпадение в ResponsePreparationЕime
                } else
                    return false; // нет совпадений по поиску
            });
        });

        SortedList<Document> sortedData = new SortedList<>(filteredData);

        // связать отсортированный результат с табличным представлением
        sortedData.comparatorProperty().bind(idTabDocument.comparatorProperty());

        // Примените отфильтрованные и отсортированные данные к представлению таблицы.
        idTabDocument.setItems(sortedData);
    }

    // Реализация обновление таблицы document
    public void UpdateTable() {

        idDoc.setCellValueFactory(new PropertyValueFactory<Document, Integer>("id"));
        idNameDocumenta.setCellValueFactory(new PropertyValueFactory<Document, String>("NameDocumenta"));
        idTipDocumenta.setCellValueFactory(new PropertyValueFactory<Document, String>("TipDocumenta"));
        idStatus.setCellValueFactory(new PropertyValueFactory<Document, String>("status"));
        idDataCreation.setCellValueFactory(new PropertyValueFactory<Document, Date>("DataCreation"));
        IdResponsePreparationЕime.setCellValueFactory(new PropertyValueFactory<Document, Date>("responsePreparationЕime"));
        IdPrepared.setCellValueFactory(new PropertyValueFactory<Document, String>("Prepared"));
        idCounterparty.setCellValueFactory(new PropertyValueFactory<Document, String>("counterparty"));
        idSigned.setCellValueFactory(new PropertyValueFactory<Document, String>("signed"));
        idContact.setCellValueFactory(new PropertyValueFactory<Document, String>("contact"));
        idSumma.setCellValueFactory(new PropertyValueFactory<Document, Integer>("summa"));
        idDescription.setCellValueFactory(new PropertyValueFactory<Document, String>("description"));

        documents = db.getDataDocument();
        idTabDocument.setItems(documents);
    }

    @FXML
    void DeleteDraftDocument(ActionEvent event) {
        try {
            draftDocument = DraftViewDoc.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM draft_documents WHERE idDraft_documents =" + draftDocument.getIdDraft();
            connection = db.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            DraftUpdateTable();
        }catch (SQLException e) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    // Обновление TableView черновик документ
    private void DraftUpdateTable() {
        idDraftDoc.setCellValueFactory(new PropertyValueFactory<DraftDocument, Integer>("idDraft"));
        idDraftNameDocumenta.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftNameDocumenta"));
        DraftidTipDocumenta.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftTipDocumenta"));
        idDraftStatus.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftStatus"));
        idDraftDataCreation.setCellValueFactory(new PropertyValueFactory<DraftDocument, Date>("draftDataCreation"));
        IdDraftResponsePreparationЕime.setCellValueFactory(new PropertyValueFactory<DraftDocument, Date>("draftResponsePreparationЕime"));
        IdDraftPrepared.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftPrepared"));
        idDraftCounterparty.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftCounterparty"));
        idDraftSigned.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftSigned"));
        idDraftContact.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftContact"));
        idDraftSumma.setCellValueFactory(new PropertyValueFactory<DraftDocument, Integer>("draftSumma"));
        idDraftDescription.setCellValueFactory(new PropertyValueFactory<DraftDocument, String>("draftDescription"));
        // сохраняем ее
        draftDocumentObservableList = db.getDraftDocuments();
        DraftViewDoc.setItems(draftDocumentObservableList);
    }

    // Реализация удаление из бд через приложение
    @FXML
    void delete(ActionEvent event) {
        try {
            document = idTabDocument.getSelectionModel().getSelectedItem();
            String query = "DELETE FROM document WHERE idDocument =" + document.getId();
            connection = db.getDbConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            UpdateTable();
        } catch (SQLException e) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // переход на страницу добавление документа
    public void AddDocument(ActionEvent actionEvent) throws IOException {
        Parent fxmlLoader = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/example/dbproject/ButDocument/AddNewDocument.fxml")));
        Scene scene = new Scene(fxmlLoader);
        Stage stage = new Stage(StageStyle.UTILITY);
//      stage.setTitle("Hello!");
        stage.setMaximized(false);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    void Edit(ActionEvent event) {
        document = idTabDocument.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dbproject/ButDocument/EditNewDocument.fxml"));
        try {
            loader.load();

        } catch (IOException e) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        AddDocumentController doc = loader.getController();
        doc.setUpdate(true);
        doc.Document(document.getNameDocumenta(), document.getDescription(), document.getDataCreation(),
                document.getResponsePreparationЕime(), document.getStatus(), document.getPrepared(), document.getSigned(),
                document.getContact(), document.getSumma(), document.getTipDocumenta(), document.getCounterparty());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();

    }


    @FXML
    void EditDraft(ActionEvent event)  {

        draftDocument = DraftViewDoc.getSelectionModel().getSelectedItem();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/dbproject/ButDocument/DraftEditDocument.fxml"));
        try {
            loader.load();

        } catch (IOException e) {
            Logger.getLogger(DocumentController.class.getName()).log(Level.SEVERE, null, e);
        }
        DraftDocumentController Draftdoc = loader.getController();
        Draftdoc.setUpdate(true);
        Draftdoc.DraftDocument(draftDocument.getDraftNameDocumenta(),draftDocument.getDraftDescription(),
                draftDocument.getDraftDataCreation(),draftDocument.getDraftResponsePreparationЕime(),
                draftDocument.getDraftStatus(),draftDocument.getDraftPrepared(),draftDocument.getDraftSigned(),draftDocument.getDraftContact(),
                draftDocument.getDraftSumma(),draftDocument.getDraftTipDocumenta(),draftDocument.getDraftCounterparty());

        Parent parent = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.initStyle(StageStyle.UTILITY);
        stage.show();
    }

}
