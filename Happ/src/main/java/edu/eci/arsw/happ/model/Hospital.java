package edu.eci.arsw.happ.model;

import java.util.ArrayList;

public class Hospital {
    public ArrayList<Nurse> nurses = new ArrayList<Nurse>();
    public ArrayList<NurseAssistant> nursesAssistant = new ArrayList<NurseAssistant>();
    public ArrayList<Patient> patients = new ArrayList<Patient>();
    
    public Hospital(){
        
    }

    public ArrayList<Nurse> getNurses(){
        return nurses;
    }

    public ArrayList<NurseAssistant> getNursesAssistant(){
        return nursesAssistant;
    }

    public ArrayList<Patient> getPatients(){
        return patients;
    }

    public ArrayList<Patient> getPatientsFromSofia(){
        return null;
    }

}