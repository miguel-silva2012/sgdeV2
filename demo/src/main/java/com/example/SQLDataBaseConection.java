package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLDataBaseConection {
    private static final String user = "root",
            password = "",
            URL = "jdbc:mysql://localhost:3306/clothes";

    private static Connection conn;

    private ResultSet rsClient;

    public SQLDataBaseConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }  

        conn = DriverManager.getConnection(URL, user, password);

        rsClient = conn.createStatement().executeQuery("SELECT * FROM tableclothes");
    }

    @SuppressWarnings("exports")
    public ResultSet getResultSet() {
        return rsClient;
    }
}
