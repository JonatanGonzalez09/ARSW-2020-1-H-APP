package edu.eci.arsw.happ.happapi.model;

public class User {
    protected static String idDocument;
    protected static String documentType;
    protected static String name;

    public User(String idDocument, String documentType, String name){
        User.idDocument = idDocument;
        User.documentType = documentType;
        User.name = name;
    }

    public String getIdDocumentUser() {
        return idDocument;
    }

    public String getDocumentTypeUser() {
        return documentType;
    }

    public String getNameUser() {
        return name;
    }

    public void setIdDocuement(String idDocuement) {
        User.idDocument = idDocuement;
    }

    public void setDocumentType(String documentType ) {
        User.documentType = documentType;
    }

    public void setName(String name ) {
        User.name = name;
    }
}
