package com.example.documentdate;

import com.example.documentdate.model.CategoryModel;
import com.example.documentdate.model.ConnectorModelDe;
import com.jfoenix.controls.JFXDatePicker;
import com.example.documentdate.model.DbConnection;
import com.example.documentdate.model.ModelDe;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainController extends Thread implements Initializable {
    public static boolean change = false;

    public void run() {

        while (true) {
            if (change) refreshtable();
            try {
                Thread.sleep(1000l);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.start();
        refreshtable();
        Timenow();
        CategoryToView();
        currentDate();


    }
ObservableList<CategoryModel> categoryModelList= FXCollections.observableArrayList();
    @FXML
    void deleteButton(ActionEvent event) {
        Alert alertDelete = new Alert(Alert.AlertType.CONFIRMATION);
        alertDelete.setTitle("O'chirish oynasi");
        alertDelete.setHeaderText("O'chirishni istaysizmi?");
        ModelDe newModel = documentView.getSelectionModel().getSelectedItem();
        if (alertDelete.showAndWait().get() == ButtonType.OK) {


            int id = newModel.getId();
            String deleteDataNumber = "DELETE FROM myDoc WHERE id='" + id + "'";
            DbConnection connectNow = new DbConnection();
            Connection connectDBs = connectNow.getDBConnection();
            Statement statements = null;

            try {
                statements = connectDBs.createStatement();
                statements.execute(deleteDataNumber);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        refreshtable();

    }

    @FXML
    private TableView<ModelDe> documentView;

    @FXML
    private TableColumn<ModelDe, Integer> id;

    @FXML
    private TableColumn<ModelDe, String> nameDocument;

    @FXML
    private TableColumn<ModelDe, String> numberDocument;

    @FXML
    private TableColumn<ModelDe, String> performerDocument;

    @FXML
    private TableColumn<ModelDe, String> dateofexecution;

    @FXML
    private TableColumn<ModelDe, String> lastdateDocument;

    @FXML
    private TableColumn<ModelDe, String> fileDocument;

    @FXML
    private TableColumn<ModelDe, String> status;
    @FXML
    private TableColumn<ModelDe, String> imagecolumn;
    @FXML
    private TableColumn<ModelDe, String> confirmationDocumentText;

    @FXML
    private TableColumn<ModelDe, String> confirmationDocumentFile;

    @FXML
    private TableColumn<ModelDe, Button> OpenFileDoc;
    @FXML
    private TableColumn<ModelDe, Button> eyeDoc;

    @FXML
    private TableColumn<ModelDe, Button> doneDoc;
    @FXML
    private TableColumn<?, ?> documentTypeColumn;
    @FXML
    private TableColumn<?, ?> registrationNumberColumn;
    @FXML
    private TableColumn<?, ?> documentRegisterData;

    Alert aLert=new Alert(Alert.AlertType.ERROR);

    @FXML
    private JFXComboBox<String> documentTypeId;

    @FXML
    void documentTypeOnAction(ActionEvent event) {
        int selectID=itemGetSelectCombo(documentTypeId.getSelectionModel().getSelectedItem());
        if (selectID==1){
            refreshtable();
        }else {
            refreshTableSelected(selectID);
        }
    }




    public void refreshtable() {
        change = false;
        ObservableList<ModelDe> modelDeObservableList = FXCollections.observableArrayList();
        try {
            DbConnection dbConnection = new DbConnection();
            java.sql.Connection connectionDB = dbConnection.getDBConnection();
            String documentViewQuery = "SELECT id,nameDocument,numberDocument,performerDocument,dateofexecution,lastdateDocument,fileDocument,confirmationDocument,confirmationDocumentFile,status FROM myDoc";


            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(documentViewQuery);


            while (queryOutput.next()) {
                ImageView on = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("baj.png"))));
                ImageView off = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("bajarilmagan.png"))));
                ImageView ed = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("ijroda.png"))));

                on.setFitHeight(27);
                on.setFitWidth(130);
                off.setFitHeight(27);
                off.setFitWidth(130);
                ed.setFitHeight(27);
                ed.setFitWidth(130);
                ImageView photo;


                ImageView eye = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("eye.png"))));
                ImageView done = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("check.png"))));
                ImageView FileOpen = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("fileOpen.png"))));
                Button eyeButton = new JFXButton();
                eyeButton.setGraphic(eye);
                eyeButton.setPrefSize(20, 20);
                eyeButton.setMaxWidth(20);
                eyeButton.setMinWidth(20);
                eyeButton.setMaxHeight(20);
                eyeButton.setMinHeight(20);
                Button doneButton = new JFXButton();
                doneButton.setGraphic(done);
                doneButton.setPrefSize(20, 20);
                doneButton.setMaxWidth(20);
                doneButton.setMinWidth(20);
                doneButton.setMaxHeight(20);
                doneButton.setMinHeight(20);
                Button FileOpenButton = new JFXButton();
                FileOpenButton.setGraphic(FileOpen);
                FileOpenButton.setPrefSize(20, 20);
                FileOpenButton.setMaxWidth(20);
                FileOpenButton.setMinWidth(20);
                FileOpenButton.setMaxHeight(20);
                FileOpenButton.setMinHeight(20);

                Integer queryID = queryOutput.getInt("id");
                String querynameDocument = queryOutput.getString("nameDocument");
                String querynumberDocument = queryOutput.getString("numberDocument");
                String queryperformerDocument = queryOutput.getString("performerDocument");
                String querydateofexecution = queryOutput.getString("dateofexecution");
                String querylastdateDocument = queryOutput.getString("lastdateDocument");
                String queryfileDocument = queryOutput.getString("fileDocument");
                String queryStatus = queryOutput.getString("status");
                String queryConfirmationDocument = queryOutput.getString("confirmationDocument");
                String queryConfirmationDocumentFile = queryOutput.getString("confirmationDocumentFile");
                String a = String.valueOf(queryStatus);
                String textTask = "";
                if (querydateofexecution.length() > 17) {
                    textTask = querydateofexecution.substring(1, 17) + "..";
                } else {
                    textTask = querydateofexecution;
                }
                if (a.equals("bajarilgan")) {
                    photo = on;
                } else if (a.equals("ijroda")) {
                    photo = ed;
                } else {
                    photo = off;

                }

                if (queryConfirmationDocumentFile== null || queryConfirmationDocumentFile.trim()=="") {
                    eyeButton.setVisible(false);
                } else {
                    eyeButton.setVisible(true);
                    eyeButton.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            try {
                                File file = new File(queryConfirmationDocumentFile);
                                if (file.exists()){
                                    if (Desktop.isDesktopSupported()){
                                        Desktop.getDesktop().open(file);
                                    }else {
                                        aLert.setAlertType(Alert.AlertType.ERROR);
                                        aLert.setHeaderText("Bunday fayl mavjud emas!");
                                        aLert.setTitle("Xatolik");
                                        aLert.show();
                                    }
                                }else {
                                    aLert.setAlertType(Alert.AlertType.ERROR);
                                    aLert.setHeaderText("Fayl Manzili notug'ri ko'rsatilgan");
                                    aLert.setTitle("Xatolik");
                                    aLert.show();

                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });

                }
                if (queryfileDocument==null || queryfileDocument.isEmpty()) {
                    FileOpenButton.setVisible(false);
                } else {
                    FileOpenButton.setVisible(true);
                    FileOpenButton.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                        try {
                            File file = new File(queryfileDocument);
                            if (file.exists()){
                                if (Desktop.isDesktopSupported()){
                                    Desktop.getDesktop().open(file);
                                }else {
                                    aLert.setAlertType(Alert.AlertType.ERROR);
                                    aLert.setHeaderText("Bunday fayl mavjud emas!");
                                    aLert.show();
                                }
                            }else {
                                aLert.setAlertType(Alert.AlertType.ERROR);
                                aLert.setHeaderText("Bunday fayl mavjud emas!");
                                aLert.show();

                            }
                        } catch (Exception e){
                            e.printStackTrace();
                        }



                        }
                    });

                }
                if (queryConfirmationDocument== null || queryConfirmationDocument.trim()=="") {
                    doneButton.setVisible(false);
                } else {
                    doneButton.setVisible(true);
                    doneButton.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            System.out.println(queryConfirmationDocument);
                        }
                    });
                }

                modelDeObservableList.add(new ModelDe(queryID, querynameDocument, querynumberDocument, queryperformerDocument, textTask, querylastdateDocument, queryfileDocument, queryStatus, queryConfirmationDocument, queryConfirmationDocumentFile, photo, eyeButton, doneButton,FileOpenButton));
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameDocument.setCellValueFactory(new PropertyValueFactory<>("nameDocument"));
            numberDocument.setCellValueFactory(new PropertyValueFactory<>("numberDocument"));
            performerDocument.setCellValueFactory(new PropertyValueFactory<>("performerDocument"));
            dateofexecution.setCellValueFactory(new PropertyValueFactory<>("dateofexecution"));
            lastdateDocument.setCellValueFactory(new PropertyValueFactory<>("lastdateDocument"));
            fileDocument.setCellValueFactory(new PropertyValueFactory<>("fileDocument"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            imagecolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
            doneDoc.setCellValueFactory(new PropertyValueFactory<>("doneDoc"));
            eyeDoc.setCellValueFactory(new PropertyValueFactory<>("eyeDoc"));
            confirmationDocumentText.setCellValueFactory(new PropertyValueFactory<>("confirmationDocument"));
            confirmationDocumentFile.setCellValueFactory(new PropertyValueFactory<>("confirmationDocumentFile"));
            OpenFileDoc.setCellValueFactory(new PropertyValueFactory<>("OpenFileDoc"));

            List<ModelDe> collect = modelDeObservableList

                    .stream()
                    .sorted((o1, o2) -> {

                        if (o1.getStatus() == o2.getStatus())
                            return o2.getId().compareTo(o1.getId());
                        else if (o1.getStatus() != o2.getStatus() && o2.getStatus() != o2.getStatus()) return 1;
                        else return -1;
                    })
                    .collect(Collectors.toList());


            ObservableList<ModelDe> oListStavaka = FXCollections.observableArrayList(collect);
            ObservableList all = searcher(oListStavaka);
            documentView.setItems(all);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @FXML
    void inDetailOpen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Detail.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Batafsil");
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private Label time;
    private volatile boolean stop = false;

    @FXML
    private Label date;

    private void Timenow() {
        Thread thread = new Thread(() -> {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            while (!stop) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                final String timenow = sdf.format(new Date());
                Platform.runLater(() -> {
                    time.setText(timenow);
                });
            }
        });
        thread.start();
    }

    private void currentDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String datenow = dateFormat.format(new Date());
        date.setText(datenow + "-yil");
    }
    @FXML
    void DoneDocButton(ActionEvent event) throws IOException {
        ModelDe modelde = documentView.getSelectionModel().getSelectedItem();
        ConnectorModelDe.setId(modelde.getId());
        ConnectorModelDe.setConfirmationDocument(modelde.getConfirmationDocument());
        ConnectorModelDe.setConfirmationDocumentFile(modelde.getConfirmationDocumentFile());
        if (modelde.getId() != null

        ) {
            Parent root = FXMLLoader.load(getClass().getResource("ConfirmationDock.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Bajarilganligining izohi");
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    void addbutton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddControllerStyle.fxml"));
        Stage stage = new Stage();
        stage.setTitle("Hujjat Qo‘shish");
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    void editButton(ActionEvent event) throws IOException {
        ModelDe modelde = documentView.getSelectionModel().getSelectedItem();
        ConnectorModelDe.setId(modelde.getId());
        if (modelde.getId() != null

        ) {
            Parent root = FXMLLoader.load(getClass().getResource("EditControllerStyle.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Hujjat Qo‘shish");
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    private TextField textSearcher;

    @FXML
    void inExecution(ActionEvent event) {
        Clear();
        refreshtable();
    }

    void Clear() {
        ObservableList<ModelDe> observableList = FXCollections.observableArrayList();
        documentView.setItems(observableList);
    }

    public ObservableList searcher(ObservableList observableList) {
        FilteredList<ModelDe> filteredList = new FilteredList<>(observableList, b -> true);
        textSearcher.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(employe -> {
                if (newValue == null || newValue.isEmpty()) {

                    return true;
                }
                String lowercaseFilter = newValue.toLowerCase();
                if (employe.getNameDocument().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (employe.getNumberDocument().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (employe.getPerformerDocument().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (employe.getDateofexecution().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else if (employe.getLastdateDocument().toLowerCase().indexOf(lowercaseFilter) != -1) {
                    return true;
                } else
                    return false;
            });
        });
        SortedList<ModelDe> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(documentView.comparatorProperty());

        return sortedList;

    }
    ObservableList<String> stringObservableList=FXCollections.observableArrayList();

    private void CategoryToView(){
    DbConnection dbConnection = new DbConnection();
    java.sql.Connection connectionDB = dbConnection.getDBConnection();
    String categoryViewQuery = "SELECT id,name FROM Category";
    Statement statement = null;
    try {

        statement = connectionDB.createStatement();
        int i=0;
        ResultSet queryOutput = statement.executeQuery(categoryViewQuery);
        while (queryOutput.next()){

            Integer categoryID = queryOutput.getInt("id");
            String categoryName  = queryOutput.getString("name");
            categoryModelList.add(new CategoryModel(categoryID,categoryName));
            stringObservableList.add(categoryModelList.get(i).getName());
            i++;
        }
        documentTypeId.setItems(stringObservableList);

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
private int itemGetSelectCombo(String name){
    for (int i = 0; i < categoryModelList.size(); i++) {
        if (categoryModelList.get(i).getName()==name){
            return categoryModelList.get(i).getId();
        }
    }
    return -1;
}
    public void refreshTableSelected(int myId) {
        change = false;
        ObservableList<ModelDe> modelDeObservableList = FXCollections.observableArrayList();
        try {
            DbConnection dbConnection = new DbConnection();
            java.sql.Connection connectionDB = dbConnection.getDBConnection();
            String documentViewQuery = "SELECT id,nameDocument,numberDocument,performerDocument,dateofexecution,lastdateDocument,fileDocument,confirmationDocument,confirmationDocumentFile,status FROM myDoc where category_id=="+myId;


            Statement statement = connectionDB.createStatement();
            ResultSet queryOutput = statement.executeQuery(documentViewQuery);


            while (queryOutput.next()) {
                ImageView on = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("baj.png"))));
                ImageView off = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("bajarilmagan.png"))));
                ImageView ed = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("ijroda.png"))));

                on.setFitHeight(27);
                on.setFitWidth(130);
                off.setFitHeight(27);
                off.setFitWidth(130);
                ed.setFitHeight(27);
                ed.setFitWidth(130);
                ImageView photo;


                ImageView eye = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("eye.png"))));
                ImageView done = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("check.png"))));
                ImageView FileOpen = new ImageView(new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("fileOpen.png"))));
                Button eyeButton = new JFXButton();
                eyeButton.setGraphic(eye);
                eyeButton.setPrefSize(20, 20);
                eyeButton.setMaxWidth(20);
                eyeButton.setMinWidth(20);
                eyeButton.setMaxHeight(20);
                eyeButton.setMinHeight(20);
                Button doneButton = new JFXButton();
                doneButton.setGraphic(done);
                doneButton.setPrefSize(20, 20);
                doneButton.setMaxWidth(20);
                doneButton.setMinWidth(20);
                doneButton.setMaxHeight(20);
                doneButton.setMinHeight(20);
                Button FileOpenButton = new JFXButton();
                FileOpenButton.setGraphic(FileOpen);
                FileOpenButton.setPrefSize(20, 20);
                FileOpenButton.setMaxWidth(20);
                FileOpenButton.setMinWidth(20);
                FileOpenButton.setMaxHeight(20);
                FileOpenButton.setMinHeight(20);

                Integer queryID = queryOutput.getInt("id");
                String querynameDocument = queryOutput.getString("nameDocument");
                String querynumberDocument = queryOutput.getString("numberDocument");
                String queryperformerDocument = queryOutput.getString("performerDocument");
                String querydateofexecution = queryOutput.getString("dateofexecution");
                String querylastdateDocument = queryOutput.getString("lastdateDocument");
                String queryfileDocument = queryOutput.getString("fileDocument");
                String queryStatus = queryOutput.getString("status");
                String queryConfirmationDocument = queryOutput.getString("confirmationDocument");
                String queryConfirmationDocumentFile = queryOutput.getString("confirmationDocumentFile");
                String a = String.valueOf(queryStatus);
                String textTask = "";
                if (querydateofexecution.length() > 17) {
                    textTask = querydateofexecution.substring(1, 17) + "..";
                } else {
                    textTask = querydateofexecution;
                }
                if (a.equals("bajarilgan")) {
                    photo = on;
                } else if (a.equals("ijroda")) {
                    photo = ed;
                } else {
                    photo = off;

                }

                if (queryConfirmationDocumentFile== null || queryConfirmationDocumentFile.trim()=="") {
                    eyeButton.setVisible(false);
                } else {
                    eyeButton.setVisible(true);
                    eyeButton.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            try {
                                File file = new File(queryConfirmationDocumentFile);
                                if (file.exists()){
                                    if (Desktop.isDesktopSupported()){
                                        Desktop.getDesktop().open(file);
                                    }else {
                                        aLert.setAlertType(Alert.AlertType.ERROR);
                                        aLert.setHeaderText("Bunday fayl mavjud emas!");
                                        aLert.setTitle("Xatolik");
                                        aLert.show();
                                    }
                                }else {
                                    aLert.setAlertType(Alert.AlertType.ERROR);
                                    aLert.setHeaderText("Fayl Manzili notug'ri ko'rsatilgan");
                                    aLert.setTitle("Xatolik");
                                    aLert.show();

                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    });

                }
                if (queryfileDocument==null || queryfileDocument.isEmpty()) {
                    FileOpenButton.setVisible(false);
                } else {
                    FileOpenButton.setVisible(true);
                    FileOpenButton.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            try {
                                File file = new File(queryfileDocument);
                                if (file.exists()){
                                    if (Desktop.isDesktopSupported()){
                                        Desktop.getDesktop().open(file);
                                    }else {
                                        aLert.setAlertType(Alert.AlertType.ERROR);
                                        aLert.setHeaderText("Bunday fayl mavjud emas!");
                                        aLert.show();
                                    }
                                }else {
                                    aLert.setAlertType(Alert.AlertType.ERROR);
                                    aLert.setHeaderText("Bunday fayl mavjud emas!");
                                    aLert.show();

                                }
                            } catch (Exception e){
                                e.printStackTrace();
                            }



                        }
                    });

                }
                if (queryConfirmationDocument== null || queryConfirmationDocument.trim()=="") {
                    doneButton.setVisible(false);
                } else {
                    doneButton.setVisible(true);
                    doneButton.setOnAction(new EventHandler() {
                        @Override
                        public void handle(Event event) {
                            System.out.println(queryConfirmationDocument);
                        }
                    });
                }

                modelDeObservableList.add(new ModelDe(queryID, querynameDocument, querynumberDocument, queryperformerDocument, textTask, querylastdateDocument, queryfileDocument, queryStatus, queryConfirmationDocument, queryConfirmationDocumentFile, photo, eyeButton, doneButton,FileOpenButton));
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameDocument.setCellValueFactory(new PropertyValueFactory<>("nameDocument"));
            numberDocument.setCellValueFactory(new PropertyValueFactory<>("numberDocument"));
            performerDocument.setCellValueFactory(new PropertyValueFactory<>("performerDocument"));
            dateofexecution.setCellValueFactory(new PropertyValueFactory<>("dateofexecution"));
            lastdateDocument.setCellValueFactory(new PropertyValueFactory<>("lastdateDocument"));
            fileDocument.setCellValueFactory(new PropertyValueFactory<>("fileDocument"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            imagecolumn.setCellValueFactory(new PropertyValueFactory<>("photo"));
            doneDoc.setCellValueFactory(new PropertyValueFactory<>("doneDoc"));
            eyeDoc.setCellValueFactory(new PropertyValueFactory<>("eyeDoc"));
            confirmationDocumentText.setCellValueFactory(new PropertyValueFactory<>("confirmationDocument"));
            confirmationDocumentFile.setCellValueFactory(new PropertyValueFactory<>("confirmationDocumentFile"));
            OpenFileDoc.setCellValueFactory(new PropertyValueFactory<>("OpenFileDoc"));

            List<ModelDe> collect = modelDeObservableList

                    .stream()
                    .sorted((o1, o2) -> {

                        if (o1.getStatus() == o2.getStatus())
                            return o2.getId().compareTo(o1.getId());
                        else if (o1.getStatus() != o2.getStatus() && o2.getStatus() != o2.getStatus()) return 1;
                        else return -1;
                    })
                    .collect(Collectors.toList());


            ObservableList<ModelDe> oListStavaka = FXCollections.observableArrayList(collect);
            ObservableList all = searcher(oListStavaka);
            documentView.setItems(all);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}