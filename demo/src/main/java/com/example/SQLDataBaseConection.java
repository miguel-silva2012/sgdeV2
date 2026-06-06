package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDataBaseConection {
    private static final String user = "root",
            password = "root",
            URL = "jdbc:mysql://localhost:3306/cadastro";

    private static Connection conn;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            if (conn == null) {
                conn = DriverManager.getConnection(URL, user, password);
            }

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
