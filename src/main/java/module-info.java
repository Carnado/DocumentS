module com.example.documentdate {
    requires javafx.controls;
    requires javafx.fxml;
    requires sqlite.jdbc;
    requires java.sql;
    requires org.controlsfx.controls;
    requires FXTrayIcon;
    requires TrayTester;
    requires javafx.media;
    requires com.jfoenix;
    requires gwt.awt;
    requires java.desktop;


    opens com.example.documentdate to javafx.fxml;
    exports com.example.documentdate;
    exports com.example.documentdate.model;
    opens com.example.documentdate.model to javafx.fxml;
}