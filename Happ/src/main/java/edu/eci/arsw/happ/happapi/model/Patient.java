package edu.eci.arsw.happ.happapi.model;

public class Patient extends User {

    private int idPatient;
    private String rh;
    private String birthDay;

    public Patient(String idDocument, String documentType, String name) {
        super(idDocument, documentType, name);
    }

    public int getIdPatient(){
        return idPatient;
    }

    public String getRH(){
        return rh;
    }

    public String getBirthDay(){
        return birthDay;
    }

    public void setRH(String rh){
        this.rh = rh;
    }

    public void setIdPatient(int idPatient){
        this.idPatient = idPatient;
    }

    public void setBirthDay(String birthDay){
        this.birthDay = birthDay;
    }

    public void updateProcedures(){

    }

    public void assignProcedures(){

    }

    public void getProcedures(){

    }

    public void assignRoom(){

    }

    public void updateRoom(){

    }
}