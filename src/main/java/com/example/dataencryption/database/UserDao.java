package com.example.dataencryption.database;

import com.example.dataencryption.models.User;
import com.example.dataencryption.utils.ScreenUtils;
import com.example.dataencryption.utils.StringUtils;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public static User loginUser(String user, String pass){
        DbConnection dbConnection = new DbConnection();
        Connection connection = DbConnection.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(StringUtils.userLoginQuery);
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
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
