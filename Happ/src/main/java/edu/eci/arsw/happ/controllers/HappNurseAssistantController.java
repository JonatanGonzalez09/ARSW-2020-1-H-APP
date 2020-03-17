/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.happ.controllers;

import edu.eci.arsw.happ.model.Task;
import edu.eci.arsw.happ.persistence.HappPersistenceException;
    import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.happ.service.HappException;
import edu.eci.arsw.happ.service.HappNurseAssistant;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author jualm
 */
@RestController
@RequestMapping(value = "/nurses-assistants/{idDocument}")
public class HappNurseAssistantController {

    @Autowired
    HappNurseAssistant happService;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getCurrentNurseAssistant(@PathVariable ("idDocument") String idDocument) throws HappException {
        try {
            return new ResponseEntity<>(happService.getCurrentNurseAssistant(idDocument), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getPatients(@PathVariable ("idDocument") String idDocument) throws HappException {
        try {
            return new ResponseEntity<>(happService.getPatients(idDocument), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }   
    
    @RequestMapping(path ="/patients/{idDocument}",method = RequestMethod.GET)
    public ResponseEntity<?> getPatient(@PathVariable ("idDocument") String idDocument) throws HappException {
        try {
            return new ResponseEntity<>(happService.getPatient(idDocument), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }    
    
    @RequestMapping(path ="/floor",method = RequestMethod.GET)
    public ResponseEntity<?> getFoor(@PathVariable ("idDocument") String idDocument) throws HappException {
        try {
            return new ResponseEntity<>(happService.getFloor(idDocument), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path ="/boss",method = RequestMethod.GET)
    public ResponseEntity<?> getBoss(@PathVariable ("idDocument") String idDocument) throws HappException {
        try {
            return new ResponseEntity<>(happService.getBoss(idDocument), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path ="/tasks",method = RequestMethod.GET)
    public ResponseEntity<?> getTasks(@PathVariable ("idDocument") String idDocument) throws HappException {
        try {
            return new ResponseEntity<>(happService.getTasks(idDocument), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path ="/tasks/{taskId}",method = RequestMethod.GET)
    public ResponseEntity<?> getTask(@PathVariable ("idDocument") String idDocument, @PathVariable ("taskId") String taskId) throws HappException {
        try {
            return new ResponseEntity<>(happService.getTask(idDocument,taskId), HttpStatus.ACCEPTED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
    
    
    @RequestMapping(path ="/tasks/{taskId}",method = RequestMethod.PUT)
    public ResponseEntity<?> updateTask(@PathVariable ("idDocument") String idDocument,@PathVariable ("taskId") String taskId, @RequestBody Task newts) throws HappException {
        try {
            happService.updateTask(idDocument,taskId,newts);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (HappPersistenceException ex) {
            Logger.getLogger(HappNurseAssistantController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(ex.getMessage(),HttpStatus.FORBIDDEN);
        }
    }
}
