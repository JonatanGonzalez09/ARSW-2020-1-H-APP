/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.persistence;

import edu.eci.arsw.happ.model.Floor;
import edu.eci.arsw.happ.model.Nurse;
import edu.eci.arsw.happ.model.Patient;
import edu.eci.arsw.happ.model.Task;
import java.util.List;

/**
 *
 * @author jualme
 */
public interface HappNurseAssistantPersistence {

    public Nurse getCurrentNurseAssistant(String idDocument) throws HappPersistenceException;
    
    public List<Patient> getPatients(String idDocument) throws HappPersistenceException;
    
    public Patient getPatient(String idDocument) throws HappPersistenceException;
    
    public Floor getFloor(String idDocument) throws HappPersistenceException;
    
    public Nurse getBoss(String idDocument) throws HappPersistenceException;
    
    public List<Task> getTasks(String idDocument) throws HappPersistenceException;
    
    public Task getTask(String idDocument, String taskId) throws HappPersistenceException;
    
    public void updateTask(String idDocument,String taskId, Task newts) throws HappPersistenceException; 
    
}
