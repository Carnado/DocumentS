package com.example.documentdate;

import com.dustinredmond.fxtrayicon.FXTrayIcon;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("MainController.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MenuItem menuItem=new MenuItem("Dastur haqida");
        MenuItem exitprogramm=new MenuItem("Chiqish!");
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                stage.show();
            }
        };
        EventHandler<ActionEvent> eventExit = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                    Platform.exit();
                    System.exit(0);
            }
        };

        exitprogramm.setOnAction(eventExit);
        menuItem.setOnAction(event);
        FXTrayIcon tryicon = new FXTrayIcon(stage, getClass().getResource("report.png"));
        tryicon.addTitleItem(true);
        tryicon.addMenuItem(menuItem);
        tryicon.addMenuItem(exitprogramm);
        tryicon.setApplicationTitle("Oynaga chiqarish");
        tryicon.show();
        stage.setMinHeight(800);
        stage.setMinWidth(1280);
        tryicon.showInfoMessage("Hujjatlar Ijrosi Dasturi Eslatmasi!","Dastur ish rejimidaqolmoqda shuni unutmang");
        stage.setTitle("HUJJATLAR NAZORATI");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}