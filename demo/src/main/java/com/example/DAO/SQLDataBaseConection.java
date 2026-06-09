package com.example.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.model.Clothe;

import lombok.Getter;

public class SQLDataBaseConection {
    private static final String user = "root",
            password = "",
            URL = "jdbc:mysql://localhost:3306/clothes";

    private static Connection conn;

    @Getter
    private ResultSet rsClient;

    public SQLDataBaseConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }  

        conn = DriverManager.getConnection(URL, user, password);

        rsClient = execQuery("SELECT * FROM tableclothes");
    }

    public void addClothe(Clothe clothe) throws SQLException{
        rsClient = execQuery(
            "INSERT INTO tableclothes VALUES (DEFAULT, " + clothe.getName()     +    ", "
                                                         + clothe.getPrice()    +    ", "
                                                         + clothe.getQuantity() +    ", "
                                                         + clothe.getDescription() + ")"
        );
    }

    private ResultSet execQuery(String query) throws SQLException {
        return conn.createStatement().executeQuery(query);
    }
}
