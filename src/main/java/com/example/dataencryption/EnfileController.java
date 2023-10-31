package com.example.dataencryption;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class EnfileController extends HomeController implements Initializable
{
    public TextField textField;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("NewWindowController initialized");
    }
}
