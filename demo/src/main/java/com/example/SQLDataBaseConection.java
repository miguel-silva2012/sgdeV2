package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.Getter;

public class SQLDataBaseConection {
    private static final String user = "root",
            password = "",
            URL = "jdbc:mysql://localhost:3306/clothes";

    private static Connection conn;

    @Getter
    private ResultSet rsClient;

    @Getter
    private String mainTable = "tableclothes";

    public SQLDataBaseConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }  

        conn = DriverManager.getConnection(URL, user, password);

        rsClient = conn.prepareStatement("SELECT * FROM " + mainTable).executeQuery();
    }

    public void closeConection() throws SQLException {
        conn.close();
    }

    public void addClothe(ClotheDAO clothe) throws SQLException {
        String query = "INSERT INTO " + mainTable + " VALUES (DEFAULT, ?, ?, ?, ?)";

        execUpdate(query, clothe);
    }

    private void execUpdate(String query, ClotheDAO clothe) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, clothe.getName());
        ps.setBigDecimal(2, clothe.getPrice());
        ps.setShort(3, clothe.getQuantity());
        ps.setString(4, clothe.getDescription());

        ps.executeUpdate();
    }
}
