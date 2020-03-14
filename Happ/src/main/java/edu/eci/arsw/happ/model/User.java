package edu.eci.arsw.happ.model;

public class User {
    protected static String idDocument;
    protected static String documentType;
    protected static String name;

    public User(String idDocument, String documentType, String name){
        this.idDocument = idDocument;
        this.documentType = documentType;
        this.name = name;
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
        this.idDocument = idDocuement;
    }

    public void setDocumentType(String documentType ) {
        this.documentType = documentType;
    }

    public void setName(String name ) {
        this.name = name;
    }
}
