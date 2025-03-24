module com.example.homework {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires javafx.graphics;
    requires mysql.connector.j;
    requires java.sql;

    opens com.example.homework to javafx.fxml;
    exports com.example.homework;
    exports com.example.homework.Controllers;
    opens com.example.homework.Controllers to javafx.fxml;
}