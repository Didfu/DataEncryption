package com.example.dataencryption.database;

import com.example.dataencryption.models.User;
import com.example.dataencryption.utils.ScreenUtils;
import com.example.dataencryption.utils.StringUtils;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User loginUser(String username, String password){
        DbConnection dbConnection = new DbConnection();
        Connection connection = dbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.userLoginQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return new User(rs);
            }
        } catch (SQLException e) {
            ScreenUtils.showAlertDialog(Alert.AlertType.ERROR, "", e.getMessage());
        }
        return null;
    }




}
