package com.example;

import java.math.BigDecimal;

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

    private ObservableList<Clothe> clothes = FXCollections.observableArrayList(); 

    private int ID = 0;

    @FXML
    private void getTextField() {
        String nameFields = nameField.getText();
        String priceFields = priceField.getText(); 

        if (nameFields.isEmpty() || priceFields.isEmpty()) {
            result.setText("The field is empty");
        } else {
            nameField.clear();
            priceField.clear();

            columnId.setCellValueFactory(new PropertyValueFactory<>("ID"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
            columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
            
            tableClothes.setItems(clothes);

            clothes.add(new Clothe(new BigDecimal(priceFields), nameFields, ID));
            
            ID++;
        }
    }
}
