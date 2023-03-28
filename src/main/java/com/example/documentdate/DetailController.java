package com.example.documentdate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextFlow;

import java.net.URL;

import java.util.ResourceBundle;

public class DetailController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


    @FXML
    private AnchorPane Detail;

    @FXML
    private TextFlow DetailText;

    @FXML
    void exitButton(ActionEvent event) {

    }
}