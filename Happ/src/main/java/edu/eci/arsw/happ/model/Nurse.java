package edu.eci.arsw.happ.model;

import java.sql.Date;

public class Nurse extends User {

    private Date startShiftTurn;
    private Date endShiftTurn;
    private int assignedFloor;

    public Nurse(String idDocument, String documentType, String name) {
        super(idDocument, documentType, name);
    }

    public Date getStartShiftTurn(){
        return startShiftTurn;
    }

    public Date getEndShiftTurn(){
        return endShiftTurn;
    }

    public int getAssignedFloor(){
        return assignedFloor;
    }

    public void setStartShiftTurn(Date startShiftTurn){
        this.startShiftTurn = startShiftTurn;
    }

    public void setEndShiftTurn(Date endShiftTurn){
        this.endShiftTurn = endShiftTurn;
    }

    public void setAssignedFloor(int assignedFloor){
        this.assignedFloor = assignedFloor;
    }

    public String getIdDocumentNurse(){
        return Nurse.idDocument;
    }

    public String getDocumentTypeNurse(){
        return Nurse.documentType;
    }

    public String getNameNurse(){
        return Nurse.name;
    }
}