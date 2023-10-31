package com.example.dataencryption.utils;

public class StringUtils {
    public static final String title = "Data Encryption";

    //
    public static final String userLoginQuery = "SELECT * FROM user WHERE user=? AND pass=?";
    public static final String createview = "CREATE VIEW ? AS SELECT (filename, status) FROM d.files; ";



}
