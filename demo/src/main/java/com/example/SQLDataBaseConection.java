package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
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

    public SQLDataBaseConection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }  

        conn = DriverManager.getConnection(URL, user, password);

        rsClient = conn.prepareStatement("SELECT * FROM tableclothes").executeQuery();
    }

    public void addClothe(ClotheDAO clothe) throws SQLException {
        execUpdate(
            "INSERT INTO tableclothes VALUES (DEFAULT, '" + clothe.getName()    +    "', '"
                                                         + clothe.getPrice()    +    "', '"
                                                         + clothe.getQuantity() +    "', '"
                                                         + clothe.getDescription() + "') "
        );
    }

    private void execUpdate(String query) throws SQLException {
        conn.prepareStatement(query).executeUpdate();
    }
}
