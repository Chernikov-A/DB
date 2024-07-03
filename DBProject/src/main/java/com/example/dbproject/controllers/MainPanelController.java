package com.example.dbproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class MainPanelController implements Initializable {
    @FXML
    public BorderPane borderPanel;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button idAccounting;

    @FXML
    private Button idContracts;

    @FXML
    private Button idDocument;

    @FXML
    private Button idProcesses;

    @FXML
    private Button idProduct;

    @FXML
    private Button idTasks;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void ButtonPaneDoc(ActionEvent event) throws IOException {
        try {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/dbproject/VboxMainPanel/MenuDocument.fxml"));
            borderPanel.setCenter(pane);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @FXML
    void PaneBottonPro(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/dbproject/VboxMainPanel/MenuProduct.fxml"));
        borderPanel.setCenter(pane);
    }
    @FXML
    void PanelButtonAccouting(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/dbproject/VboxMainPanel/MenuAccounting.fxml"));
        borderPanel.setCenter(pane);
    }


    @FXML
    void PanelButtonProcesses(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/dbproject/VboxMainPanel/MenuProcesses.fxml"));
        borderPanel.setCenter(pane);
    }

    @FXML
    void PanelButtonTasks(ActionEvent event)throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("/com/example/dbproject/VboxMainPanel/MenuTask.fxml"));
        borderPanel.setCenter(pane);
    }

}

