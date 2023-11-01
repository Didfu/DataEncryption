package com.example.dataencryption;
import com.example.dataencryption.database.FileDao;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class EnfileController extends HomeController implements Initializable
{
    @FXML
    public void submituser(){
        System.out.println(sharing.getText());
    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("NewWindowController initialized");
    }
}
