package com.example.documentdate.model;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class
ModelDe {
    private Integer id;
    private String nameDocument, numberDocument, performerDocument, dateofexecution, lastdateDocument, fileDocument, status,confirmationDocument,confirmationDocumentFile;
    private ImageView photo;

    private Button eyeDoc,doneDoc,OpenFileDoc;

    public ModelDe(Integer id, String nameDocument, String numberDocument, String performerDocument, String dateofexecution, String lastdateDocument, String fileDocument, String status, String confirmationDocument, String confirmationDocumentFile, ImageView photo, Button eyeDoc, Button doneDoc,Button OpenFileDoc) {
        this.id = id;
        this.nameDocument = nameDocument;
        this.numberDocument = numberDocument;
        this.performerDocument = performerDocument;
        this.dateofexecution = dateofexecution;
        this.lastdateDocument = lastdateDocument;
        this.fileDocument = fileDocument;
        this.status = status;
        this.confirmationDocument = confirmationDocument;
        this.confirmationDocumentFile = confirmationDocumentFile;
        this.photo = photo;
        this.eyeDoc = eyeDoc;
        this.doneDoc = doneDoc;
        this.OpenFileDoc = OpenFileDoc;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDocument() {
        return nameDocument;
    }

    public void setNameDocument(String nameDocument) {
        this.nameDocument = nameDocument;
    }

    public String getNumberDocument() {
        return numberDocument;
    }

    public void setNumberDocument(String numberDocument) {
        this.numberDocument = numberDocument;
    }

    public String getPerformerDocument() {
        return performerDocument;
    }

    public void setPerformerDocument(String performerDocument) {
        this.performerDocument = performerDocument;
    }

    public String getDateofexecution() {
        return dateofexecution;
    }

    public void setDateofexecution(String dateofexecution) {
        this.dateofexecution = dateofexecution;
    }

    public String getLastdateDocument() {
        return lastdateDocument;
    }

    public void setLastdateDocument(String lastdateDocument) {
        this.lastdateDocument = lastdateDocument;
    }

    public String getFileDocument() {
        return fileDocument;
    }

    public void setFileDocument(String fileDocument) {
        this.fileDocument = fileDocument;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getConfirmationDocument() {
        return confirmationDocument;
    }

    public void setConfirmationDocument(String confirmationDocument) {
        this.confirmationDocument = confirmationDocument;
    }

    public String getConfirmationDocumentFile() {
        return confirmationDocumentFile;
    }

    public void setConfirmationDocumentFile(String confirmationDocumentFile) {
        this.confirmationDocumentFile = confirmationDocumentFile;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public Button getEyeDoc() {
        return eyeDoc;
    }

    public void setEyeDoc(Button eyeDoc) {
        this.eyeDoc = eyeDoc;
    }

    public Button getDoneDoc() {
        return doneDoc;
    }

    public void setDoneDoc(Button doneDoc) {
        this.doneDoc = doneDoc;
    }

    public Button getOpenFileDoc() {
        return OpenFileDoc;
    }

    public void setOpenFileDoc(Button openFileDoc) {
        OpenFileDoc = openFileDoc;
    }
}