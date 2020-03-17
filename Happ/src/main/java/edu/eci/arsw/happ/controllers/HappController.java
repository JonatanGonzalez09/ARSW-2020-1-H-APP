package edu.eci.arsw.happ.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.eci.arsw.happ.service.HappException;
import edu.eci.arsw.happ.service.HappService;

@RestController
@RequestMapping(value = "/nurses-assistants")
public class HappController {

    @Autowired
    HappService happService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getNursesAssistants() throws HappException {
        return new ResponseEntity<>(happService.getNurseAssistants(), HttpStatus.ACCEPTED);
    }
    
    

}