package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

import com.example.Controllers.PrimaryController;

public class App extends Application {

    private static Scene scene;

    public static SQLDataBaseConection sqlDBConection;

    @Override
    public void start(@SuppressWarnings("exports") Stage stage) throws SQLException, IOException   {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("primary.fxml"));
        scene = new Scene(fxmlLoader.load(), 640, 480);

        PrimaryController controller = fxmlLoader.getController();

        sqlDBConection = new SQLDataBaseConection();

        while (sqlDBConection.getRsClient().next()) {
            controller.clothes.add(new ClotheDAO(
                    sqlDBConection.getRsClient().getString("name"), 
                    sqlDBConection.getRsClient().getBigDecimal("price"), 
                    sqlDBConection.getRsClient().getByte("quantity"), 
                    sqlDBConection.getRsClient().getString("description")
                )
            );
        }
        
        controller.columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        controller.columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        controller.columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        controller.columnDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        
        controller.tableClothes.setItems(controller.clothes);

        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws SQLException {
        sqlDBConection.closeConection();
    }

    public static void main(String[] args) {
        launch();
    }
}