package edu.eci.arsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.eci.arsw.model.Bed;
import edu.eci.arsw.model.Patient;
import edu.eci.arsw.model.Procedure;
import edu.eci.arsw.model.Undergoes;
import edu.eci.arsw.service.NurseManagerService;

@Controller
@RequestMapping("nurse")
public class ManagementController {

    @Autowired
    NurseManagerService nurseManagerService;

    @GetMapping("prueba")
    public String getPrueba(){
        return "Hello World !";
    }

    @GetMapping("patients")
    public List<Patient> getAllPatiens(){
        return this.nurseManagerService.getPatients();
    }

    @GetMapping("undergoes")
    public List<Undergoes> getAllUndergoes(){
        return this.nurseManagerService.getUndergoes();
    }

    @GetMapping("procedures")
    public List<Procedure> getAllProcedures(){
        return this.nurseManagerService.getProcedures();
    }
    
    @RequestMapping(method = RequestMethod.PUT, value = "/undergoes/{idUndergoes}}")
	public void updateUndergoes(@RequestBody Undergoes newUndergoes, @PathVariable("idUndergoes") int idUndergoes){
        List<Undergoes> undergoes = nurseManagerService.getUndergoes();
        for (int i=0; i < undergoes.size();i++){
            if (undergoes.get(i).getUndergoesId() == newUndergoes.getUndergoesId()){
                this.nurseManagerService.updateUndergoes(newUndergoes);
            }
        }
    }

    @RequestMapping(method = RequestMethod.POST, value="/new-undergoes")
    public void addBlock(@RequestBody Undergoes undergoes){
        nurseManagerService.setUndergoes(undergoes);
    }

    @RequestMapping(method = RequestMethod.POST, value="/new-procedure")
    public void addBlock(@RequestBody Procedure procedure){
        nurseManagerService.setProcedure(procedure);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/patient/{idBed}}")
	public void updateProcedures(@RequestBody Bed bed, @PathVariable("idBed") int idBed){
        List<Bed> beds = nurseManagerService.getAllBed();
        for (int i=0; i < beds.size();i++){
            if (beds.get(i).getBedId() == bed.getBedId()){
                this.nurseManagerService.updateBed(bed);
            }
        }
    }




}