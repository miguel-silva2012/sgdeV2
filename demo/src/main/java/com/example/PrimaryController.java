package com.example;

import java.math.BigDecimal;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PrimaryController {
    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private Label result;

    @FXML 
    private TableView<Clothe> tableClothes;

    @FXML
    private TableColumn<Clothe, Integer> columnId;

    @FXML
    private TableColumn<Clothe, String> columnName;

    @FXML
    private TableColumn<Clothe, BigDecimal> columnPrice;

    @FXML
    private TableColumn<Clothe, Short> columnQuantity;

    @FXML
    private TableColumn<Clothe, String> columnDescription;

    private ObservableList<Clothe> clothes = FXCollections.observableArrayList(); 

    private int ID = 0;

    SQLDataBaseConection sqlDBConection;

    @FXML
    private void getTextField() throws SQLException {
        String nameFields = nameField.getText();
        String priceFields = priceField.getText(); 

        sqlDBConection = new SQLDataBaseConection();

        if (nameFields.isEmpty() || priceFields.isEmpty()) {
            result.setText("The field is empty");
        } else {
            nameField.clear();
            priceField.clear();

            columnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
            columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
            
            tableClothes.setItems(clothes);

            while (sqlDBConection.getResultSet().next()) {
                clothes.add(new Clothe(sqlDBConection.getResultSet().getInt("ID"),
                    sqlDBConection.getResultSet().getString("name"), 
                    sqlDBConection.getResultSet().getBigDecimal("price"), 
                    sqlDBConection.getResultSet().getShort("quantity"), 
                    sqlDBConection.getResultSet().getString("description")));
            }
            
            ID++;
        }
    }
}
