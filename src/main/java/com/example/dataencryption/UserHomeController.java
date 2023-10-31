package com.example.dataencryption;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;



public class UserHomeController extends HomeController implements Initializable {
    public Label nameLabel;
    public BorderPane bp;
    public AnchorPane ap;
    public TableColumn<File, String> nameColumn;
    public TableColumn<File, String> statusColumn;


    @FXML
    protected TextField pathTextField;

    void setTableColumnDefault(){
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        System.out.println("user here" );
        setTableValues();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("at user");
        setTableColumnDefault();

    }


}

