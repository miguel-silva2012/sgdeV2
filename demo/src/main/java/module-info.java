module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires javafx.graphics;

    opens com.example to javafx.fxml;
    opens com.example.Controllers to javafx.fxml;
    exports com.example;
    exports com.example.Controllers;
}
