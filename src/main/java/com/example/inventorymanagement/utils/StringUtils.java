package com.example.inventorymanagement.utils;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class StringUtils {
    public static final String dbUrl = "jdbc:mysql://localhost:3306";
    public static final String dbUser= "root";
    public static final String dbPassword = "123456";
    public static final String dbName = "inventorymanagement";
    public static final String title = "Inventory Management";

    //
    public static final String userLoginQuery = "SELECT * FROM users WHERE username=? AND password=?";
    public static final String createProductQuery = "INSERT INTO products VALUES(?, ?, ?, ?);";
    public static final String selectProductQuery = "SELECT * FROM products;";
    public static final String getProductStock = "SELECT stock FROM products WHERE id=?;";
    public static final String updateProductQuery = "UPDATE products SET name=?,price=?,stock=? WHERE id=?";
    public static final String deleteProductQuery = "DELETE FROM products WHERE id=?;";
    public static final String getLeastProductQuery = "SELECT * FROM products WHERE stock <= 10;";

    public static final String getProductByIdQuery = "select * from products where id=?;";
    public static final String createOrderQuery = "INSERT INTO orders VALUES(?, ?, ?, ?, ?);";
    public static final String selectOrderQuery = "SELECT * FROM orders;";
    public static final String deleteOrderQuery = "DELETE FROM orders WHERE id=?;";



}
