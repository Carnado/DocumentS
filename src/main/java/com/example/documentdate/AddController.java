package com.example.documentdate;

import com.example.documentdate.model.DbConnection;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.io.File;
import java.awt.Desktop;
public class AddController implements Initializable {
    Stage stage;
    @FXML
    private AnchorPane addDocPane;


    @FXML
    private TextField addFileDocument;

    @FXML
    private TextField addnameDocument;

    @FXML
    private TextField addnumberDocument;


    @FXML
    private TextField addpreformerDocument;

    @FXML
    private TextArea addTaskDocument;

    @FXML
    private DatePicker addexDocument;

    @FXML
    private Label statusExDate;

    @FXML
    private Button saveButton;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String addDocExc;
        LocalDate dateEXC = addexDocument.getValue();

        tooltip();


        if (dateEXC != null) {
            addDocExc = addexDocument.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } else {
            addDocExc="";
        }


        checkTextField();

        addnameDocument.textProperty().addListener((observable, oldValue, newValue) -> {
            checkTextField();
        });
        addexDocument.valueProperty().addListener((observable, oldValue, newValue) -> {
            checkTextField();
        });

    }

    @FXML
    void adddocButton(ActionEvent event) {
        stage=(Stage) addDocPane.getScene().getWindow();
        String dataEx = addexDocument.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        String addNumber=addnumberDocument.getText().trim();
        String addNameDoc=addnameDocument.getText().trim();
        String addCreatorDoc=addpreformerDocument.getText().trim();
        String addTask=addTaskDocument.getText().trim();
        String FileAddress=addFileDocument.getText().trim();
        DbConnectAdd(addNameDoc,addNumber,addCreatorDoc,addTask,dataEx,FileAddress);
        stage.close();
    }

    private void DbConnectAdd(String nameDoc,String numberDoc,String preFormerDoc,String taskDoc,String DateEx,String fileDocument) {
        DbConnection dbConnection = new DbConnection();
        java.sql.Connection connectionDB = dbConnection.getDBConnection();
        String sql = "INSERT INTO myDoc (nameDocument,numberDocument,performerDocument,dateofexecution,lastdateDocument,fileDocument,status) VALUES ('"+nameDoc.replace("'", "`")+"','"+numberDoc.replace("'", "`")+"','"+preFormerDoc.replace("'", "`")+"','"+taskDoc.replace("'", "`")+"','"+DateEx.replace("'", "`")+"','"+fileDocument.replace("'", "`")+"',"+"'bajarilmagan')";
        try {
            Statement statement = connectionDB.createStatement();
            statement.executeUpdate(sql);
            System.out.println(sql);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    void exitButton(ActionEvent event) {
        stage=(Stage) addDocPane.getScene().getWindow();
        stage.close();
    }

    public void checkTextField() {
        String addDocExc;
        LocalDate dateEXC = addexDocument.getValue();

        if (dateEXC != null) {
            addDocExc = addexDocument.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        } else {
            addDocExc="";
        }
        if (addDocExc == "" || addDocExc == null) statusExDate.setText("*");
        else statusExDate.setText("");
        saveButton.setDisable(addDocExc==""||addDocExc==null);

    }
    @FXML
    void addFile(ActionEvent event) {
        FileChooser fileChooser=new FileChooser();
        File file=fileChooser.showOpenDialog(stage);
        if (file!=null){
            addFileDocument.setText(file.getAbsolutePath());
        }
        else {
            addFileDocument.setText(" ");
        }
    }
    private void tooltip() {
        final Tooltip nameDoc = new Tooltip("Hujjat nomini kiriting!");
        addnameDocument.setTooltip(nameDoc);
        final Tooltip numberDoc = new Tooltip("Vazifa matnini kirting!");
        addTaskDocument.setTooltip(numberDoc);
        final Tooltip createData = new Tooltip("Hujjat Raqamini Kiriting!");
        addnumberDocument.setTooltip(createData);
        final Tooltip nameExcData = new Tooltip("Kim tomonidan yaratilganini kiriting!");
        addpreformerDocument.setTooltip(nameExcData);
    }

}