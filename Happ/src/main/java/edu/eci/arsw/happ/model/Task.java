/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.model;

/**
 *
 * @author jualm
 */
public class Task {

    private String taskId;
    private Nurse nurse;
    private Patient patient;
    private Procedure procedure;
    
    public Task(String taskId, Nurse nurse, Patient patient, Procedure procedure) {
        this.taskId = taskId;   
        this.nurse = nurse;
        this.patient = patient;
        this.procedure = procedure;       
    }

    public String getTaskId() {
        return taskId;
    }

    public Nurse getNurse() {
        return nurse;
    }

    public Patient getPatient() {
        return patient;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }
    
    
    
    
    
}
