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
    public TableView<ClotheDAO> tableClothes;

    @FXML
    public TableColumn<ClotheDAO, Short> columnId; 

    @FXML
    public TableColumn<ClotheDAO, String> columnName;

    @FXML
    public TableColumn<ClotheDAO, BigDecimal> columnPrice;

    @FXML
    public TableColumn<ClotheDAO, Short> columnQuantity;

    @FXML
    public TableColumn<ClotheDAO, String> columnDescription;

    public ObservableList<ClotheDAO> clothes = FXCollections.observableArrayList(); 

    @FXML
    private void execute() throws SQLException {
        String nameFields = nameField.getText();
        String priceFields = priceField.getText(); 
        String quantityFields = quantityField.getText(); 
        String descriptionFields = descriptionField.getText(); 

        if (!(nameFields.isEmpty() || priceFields.isEmpty() || quantityFields.isEmpty() || descriptionFields.isEmpty())) {
            nameField.clear();
            priceField.clear();
            quantityField.clear();
            descriptionField.clear();

            ClotheDAO clothe = new ClotheDAO(nameFields, new BigDecimal(priceFields), Byte.parseByte(quantityFields), descriptionFields);

            App.sqlDBConection.addClothe(clothe);

            clothes.add(clothe);
            
            tableClothes.setItems(clothes);
        } else {
            result.setText("Some field is empty");
            result.setStyle("-fx-text-fill: red");
        }
    }
}
