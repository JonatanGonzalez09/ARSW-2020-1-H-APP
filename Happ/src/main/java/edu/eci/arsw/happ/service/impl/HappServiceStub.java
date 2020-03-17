package edu.eci.arsw.happ.service.impl;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Service;

import edu.eci.arsw.happ.model.NurseAssistant;
import edu.eci.arsw.happ.service.HappService;

@Service
public class HappServiceStub implements HappService {
    List<NurseAssistant> nurseAssistantList;

    public HappServiceStub(){
        nurseAssistantList = new CopyOnWriteArrayList<>();
        NurseAssistant nurseAssistant1 = new NurseAssistant("1014292509", "Cedula de ciudadania", "Patricia Mendoza");
        NurseAssistant nurseAssistant2 = new NurseAssistant("1014292510", "Cedula de ciudadania", "Laura Bernal");
        NurseAssistant nurseAssistant3 = new NurseAssistant("1014292511", "Cedula de ciudadania", "Angie Ramirez");
        nurseAssistantList.add(nurseAssistant1);
        nurseAssistantList.add(nurseAssistant2);
        nurseAssistantList.add(nurseAssistant3);
    }

    @Override
    public List<NurseAssistant> getNurseAssistants() {
        return nurseAssistantList;
    }
    
}