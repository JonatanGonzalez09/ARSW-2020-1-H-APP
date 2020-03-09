package happ.model;

import java.util.ArrayList;

public class ChiefNurse extends Nurse {

    public ChiefNurse(String idDocument, String documentType, String name) {
        super(idDocument, documentType, name);
    }
    
    public void AssignAssistant(NurseAssistant assistants){

    }

    public ArrayList<Patient> getPatient(){
        return null;
    }

    public void assignPatientOnFloor (Patient persona){

    }

    public void updatePatient(Patient persona){

    }

    public void createProcedures(){

    }

    public void solicitudCambioDePisoPaciente(Floor piso){

    }

    public ArrayList<Patient> getPatientsState(){
        return null;
    }

    public void asignacionTareasEnfermeraAuxiliar(NurseAssistant persona){

    }

    public ArrayList<String> getAssistantsTaskState(){
        return null;
    }

    public ArrayList<Patient> generarReportMedicoPaciente(){
        return null;
    }

    public void asignarEnferneraAuxiliarAPiso (NurseAssistant enfermera){

    }

    public ArrayList<String> consultarEstadoResponsabilidadesAuxiliares(NurseAssistant enfermera){
        return null;
    }

    public void consultarAlertasAuxiliares(NurseAssistant auxiliar){

    }
}