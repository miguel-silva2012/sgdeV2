package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLDataBaseConection {
    private static final String user = "root",
            password = "",
            URL = "jdbc:mysql://localhost:3306/clothes";

    private static Connection conn;

    public static void main(String[] args) throws SQLException {
        System.out.println(getColumn("id"));
        System.out.println(getColumn("price"));
        System.out.println(getColumn("name"));
    }

    public static List<String> getColumn(String column) throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        List<String> columnOfTable = new ArrayList<String>();

        if (conn == null) {
            conn = DriverManager.getConnection(URL, user, password);

            ResultSet rsClient = conn.createStatement().executeQuery("SELECT * FROM tableclothes");

            while (rsClient.next()) {
                columnOfTable.add(rsClient.getString(column));
            }
        } 
        
        return columnOfTable;
    }
}
