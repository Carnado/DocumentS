package com.example.documentdate.model;
import com.example.documentdate.Main;
import javafx.fxml.FXMLLoader;

import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    public Connection databaseLink;

    public Connection getDBConnection() {
        URL resource = Main.class.getResource("Dbase.db");
        String jdbcUrl = "jdbc:sqlite:/Dbase.db";

        try {
            Class.forName("org.sqlite.JDBC");
            databaseLink = DriverManager.getConnection(jdbcUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return databaseLink;
    }
}
