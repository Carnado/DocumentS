package com.example.documentdate;

import com.example.documentdate.model.ConnectorModelDe;
import com.example.documentdate.model.DbConnection;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class ConfirmationDockController implements Initializable {
Stage stage;

Integer id=ConnectorModelDe.getId();
    @FXML
    private AnchorPane addConfirmationPane;

    @FXML
    private JFXButton AddConfirmationDocButton;

    @FXML
    private TextField ConfirmationTextFile;

    @FXML
    private TextArea ConfirmationText;

    @FXML
    private Label ConfirmationTextFileNoFull;

    @FXML
    private Label ConfirmationTextNoFull;

    @FXML
    private Label ConfirmationAlert;

    @FXML
    private Label ConfirmationAlertIco;

    @FXML
    void CancelConfirmationDocButton(ActionEvent event) {
        stage=(Stage) addConfirmationPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    void DockFileOpenButton(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(stage);
        if (file!=null){
            ConfirmationTextFile.setText(file.getAbsolutePath());
        }

    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    check();
        ConfirmationTextFile.setText(ConnectorModelDe.getConfirmationDocumentFile());
        ConfirmationText.setText(ConnectorModelDe.getConfirmationDocument());
    }
    @FXML
    void AddConfirmationDocButton(ActionEvent event) {
        String a=ConfirmationText.getText();
        String b=ConfirmationTextFile.getText();
        if (a!=null &&  a!="" || b!=null &&  b!="" ){
        UpdateData(id,b,a);
            System.out.println("Save");
            stage=(Stage) addConfirmationPane.getScene().getWindow();
            stage.close();
            MainController.change=true;
        }
        else {
            Uncheck();
        }
    }
    void check(){
        ConfirmationTextFile.setEditable(false);
        ConfirmationAlert.setVisible(false);
        ConfirmationTextFileNoFull.setVisible(false);
        ConfirmationTextNoFull.setVisible(false);
        ConfirmationAlertIco.setVisible(false);
    }
    void Uncheck(){
        ConfirmationTextFile.setEditable(true);
        ConfirmationAlert.setVisible(true);
        ConfirmationTextFileNoFull.setVisible(true);
        ConfirmationTextNoFull.setVisible(true);
        ConfirmationAlertIco.setVisible(true);
    }
    void UpdateData(int Id,String file,String text){
        if (file=="" || file==null || file=="null"){
            file=" ";
        }
        if (text=="" || text==null || text=="null"){
            text=" ";
        }
        String UpdateData="UPDATE myDoc SET confirmationDocument='"+text.replace("'", "`")+"',confirmationDocumentFile='"+file+"',status='bajarilgan"+"' WHERE id="+Id;
        DbConnection connection1=new DbConnection();
        Connection connectionDB=connection1.getDBConnection();
        Statement statement=null;
        try {
            statement=connectionDB.createStatement();
            statement.execute(UpdateData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}