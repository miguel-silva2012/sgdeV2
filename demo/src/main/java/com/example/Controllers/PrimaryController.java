package com.example.Controllers;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import com.example.App;
import com.example.ClotheDAO;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PrimaryController {
    @FXML
    private TextField nameField, priceField, quantityField, descriptionField;

    @FXML
    private Label result;

    @FXML 
    public TableView<ClotheDAO> tableClothes;

    @FXML
    public TableColumn<ClotheDAO, Short> columnId, columnQuantity; 

    @FXML
    public TableColumn<ClotheDAO, String> columnName, columnDescription;

    @FXML
    public TableColumn<ClotheDAO, BigDecimal> columnPrice;

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

    @FXML
    private void generateReport(ActionEvent event) throws IOException {
        
    }
}
