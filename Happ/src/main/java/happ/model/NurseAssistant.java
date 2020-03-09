package happ.model;

import java.util.ArrayList;

public class NurseAssistant extends Nurse {

    public NurseAssistant(String idDocument, String documentType, String name) {
		super(idDocument, documentType, name);
    }

    public int getPatient(Patient id){
        return id.getIdPatient();
    }

    public void updatePatientProcedures(){

    }

    public void sendAlert(){

    }

    public ArrayList<Patient> getPatients() {
        return null;
    }
}
