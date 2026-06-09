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
    private TextField quantityField;

    @FXML
    private TextField descriptionField;

    @FXML
    private Label result;

    @FXML 
    private TableView<ClotheDAO> tableClothes;

    @FXML
    private TableColumn<ClotheDAO, Short> columnId; 

    @FXML
    private TableColumn<ClotheDAO, String> columnName;

    @FXML
    private TableColumn<ClotheDAO, BigDecimal> columnPrice;

    @FXML
    private TableColumn<ClotheDAO, Short> columnQuantity;

    @FXML
    private TableColumn<ClotheDAO, String> columnDescription;

    private ObservableList<ClotheDAO> clothes = FXCollections.observableArrayList(); 

    SQLDataBaseConection sqlDBConection;

    public PrimaryController() throws SQLException {
        sqlDBConection = new SQLDataBaseConection();

        while (sqlDBConection.getRsClient().next()) {
            clothes.add(new ClotheDAO(sqlDBConection.getRsClient().getShort("ID"),
                sqlDBConection.getRsClient().getString("name"), 
                sqlDBConection.getRsClient().getBigDecimal("price"), 
                sqlDBConection.getRsClient().getByte("quantity"), 
                sqlDBConection.getRsClient().getString("description")));
        }
    }

    @FXML
    private void execute() throws SQLException {
        String nameFields = nameField.getText();
        String priceFields = priceField.getText(); 
        String quantityFields = quantityField.getText(); 
        String descriptionFields = descriptionField.getText(); 

        sqlDBConection = new SQLDataBaseConection();

        if (!(nameFields.isEmpty() || priceFields.isEmpty() || quantityFields.isEmpty() || descriptionFields.isEmpty())){              
            if (!(nameFields.length() > 30 || nameFields.matches(".*\\d+.*") ||
                priceFields.matches("^[a-zA-Z]+$") || Byte.parseByte(quantityFields) > 127)) {
                nameField.clear();
                priceField.clear();
                quantityField.clear();
                descriptionField.clear();

                columnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
                columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
                columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
                columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
                columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
                
                tableClothes.setItems(clothes);

                sqlDBConection.addClothe(new ClotheDAO((short)0, nameFields, new BigDecimal(priceFields), Byte.parseByte(quantityFields), descriptionFields));
            } else {
                result.setText("Some field is invalid");
                result.setStyle("-fx-text-fill: yellow");
            }
        } else {
            result.setText("Some field is empty");
            result.setStyle("-fx-text-fill: red");
        }
    }
}
