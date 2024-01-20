package com.example.dataencryption.models;

import javafx.scene.control.TextField;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class CFile {


    private  int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getSharing() {
        if (Objects.equals(sharing, "")){return "all";}else {return sharing;}

    }

    public void setSharing(String sharing) {
        this.sharing = sharing;
    }

    private String name;

    private String status;
    private String sharing;

    public CFile(String name, String status, String sharing) {
        this.name = name;
        this.status = status;
        this.sharing = sharing;
    }
    public CFile(ResultSet rs){
        try {
            this.id = rs.getInt(1);
            this.name = rs.getString("filename");
            this.status = rs.getString("status");
            this.sharing = rs.getString("sharing");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
