package com.example;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.example.DAO.SQLDataBaseConection;
import com.example.model.Clothe;

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
    private TableView<Clothe> tableClothes;

    @FXML
    private TableColumn<Clothe, Short> columnId; 

    @FXML
    private TableColumn<Clothe, String> columnName;

    @FXML
    private TableColumn<Clothe, BigDecimal> columnPrice;

    @FXML
    private TableColumn<Clothe, Short> columnQuantity;

    @FXML
    private TableColumn<Clothe, String> columnDescription;

    private ObservableList<Clothe> clothes = FXCollections.observableArrayList(); 

    SQLDataBaseConection sqlDBConection;

    public PrimaryController() throws SQLException {
        sqlDBConection = new SQLDataBaseConection();

        while (sqlDBConection.getRsClient().next()) {
            clothes.add(new Clothe(sqlDBConection.getRsClient().getShort("ID"),
                sqlDBConection.getRsClient().getString("name"), 
                sqlDBConection.getRsClient().getBigDecimal("price"), 
                sqlDBConection.getRsClient().getShort("quantity"), 
                sqlDBConection.getRsClient().getString("description")));
        }        
    }

    @FXML
    private void getTextField() throws SQLException {
        String nameFields = nameField.getText();
        String priceFields = priceField.getText(); 
        String quantityFields = priceField.getText(); 
        String descriptionFields = priceField.getText(); 

        sqlDBConection = new SQLDataBaseConection();

        if (!(nameFields.isEmpty() || priceFields.isEmpty() || quantityFields.isEmpty() || descriptionFields.isEmpty())) {
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
        } else {
            result.setText("Some field is empty");
        }
    }
}
