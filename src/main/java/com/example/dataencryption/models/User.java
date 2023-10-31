package com.example.dataencryption.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class User {

    private final String user;
    private final String pass;

    public User(ResultSet rs) throws SQLException {
        this.user = rs.getString("user");
        this.pass = rs.getString("pass");
    }


    public String getUsername() {
        return user;
    }

}
