package com.example.documentdate.model;

import javafx.scene.image.ImageView;

public class ConnectorModelDe {
    public static Integer id;
    public static String nameDocument, numberDocument, performerDocument, dateofexecution, lastdateDocument, fileDocument, status,confirmationDocument,confirmationDocumentFile;

    public static ImageView getPhoto() {
        return photo;
    }

    public static void setPhoto(ImageView photo) {
        ConnectorModelDe.photo = photo;
    }

    public static ImageView photo;
    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        ConnectorModelDe.id = id;
    }

    public static String getNameDocument() {
        return nameDocument;
    }

    public static void setNameDocument(String nameDocument) {
        ConnectorModelDe.nameDocument = nameDocument;
    }

    public static String getNumberDocument() {
        return numberDocument;
    }

    public static void setNumberDocument(String numberDocument) {
        ConnectorModelDe.numberDocument = numberDocument;
    }

    public static String getPerformerDocument() {
        return performerDocument;
    }

    public static void setPerformerDocument(String performerDocument) {
        ConnectorModelDe.performerDocument = performerDocument;
    }

    public static String getDateofexecution() {
        return dateofexecution;
    }

    public static void setDateofexecution(String dateofexecution) {
        ConnectorModelDe.dateofexecution = dateofexecution;
    }

    public static String getLastdateDocument() {
        return lastdateDocument;
    }

    public static void setLastdateDocument(String lastdateDocument) {
        ConnectorModelDe.lastdateDocument = lastdateDocument;
    }

    public static String getFileDocument() {
        return fileDocument;
    }

    public static void setFileDocument(String fileDocument) {
        ConnectorModelDe.fileDocument = fileDocument;
    }

    public static String getStatus() {
        return status;
    }

    public static void setStatus(String status) {
        ConnectorModelDe.status = status;
    }

    public static String getConfirmationDocument() {
        return confirmationDocument;
    }

    public static void setConfirmationDocument(String confirmationDocument) {
        ConnectorModelDe.confirmationDocument = confirmationDocument;
    }

    public static String getConfirmationDocumentFile() {
        return confirmationDocumentFile;
    }

    public static void setConfirmationDocumentFile(String confirmationDocumentFile) {
        ConnectorModelDe.confirmationDocumentFile = confirmationDocumentFile;
    }
}