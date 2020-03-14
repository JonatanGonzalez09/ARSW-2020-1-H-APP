package edu.eci.arsw.happ.model;

import java.sql.Date;

public class NurseAssistant extends Nurse {

    private Date startShiftTurnNurseAssistant;
    private Date endShiftTurnNurseAssistant;
    private int assignedFloorNurseAssistant;

    public NurseAssistant(String idDocument, String documentType, String name) {
		super(idDocument, documentType, name);
    }

    public Date getStartShiftTurnNurseAssistant(){
        return startShiftTurnNurseAssistant;
    }

    public Date getEndShiftTurnNurseAssistant(){
        return endShiftTurnNurseAssistant;
    }

    public int getAssignedFloorNurseAssistant(){
        return assignedFloorNurseAssistant;
    }

    public void setStartShiftTurnNurseAssistant(Date startShiftTurnNurseAssistant){
        this.startShiftTurnNurseAssistant = startShiftTurnNurseAssistant;
    }

    public void setEndShiftTurnNurseAssistant(Date endShiftTurnNurseAssistant){
        this.endShiftTurnNurseAssistant = endShiftTurnNurseAssistant;
    }

    public void setAssignedFloorNurseAssistant(int assignedFloorNurseAssistant){
        this.assignedFloorNurseAssistant = assignedFloorNurseAssistant;
    }

    public int getPatientNurseAssistant(Patient id){
        return id.getIdPatient();
    }

    public String getIdDocumentNurseAssistant(){
        return Nurse.idDocument;
    }

    public String getDocumentTypeNurseAssistant(){
        return Nurse.documentType;
    }

    public String getNameNurseAssistant(){
        return Nurse.name;
    }
}
