package com.example.documentdate;

import com.example.documentdate.model.ConnectorModelDe;
import com.example.documentdate.model.DbConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    Stage stage;
    Integer queryID = 0;
    String querynameDocument = "";
    String querynumberDocument = "";
    String queryperformerDocument = "";
    String querydateofexecution = "";
    String querylastdateDocument = "";
    String queryfileDocument = "";
    String queryStatus ="";
    String queryConfirmationDocument ="";


    @FXML
    private AnchorPane addDocPane;


    @FXML
    private TextField editFileDocument;

    @FXML
    private TextField editNameDocument;

    @FXML
    private TextField editNumberDocument;

    @FXML
    private TextField editPreformerDocument;

    @FXML
    private TextArea editTaskDocument;

    @FXML
    private DatePicker editExDocument;

    @FXML
    private Label statusExDate;

    @FXML
    private Button saveButton;

    @FXML
    void addFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(stage);
        if (file!=null){
            editFileDocument.setText(file.getAbsolutePath());
        }
        else {
            editFileDocument.setText(" ");
        }
    }

    @FXML
    void editDocButton(ActionEvent event) {
        String dataEx = editExDocument.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        UpdateData(queryID,editNameDocument.getText(),editNumberDocument.getText(),editPreformerDocument.getText(),editTaskDocument.getText(),editFileDocument.getText(),dataEx);
    }

    @FXML
    void exitButton(ActionEvent event) {

    }

    public void refreshtable(int ids) {

        try {
            DbConnection dbConnection = new DbConnection();
            java.sql.Connection connectionDB = dbConnection.getDBConnection();
            String documentViewQuery = "SELECT id,nameDocument,numberDocument,performerDocument,dateofexecution,lastdateDocument,fileDocument,confirmationDocument,status FROM myDoc where id="+ids;


            Statement statement = null;
            try {
                statement = connectionDB.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            ResultSet queryOutput = null;
            try {
                queryOutput = statement.executeQuery(documentViewQuery);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


            while (queryOutput.next()) {
                queryID = queryOutput.getInt("id");
                querynameDocument = queryOutput.getString("nameDocument");
                querynumberDocument = queryOutput.getString("numberDocument");
                queryperformerDocument = queryOutput.getString("performerDocument");
                querydateofexecution = queryOutput.getString("dateofexecution");
                querylastdateDocument = queryOutput.getString("lastdateDocument");
                queryfileDocument = queryOutput.getString("fileDocument");
                queryStatus = queryOutput.getString("status");
                queryConfirmationDocument = queryOutput.getString("confirmationDocument");


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshtable(ConnectorModelDe.getId());
         fullTask();
    }
    void  fullTask(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        formatter.parse(querylastdateDocument);
        LocalDate localDate=LocalDate.parse(querylastdateDocument,formatter);
        editNameDocument.setText(querynameDocument);
        editNumberDocument.setText(querynumberDocument);
        editPreformerDocument.setText(queryperformerDocument);
        editTaskDocument.setText(querydateofexecution);
        editFileDocument.setText(queryfileDocument);
        editExDocument.setValue(localDate);
    }
    void UpdateData(int Id,String nameDoc,String numberDoc,String preformDoc,String taskDoc,String fileDoc , String dateEx){
        String UpdateData="UPDATE myDoc SET nameDocument='"+nameDoc.replace("'", "`")+"',numberDocument='"+numberDoc.replace("'", "`")+"',performerDocument='"+preformDoc.replace("'", "`")+"',dateofexecution='"+taskDoc.replace("'", "`")+"',fileDocument='"+fileDoc.replace("'", "`")+"',lastdateDocument='"+dateEx.replace("'", "`")+"' WHERE id="+Id;
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