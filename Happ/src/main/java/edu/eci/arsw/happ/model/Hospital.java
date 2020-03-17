package edu.eci.arsw.happ.model;

import java.util.ArrayList;

public class Hospital {

    Patient[] patients = new Patient[]{new Patient("1014292509", "Cedula de Ciudadania", "Jonatan Gonzalez"), new Patient("1014292510", "Cedula de Ciudadania", "Andres Perez")};
    NurseAssistant[] nursesAssistant = new NurseAssistant[]{new NurseAssistant("123456789", "Cedula de Ciudadania", "Patricia Mendoza"), new NurseAssistant("1234567810", "Cedula de Ciudadania", "Carla Morrison")};
    Nurse[] nurses = new Nurse[]{new Nurse("103456789", "Cedula de Ciudadania", "Wendy Patiño"), new Nurse("1134567810", "Cedula de Ciudadania", "Lucia Ramirez")};
    Floor[] floors = new Floor[]{new Floor(1, 5, true), new Floor(2, 5, true)};
    Bed[] beds = new Bed[]{new Bed(1, true), new Bed(2, true), new Bed(3, true), new Bed(4, true), new Bed(5, true)};

    public ArrayList<Nurse> getNurses(){
        ArrayList<Nurse> listNurse = new ArrayList<Nurse>();
        for (int i=0; i < nurses.length; i++){
            listNurse.add(nurses[i]);
        }
        return listNurse;
    }

    public ArrayList<NurseAssistant> getNursesAssistant(){
        ArrayList<NurseAssistant> listNurseAssistant = new ArrayList<NurseAssistant>();
        for (int i=0; i < nursesAssistant.length; i++){
            listNurseAssistant.add(nursesAssistant[i]);
        }
        return listNurseAssistant;
    }

    public ArrayList<Patient> getPatients(){
        ArrayList<Patient> listPatients = new ArrayList<Patient>();
        for (int i=0; i < patients.length; i++){
            listPatients.add(patients[i]);
        }
        return listPatients;
    }

    public ArrayList<Floor> getFloors(){
        ArrayList<Floor> listFloor = new ArrayList<Floor>();
        for (int i=0; i < floors.length; i++){
            listFloor.add(floors[i]);
        }
        return listFloor;
    }

    public ArrayList<Bed> getBeds(){
        ArrayList<Bed> listBeds = new ArrayList<Bed>();
        for (int i=0; i < beds.length; i++){
            listBeds.add(beds[i]);
        }
        return listBeds;
    }

    public ArrayList<Patient> getPatientsFromSofia(){
        return null;
    }

}