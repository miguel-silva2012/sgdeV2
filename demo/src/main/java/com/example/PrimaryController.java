package com.example;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private TextField mainField;

    @FXML
    private Label result;

    @FXML
    private void getTextField() {
        String textField = mainField.getText();

        if (textField.isEmpty()) {
            result.setText("The field is empty");
        } else {
            result.setText(textField);
            mainField.clear();
        }
    }
}
