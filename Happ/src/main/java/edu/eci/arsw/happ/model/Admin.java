package edu.eci.arsw.happ.model;

public class Admin extends User {

    protected static String idDocument;
    protected static String documentType;
    protected static String name;

    public Admin(String idDocument, String documentType, String name) {
        super(idDocument, documentType, name);
    }

    public String getIdDocumentAdmin() {
        return idDocument;
    }

    public String getDocumentTypeAdmin() {
        return documentType;
    }

    public String getNameAdmin() {
        return name;
    }

    public void setIdDocuementAdmin(String idDocuement) {
        this.idDocument = idDocuement;
    }

    public void setDocumentTypeAdmin(String documentType ) {
        this.documentType = documentType;
    }

    public void setNameAdmin(String name ) {
        this.name = name;
    }


}
    