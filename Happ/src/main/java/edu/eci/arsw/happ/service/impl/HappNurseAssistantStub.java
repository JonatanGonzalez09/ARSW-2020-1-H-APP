/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.service.impl;

import edu.eci.arsw.happ.model.Floor;
import edu.eci.arsw.happ.model.Nurse;
import edu.eci.arsw.happ.model.Patient;
import edu.eci.arsw.happ.model.Task;
import edu.eci.arsw.happ.persistence.HappNurseAssistantPersistence;
import edu.eci.arsw.happ.persistence.HappPersistenceException;
import edu.eci.arsw.happ.service.HappNurseAssistant;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author jualme
 */
public class HappNurseAssistantStub implements HappNurseAssistant {
    
    @Autowired
    HappNurseAssistantPersistence Hp;
    
    
    /**
     * Metodo encargado de regresasr la informacion de la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @throws HappPersistenceException 
     */
    @Override
    public Nurse getCurrentNurseAssistant(String idDocument) throws HappPersistenceException {
        return Hp.getCurrentNurseAssistant(idDocument);
    }

    
    /**
     * Metodo encargado de regresasr la informacion de los pacientes asignados a la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @throws HappPersistenceException 
     */    
    @Override
    public List<Patient> getPatients(String idDocument) throws HappPersistenceException {
        return Hp.getPatients(idDocument);
    }

    /**
     * Metodo encargado de regresasr la informacion del paciente asignado a la enfermera asistente
     * @param idDocument documento de identidad del paciente
     * @throws HappPersistenceException 
     */ 
    @Override
    public Patient getPatient(String idDocument) throws HappPersistenceException {
        return Hp.getPatient(idDocument);
    }
    
     /**
     * Metodo encargado de regresasr la informacion del piso asignado a la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @throws HappPersistenceException 
     */ 
    @Override
    public Floor getFloor(String idDocument) throws HappPersistenceException {
        return Hp.getFloor(idDocument);        
    }

    /**
     * Metodo encargado de regresasr la informacion del jefe asignado a la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @throws HappPersistenceException 
     */     
    @Override
    public Nurse getBoss(String idDocument) throws HappPersistenceException {
        return Hp.getBoss(idDocument);
    }

    /**
     * Metodo encargado de regresasr la informacion de las tareas asignadas a la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @throws HappPersistenceException 
     */    
    @Override
    public List<Task> getTasks(String idDocument) throws HappPersistenceException {
        return Hp.getTasks(idDocument);
    }

    /**
     * Metodo encargado de regresasr la informacion de la tarea asignada a la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @param taskId Id de la tarea a consultar
     * @throws HappPersistenceException 
     */
    @Override
    public Task getTask(String idDocument, String taskId) throws HappPersistenceException {
        return Hp.getTask(idDocument, taskId);
    }

    /**
     * Metodo encargado de actualizar la informacion de la tarea asignada a la enfermera asistente
     * @param idDocument documento de identidad de la enfermera
     * @param taskId Id de la tarea a actualizar
     * @param newts la informacion de la nueva tarea
     * @throws HappPersistenceException 
     */
    @Override
    public void updateTask(String idDocument, String taskId, Task newts) throws HappPersistenceException {
        Hp.updateTask(idDocument, taskId, newts);
    }
    
}
