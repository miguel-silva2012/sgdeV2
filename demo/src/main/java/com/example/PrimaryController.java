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

    private boolean validClotheAdding() {
        try {
            String nameFieldCont = nameField.getText();         

            short quantityFieldCont = Short.parseShort(quantityField.getText().trim()); 

            BigDecimal priceFieldCont = new BigDecimal(priceField.getText().trim().replace(",", "."));

            String descriptionFieldCont = descriptionField.getText();

            ClotheDAO clothe = new ClotheDAO(nameFieldCont, priceFieldCont, quantityFieldCont, descriptionFieldCont);

            App.sqlDBConection.addClothe(clothe);

            clothes.add(clothe);
            
            tableClothes.setItems(clothes);
        } catch (SQLException | NumberFormatException e) {
            result.setText("Some field is invalid");
            result.setStyle("-fx-text-fill: yellow");
            return false;
        }

        return true;
    }

    @FXML
    private void updateTable() {
        validClotheAdding();

        nameField.clear();
        priceField.clear();
        quantityField.clear();
        descriptionField.clear();
    }
}
