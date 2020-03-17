/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.service;

import edu.eci.arsw.happ.model.Floor;
import edu.eci.arsw.happ.model.Nurse;
import edu.eci.arsw.happ.model.Patient;
import edu.eci.arsw.happ.model.Task;
import java.util.List;

/**
 *
 * @author jualm
 */
public interface HappNurse {
    
    public List<Patient> getPatients();
    
    public Patient getPatient(String idDocument);
    
    public Floor getFloor();
    
    public Floor setFloor(Nurse nurse);
    
    public List<Task> getTasks();
    
    public Task getTask(String taskId);
    
    public void setTask(String taskId); 
    
    public void updateTask(String taskId);   
    
}
