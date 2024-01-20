package com.example.dataencryption.database;

import com.example.dataencryption.utils.ScreenUtils;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    

    public static Connection getConnection() {
        Connection connectionLink = null;
        try{
            connectionLink = DriverManager.getConnection("yourconnection","root","yourpassword");
        }catch (SQLException e){
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return connectionLink;

    }
}
