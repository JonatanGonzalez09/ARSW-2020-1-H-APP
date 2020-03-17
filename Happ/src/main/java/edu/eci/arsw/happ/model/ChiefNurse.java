package edu.eci.arsw.happ.model;

import java.sql.Date;

public class ChiefNurse extends Nurse {
    
    private Date startShiftTurnChiefNurse;
    private Date endShiftTurnChiefNurse;
    private int assignedFloorChiefNurse;

    public ChiefNurse(String idDocument, String documentType, String name) {
        super(idDocument, documentType, name);
    }

    public Date getStartShiftTurnChiefNurse(){
        return startShiftTurnChiefNurse;
    }

    public Date getEndShiftTurnChiefNurse(){
        return endShiftTurnChiefNurse;
    }

    public int getAssignedFloorChiefNurse(){
        return assignedFloorChiefNurse;
    }

    public void setStartShiftTurnChiefNurse(Date startShiftTurnChiefNurse){
        this.startShiftTurnChiefNurse = startShiftTurnChiefNurse;
    }

    public void setEndShiftTurnChiefNurse(Date endShiftTurnChiefNurse){
        this.endShiftTurnChiefNurse = endShiftTurnChiefNurse;
    }

    public void setAssignedFloorChiefNurse(int assignedFloorChiefNurse){
        this.assignedFloorChiefNurse = assignedFloorChiefNurse;
    }

    public int getPatientChiefNurse(Patient id){
        return id.getIdPatient();
    }

    public String getIdDocumentChiefNurse(){
        return Nurse.idDocument;
    }

    public String getDocumentTypeChiefNurse(){
        return Nurse.documentType;
    }

    public String getNameChiefNurse(){
        return Nurse.name;
    }
}