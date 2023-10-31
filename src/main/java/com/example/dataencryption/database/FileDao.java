package com.example.dataencryption.database;

import com.example.dataencryption.EnfileController;
import com.example.dataencryption.models.CFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FileDao extends EnfileController {
    public static ObservableList<CFile> getFiles(){
        ObservableList<CFile> temp = FXCollections.observableArrayList();
        Connection connection = DbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM files;");
            while (rs.next()){
                temp.add(new CFile(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return temp;
    }
    public static void insertFile(CFile file){
        Connection connection = DbConnection.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO d.files(filename, status, sharing) VALUES(?, ?, ?); ");
            pstm.setString(1, file.getName());
            pstm.setString(2, file.getStatus());
            pstm.setString(3, file.getSharing());
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void updateStatus(int id, String status){
        Connection connection = DbConnection.getConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("UPDATE d.files SET status=? WHERE id = ?;");
            pstm.setString(1, status);
            pstm.setInt(2, id);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getFile(String name){
        int id = 0;
        Connection connection = DbConnection.getConnection();
        PreparedStatement pstm;
        try {
            pstm = connection.prepareStatement("SELECT id FROM d.files WHERE filename=?");
            pstm.setString(1, name);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("getfile error");
            throw new RuntimeException(e);
        }
        return id;
    }
}
