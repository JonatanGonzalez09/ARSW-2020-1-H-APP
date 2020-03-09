package happ.model;

import java.sql.Date;

public class Nurse extends User {

    private Date startShiftTurn;
    private Date endShiftTurn;
    private Date assignedFloor;

    public Nurse(String idDocument, String documentType, String name) {
        super(idDocument, documentType, name);
    }

    public Date getStartShiftTurn(){
        return startShiftTurn;
    }

    public Date getEndShiftTurn(){
        return endShiftTurn;
    }

    public Date getAssignedFloor(){
        return assignedFloor;
    }

    public void setStartShiftTurn(Date startShiftTurn){
        this.startShiftTurn = startShiftTurn;
    }

    public void setEndShiftTurn(Date endShiftTurn){
        this.endShiftTurn = endShiftTurn;
    }

    public void setAssignedFloor(Date assignedFloor){
        this.assignedFloor = assignedFloor;
    }
}