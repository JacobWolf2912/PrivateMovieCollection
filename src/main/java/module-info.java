module com.example.mytunes {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;

    opens com.privatemoviecollection.ui to javafx.fxml;
    exports com.privatemoviecollection.ui;
    opens com.privatemoviecollection.ui.controllers to javafx.fxml;
    exports com.privatemoviecollection.ui.controllers;
}