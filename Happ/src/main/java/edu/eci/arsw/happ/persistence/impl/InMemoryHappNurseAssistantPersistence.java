/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.persistence.impl;

import edu.eci.arsw.happ.model.Floor;
import edu.eci.arsw.happ.model.Nurse;
import edu.eci.arsw.happ.model.Patient;
import edu.eci.arsw.happ.model.Task;
import edu.eci.arsw.happ.persistence.HappNurseAssistantPersistence;
import edu.eci.arsw.happ.persistence.HappPersistenceException;
import java.util.List;

/**
 *
 * @author jualme
 */
public class InMemoryHappNurseAssistantPersistence implements HappNurseAssistantPersistence {

    @Override
    public Nurse getCurrentNurseAssistant(String idDocument) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Patient> getPatients(String idDocument) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient getPatient(String idDocument) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Floor getFloor(String idDocument) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Nurse getBoss(String idDocument) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Task> getTasks(String idDocument) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Task getTask(String idDocument, String taskId) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTask(String idDocument, String taskId, Task newts) throws HappPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
