package com.example.dataencryption;

import com.example.dataencryption.database.FileDao;
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





    void setuservalues(){

        System.out.println("before" );


        fileTable.setItems(FileDao.getFiles());


        System.out.println("after");

    }
    void setTableColumnDefault(){
        System.out.println("before name" );
        nameColumn.setCellValueFactory(new PropertyValueFactory<File, String>("name"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<File, String>("status"));
        System.out.println("here" );
        setTableValues();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("at user");
        setTableColumnDefault();
    }


}

